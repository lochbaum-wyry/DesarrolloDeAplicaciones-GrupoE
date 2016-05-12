package domain.servicesRest;

import domain.User;
import domain.exceptions.SingUpException;
import domain.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@Service
public class UserServiceRest {


    UserService userService;

    public UserServiceRest(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/user")
    @Produces("application/json")
    public User user(){
        return new User("asd4","asd3","asd2","asd");
    }

    @GET
    @Path("/list")
    @Produces("application/json")
    public List<User> listUser(){
        return userService.getUserRepository().findAll();
    }

    @POST
    @Path("/singUp")
    @Consumes("application/json")
    public Response signUp(User user){
        Response response;
        try {
            userService.singUp(user.getName(),user.getLastName(),user.getUserName(),user.getEmail());
            response = Response.ok().tag("El usuario fue creado Correctamente").build();
        } catch (SingUpException e) {
            response = Response.serverError().tag("No se pudo crear el Usuario Correctamente").build();
        }

        return response;
    }






}
