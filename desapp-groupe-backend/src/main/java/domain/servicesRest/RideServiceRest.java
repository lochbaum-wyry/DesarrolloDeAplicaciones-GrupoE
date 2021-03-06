package domain.servicesRest;

import domain.*;
import domain.exceptions.NoSeatsAvailableException;
import domain.notifications.RideAcceptedNotification;
import domain.notifications.RideRejectedNotification;
import domain.notifications.RideRequestedNotification;
import domain.services.*;
import domain.servicesRest.daos.RateDTO;
import domain.servicesRest.daos.RideRequestDTO;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
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
    private NotificationService notificationService;

    public RideServiceRest(RideService rideService, UserService userService, RouteService routeService,RatingService ratingService, NotificationService notificationService)
    {
        this.userService = userService;
        this.rideService = rideService;
        this.routeService = routeService;
        this.ratingService = ratingService;
        this.notificationService = notificationService;
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
    @Path("awaitingRidesAsDriver/{userId}")
    @Produces("application/json")
    public List<Ride> awaitingRidesAsDriver(@PathParam("userId") final int userId){
        List<Ride> rides = rideService.awaitingRidesAsDriver(userId);
        return rides;
    }

    @GET
    @Path("awaitingRidesAsPassenger/{userId}")
    @Produces("application/json")
    public List<Ride> awaitingRidesAsPassenger(@PathParam("userId") final int userId){
        List<Ride> rides = rideService.awaitingRidesAsPassenger(userId);
        return rides;
    }

    @GET
    @Path("getFutureRides/{userId}")
    @Produces("application/json")
    public List<Ride> getFutureRides(@PathParam("userId") final int userId){
        List<Ride> rides = rideService.getFutureRides(userId);
        return rides;
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

        Notification notification = new RideRejectedNotification(rideRequest.getDriver(), rideRequest);
        notificationService.create(notification);

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
            Ride ride = rideService.acceptRideRequest(rideRequest);

            Notification notification = new RideAcceptedNotification(rideRequest.getRequester(), ride);
            notificationService.create(notification);

            response = Response.ok().tag("accepted_request_msg").build();

        } catch (NoSeatsAvailableException e)
        {
            response = Response.serverError().tag(e.getMessage()).build();
            e.printStackTrace();
        } catch (DataAccessException e)
        {
            response = Response.serverError().tag(e.getMessage()).build();
            e.printStackTrace();
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

        Notification notification = new RideRequestedNotification(rideRequest.getDriver(), rideRequest);
        notificationService.create(notification);


        response = Response.ok().tag("request_sent_msg").build();
        return response;
    }

    @POST
    @Path("rate")
    @Consumes("application/json")
    @Produces("application/json")
    public Response rate(final RateDTO rateDTO){

        Rate rate = rateFromDTO(rateDTO);
        try
        {
            ratingService.rate(rate);
            return Response.ok().tag("Se califico correctamente").build();
        }
        catch (Exception e){
          return Response.serverError().tag(e.getMessage()).build();
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

    private Rate rateFromDTO(RateDTO rateDTO)
    {
        User rater = userService.getUserRepository().findById(rateDTO.getRaterId());
        User ratedUser = userService.getUserRepository().findById(rateDTO.getRatedUserId());
        Ride ride = rideService.getRideRepository().findById(rateDTO.getRideId());
        Vehicle vehicle = userService.getVehicleRepository().findById(rateDTO.getVehicleId());
        RateType rateType = rateDTO.getRateType();
        RateValue rateValue = rateDTO.getRateValue();
        String comment = rateDTO.getComment();

        Rate rate = new Rate(rater, ratedUser, ride, rateType, rateValue, comment);
        rate.setVehicle(vehicle);
        return rate;

    }


}
