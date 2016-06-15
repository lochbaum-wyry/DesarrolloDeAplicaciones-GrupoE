package domain.servicesRest;

import domain.RideRequest;
import domain.Route;
import domain.RoutePoint;
import domain.User;
import domain.services.RideService;
import domain.services.RouteService;
import domain.services.UserService;
import domain.servicesRest.daos.RideRequestDTO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/ride")
@Service
public class RideServiceRest
{
    UserService userService;
    RideService rideService;
    RouteService routeService;

    public RideServiceRest(RideService rideService, UserService userService, RouteService routeService)
    {
        this.userService = userService;
        this.rideService = rideService;
        this.routeService = routeService;
    }


    @GET
    @Path("pendingRequestsBy/{requesterId}")
    @Produces("application/json")
    public List<RideRequest> getPendingRequestsBy(@PathParam("requesterId") final int requesterId)
    {
        User requester = userService.getUser(requesterId);
        List<RideRequest> list = rideService.getPendingRequestsBy(requester);
        return list;
    }
//
//    @GET
//    @Path("pendingRequestsFor/{driverId}")
//    @Produces("application/json")
//    public List<RideRequest> getPendingRequestsFor(@PathParam("driverId") final int driverId)
//    {
//        User driver = userService.getUser(driverId);
//        return rideService.getPendingRequestsFor(driver);
//    }

    @POST
    @Path("requestRide")
    @Consumes("application/json")
    public Response requestRide(final RideRequestDTO rideRequestDTO)
    {
        Response response;
        RideRequest rideRequest = rideRequestFromDTO(rideRequestDTO);
        rideService.requestRide(rideRequest);
        response = Response.ok().tag("Se solicitó el viaje al conductor").build();
        return response;
    }

    private RideRequest rideRequestFromDTO(RideRequestDTO rideRequestDTO)
    {
        User driver = userService.getUserRepository().findById(rideRequestDTO.getDriverId());
        User requester = userService.getUserRepository().findById(rideRequestDTO.getDriverId());
        DateTime date = rideRequestDTO.getDate();
        Route route = routeService.getRouteRepository().findById(rideRequestDTO.getRouteId());
        RoutePoint boardingAt = routeService.getRoutePointRepository().findById(rideRequestDTO.getBoardingAtId());
        RoutePoint getOffAt = routeService.getRoutePointRepository().findById(rideRequestDTO.getGetOffAtId());

        return new RideRequest(requester, driver, date, route, boardingAt, getOffAt);
    }


}
