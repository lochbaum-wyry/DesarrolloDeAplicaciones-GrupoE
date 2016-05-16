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
    @Path("signUp")
    @Consumes("application/json")
    public Response signUp(User user){
        Response response;
        try {
            userService.signUp(user.getName(),user.getLastName(),user.getUserName(),user.getEmail());
            response = Response.ok().tag("El usuario fue creado").build();
        } catch (SingUpException e) {
            response = Response.serverError().tag("No se pudo crear el usuario").build();
        }

        return response;
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
