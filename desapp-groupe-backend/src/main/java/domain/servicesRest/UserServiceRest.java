package domain.servicesRest;

import domain.User;
import domain.services.UserService;

//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;

//@Path("/user")
public class UserServiceRest {

    UserService userService;

    public UserServiceRest(UserService userService) {
        this.userService = userService;
    }

//    @POST
//    @Path("/{from}")
//    @Produces("application/json")
    public User singUp(){
        return null;
    }

}
