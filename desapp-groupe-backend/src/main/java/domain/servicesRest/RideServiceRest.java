package domain.servicesRest;

import domain.RideRequest;
import domain.Route;
import domain.RoutePoint;
import domain.User;
import domain.services.RideService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/ride")
@Service
public class RideServiceRest
{
    RideService rideService;

    public RideServiceRest(RideService rideService) {
        this.rideService = rideService;
    }

    @POST
    @Path("requestRide")
    @Consumes("application/json")
    public Response requestRide(final RideRequest rideRequest)
    {
        Response response;
        rideService.requestRide(rideRequest);
        response = Response.ok().tag("Se solicitó el viaje al conductor").build();
        return response;
    }
}
