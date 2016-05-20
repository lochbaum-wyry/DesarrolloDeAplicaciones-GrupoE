package domain.servicesRest;


import domain.User;
import domain.builders.UserBuilder;
import domain.services.UserService;
import domain.services.gaming_service.GamingService;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("gaming")
@Service
public class GamingServiceRest {

    GamingService gamingService;

    public GamingServiceRest(GamingService gamingService) {
        this.gamingService = gamingService;
    }


    @GET
    @Path("ranking")
    @Produces("application/json")
    public List<User> ranking(){

        return new ArrayList<User>();
    }
}
