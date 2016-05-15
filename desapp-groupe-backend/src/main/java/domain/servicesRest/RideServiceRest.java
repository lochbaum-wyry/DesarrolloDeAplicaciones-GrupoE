package domain.servicesRest;

import domain.Route;
import domain.RoutePoint;
import domain.User;
import domain.services.RideService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    @Path("/requestRide")
    @Consumes("application/json")
    public Response requestRide(final User requester, final User driver, final DateTime date, final Route route, final RoutePoint departure, RoutePoint arrival){
        Response response = null;
//        try {
            rideService.requestRide(requester, driver, date, route, departure, arrival);
            response = Response.ok().tag("Se solicitó el viaje al conductor").build();
//        } catch (SubiQueTeLlevoException e) {
//            response = Response.serverError().tag("No se pudo crear el usuario").build();
//        }

        return response;
    }
}
