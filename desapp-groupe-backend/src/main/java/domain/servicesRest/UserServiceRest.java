package domain.servicesRest;

import domain.LatLng;
import domain.Schedule;
import domain.User;
import domain.builders.UserBuilder;
import domain.exceptions.SingUpException;
import domain.exceptions.SubiQueTeLlevoException;
import domain.services.UserService;
import org.springframework.stereotype.Service;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
@Service
public class UserServiceRest {


    UserService userService;

    public UserServiceRest(UserService userService) {
        this.userService = userService;
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

        if(userService.existUser(user.getEmail())){
            realUser = userService.login(user.getEmail(),"un token ");
        }
        else {
            try {
                realUser = userService.signUp(user.getName(), user.getLastName(), user.getUserName(), user.getEmail());

            } catch (SingUpException e) {
                realUser = null;
            }
        }
        return realUser;
    }

    @GET
    @Path("login/{email}/{passwd}")
    @Produces("application/json")
    public User login(@PathParam("date") final String email, @PathParam("passwd") final String token) {
        try {
            userService.signUp("ejemplo", "ejemplo", "ejemplo", email);
        } catch (SingUpException e) {
            e.printStackTrace();
        }
        return userService.login(email,token);
    }

    @POST
    @Path("addRoute")
    @Consumes("application/json")
    public Response addRoute(User user, List<LatLng> points, Float distanceInKms, Float fixedCosts, List<Schedule> schedules)
    {
        Response response;
        try {
            userService.addRoute(user,points,distanceInKms,fixedCosts,schedules);
            response = Response.ok().tag("La ruta fue agregada correctamente").build();
        } catch (SubiQueTeLlevoException e) {
            response = Response.serverError().tag("No se pudo agregar la ruta").build();
        }

        return response;
    }

}
