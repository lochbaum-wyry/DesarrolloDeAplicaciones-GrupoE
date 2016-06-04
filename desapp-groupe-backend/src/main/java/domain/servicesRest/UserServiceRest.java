package domain.servicesRest;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.oauth2.model.Userinfoplus;
import domain.*;
import domain.builders.UserBuilder;
import domain.exceptions.SingUpException;
import domain.exceptions.SubiQueTeLlevoException;
import domain.services.GoogleCredentialsService;
import domain.services.UserService;
import domain.services.UserTokenService;
import helpers.UserAuthorization;
import helpers.UserTokenResponse;
import org.springframework.stereotype.Service;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
@Service
public class UserServiceRest {


    UserService userService;
    GoogleCredentialsService googleCredentialsService;
    UserTokenService userTokenService;

    public UserServiceRest(UserService userService,GoogleCredentialsService googleCredentialsService,UserTokenService userTokenService) {
        this.userService = userService;
        this.googleCredentialsService = googleCredentialsService;
        this.userTokenService = userTokenService;
    }

    @GET
    @Path("user")
    @Produces("application/json")
    public User user(){
        User user = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();
        return user;
    }

    @GET
    @Path("list")
    @Produces("application/json")
    public List<User> listUser(){
        return userService.getUserRepository().findAll();
    }

    @POST
    @Path("signUpAndLogin")
    @Consumes("application/json")
    @Produces("application/json")
    public User signUp(User user){

        User realUser = null;

        if(userService.existUser(user.getEmail()))
        {
            realUser = userService.login(user.getEmail(),"un token ");
        } else {
            try {
                realUser = userService.signUp(user.getName(), user.getLastName(), user.getUserName(), user.getEmail(),user.getImage());

            } catch (SingUpException e) {
                realUser = null;
            }
        }
        return realUser;
    }

    //Intento login de lalo
    @POST
    @Path("signUpAndLogin2")
    @Consumes("application/json")
    @Produces("application/json")
    public UserTokenResponse signUp2(UserAuthorization userAuthorization){
        Credential credential = googleCredentialsService.create(userAuthorization.getAuthorizationCode());
        Userinfoplus userinfoplus = googleCredentialsService.getUserinfo(credential);
        GoogleOauthCredential googleOauthCredential = googleCredentialsService.get(userinfoplus.getId());
        User user = userService.signUpWithCredentials(userinfoplus, googleOauthCredential);
        UserToken token = userTokenService.findByUserId(user.getId());
        return new UserTokenResponse(token.getToken());
    }
    //Fin intento de login de lalo

    @GET
    @Path("login/{email}/{passwd}")
    @Produces("application/json")
    public User login(@PathParam("email") final String email, @PathParam("passwd") final String token) {
        /*try {
            userService.signUp("ejemplo", "ejemplo", "ejemplo", email,"ejemplo");
        } catch (SingUpException e) {
            e.printStackTrace();
        }*/
        return userService.login(email,token);
    }

    @GET
    @Path("getUser/{id}")
    @Produces("application/json")
    public User getUser(@PathParam("id") final Integer id) {
        return userService.getUser(id);
    }

    @POST
    @Path("{id}/addRoute/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addRoute(@PathParam("id") final Integer id, Route route)
    {
        Response response;
        try {
            User user = userService.getUser(id);
            userService.addRoute(user,route);
            response = Response.ok().tag("La ruta fue agregada correctamente").build();
        } catch (SubiQueTeLlevoException e) {
            response = Response.serverError().tag("No se pudo agregar la ruta").build();
        }

        return response;
    }

}
