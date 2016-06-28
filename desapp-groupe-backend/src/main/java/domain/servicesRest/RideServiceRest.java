package domain.servicesRest;

import domain.*;
import domain.exceptions.NoSeatsAvailableException;
import domain.services.RatingService;
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
    RatingService ratingService;

    public RideServiceRest(RideService rideService, UserService userService, RouteService routeService,RatingService ratingService)
    {
        this.userService = userService;
        this.rideService = rideService;
        this.routeService = routeService;
        this.ratingService = ratingService;
    }

    public RideServiceRest(){}


    @GET
    @Path("pendingRequestsBy/{requesterId}")
    @Produces("application/json")
    public List<RideRequest> getPendingRequestsBy(@PathParam("requesterId") final int requesterId)
    {
        User requester = userService.getUser(requesterId);
        List<RideRequest> list = rideService.getPendingRequestsBy(requester);
        return list;
    }

    @GET
    @Path("getRateablesRides/{userId}")
    @Produces("application/json")
    public List<Ride> getRateablesRides(@PathParam("userId") final int userId){
        List<Ride> rides = rideService.getRateablesRides(userId);
        return rides;
    }

    @GET
    @Path("getRateablesFrom/{rideId}/{userId}")
    @Produces("application/json")
    public Ride getRateablesFrom(@PathParam("rideId") final int rideId,@PathParam("userId") final int userId){
        return rideService.getRateablesFrom(rideId,userId);
    }

    @GET
    @Path("pendingRequestsFor/{driverId}")
    @Produces("application/json")
    public List<RideRequest> getPendingRequestsFor(@PathParam("driverId") final int driverId)
    {
        User driver = userService.getUser(driverId);
        List<RideRequest> list = rideService.getPendingRequestsFor(driver);
        return list;
    }

    @GET
    @Path("cancelRequest/{rideRequestId}")
    @Produces("application/json")
    public Response cancelRideRequests(@PathParam("rideRequestId") final int rideRequestId)
    {
        rideService.cancelRideRequest(rideRequestId);
        Response response = Response.ok().tag("cancelled_request_msg").build();
        return response;
    }

    @GET
    @Path("rejectRequest/{rideRequestId}")
    @Produces("application/json")
    public Response rejectRideRequests(@PathParam("rideRequestId") final int rideRequestId)
    {
        RideRequest rideRequest = rideService.getRideRequestRepository().findById(rideRequestId);
        rideService.rejectRideRequest(rideRequest);
        Response response = Response.ok().tag("rejected_request_msg").build();
        return response;
    }

    @GET
    @Path("acceptRequest/{rideRequestId}")
    @Produces("application/json")
    public Response acceptRideRequests(@PathParam("rideRequestId") final int rideRequestId)
    {
        Response response;
        RideRequest rideRequest = rideService.getRideRequestRepository().findById(rideRequestId);
        try {
            rideService.acceptRideRequest(rideRequest);
            response = Response.ok().tag("accepted_request_msg").build();
        } catch (NoSeatsAvailableException e) {
            response = Response.serverError().tag(e.getMessage()).build();
        }
        return response;
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
        response = Response.ok().tag("request_sent_msg").build();
        return response;
    }

    @POST
    @Path("rate")
    @Consumes("application/json")
    public Response rate(Rate rate){
        try
        {
            switch (rate.getRateType()) {
                case Driving:
                    ratingService.rateDriverOfRide(rate.getRater(),rate.getRide(),rate.getRateValue(),rate.getComment());
                case Accompany:
                    ratingService.ratePassengerOfRide(rate.getRater(),rate.getRatedUser(),rate.getRide(),rate.getRateValue(),rate.getComment());
                case CarState:
                    ratingService.rateVehicleOfRide(rate.getRater(),rate.getRide(),rate.getRateValue(),rate.getComment());
            }
            return Response.ok().tag("Se califico correctamente").build();
        }
        catch (Exception e){
          return Response.serverError().build();
        }
    }

    private RideRequest rideRequestFromDTO(RideRequestDTO rideRequestDTO)
    {
        User driver = userService.getUserRepository().findById(rideRequestDTO.getDriverId());
        User requester = userService.getUserRepository().findById(rideRequestDTO.getRequesterId());
        DateTime date = rideRequestDTO.getDate();
        Route route = routeService.getRouteRepository().findById(rideRequestDTO.getRouteId());
        RoutePoint boardingAt = routeService.getRoutePointRepository().findById(rideRequestDTO.getBoardingAtId());
        RoutePoint getOffAt = routeService.getRoutePointRepository().findById(rideRequestDTO.getGetOffAtId());

        return new RideRequest(requester, driver, date, route, boardingAt, getOffAt);
    }


}
