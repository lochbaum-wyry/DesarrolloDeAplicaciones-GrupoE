package domain.service_tests;


import domain.*;
import domain.exceptions.NoSeatsAvailableException;
import domain.repositories.*;
import domain.services.RideService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RideServiceTest extends AbstractServiceTest
{
    @Autowired
    public RideService rideService ;

    @Autowired
    public RideRepository rideRepo ;

    @Autowired
    public RouteRepository routeRepo ;

    @Autowired
    public UserRepository userRepo ;

    @Autowired
    public VehicleRepository vehicleRepo ;

    @Autowired
    public RideRequestRepository rideRequestRepo ;

    @Test
    public void test_requestRide_aNewRideRequestIsCreated()
    {
        Route route = aCommonRouteWithLocations(2,50,0);
        User requester = aPassenger();
        User driver = aDriver();
        DateTime date = new DateTime(2016,6,1,0,0,0);
        RoutePoint board = route.getRoutePoints().get(0);
        RoutePoint getOff = route.getRoutePoints().get(1);

        userRepo.save(requester);
        userRepo.save(driver);
        routeRepo.save(route);

        RideRequest rideRequest = rideService.requestRide(requester, driver, date, route, board , getOff);

        int foundRequests = rideRequestRepo.findByExample(rideRequest).size();
        assertEquals(1, foundRequests);
    }


    @Test
    public void test_rejectRideRequest_whenRejectedThenRequestIsInRejectedStatus()
    {
        RideRequest rideRequest = aPersistedRideRequest();
        rideService.rejectRideRequest(rideRequest);
        RideRequest foundRequest = rideRequestRepo.findByExample(rideRequest).get(0);
        assertEquals(RequestStatus.Rejected, foundRequest.getStatus());
    }

    @Test
    public void test_acceptRideRequest_whenAcceptedThenRequestIsInAcceptedStatus()
    {
        RideRequest rideRequest = aPersistedRideRequest();
        try {
            rideService.acceptRideRequest(rideRequest);
            RideRequest foundRequest = rideRequestRepo.findByExample(rideRequest).get(0);
            assertEquals(RequestStatus.Accepted, foundRequest.getStatus());
        } catch (NoSeatsAvailableException e){
            fail("Expected request to be accepted but NoSeatsAvailableException is thrown");
        }
    }

    @Test
    public void test_acceptRideRequest_whenAcceptedThenPassengerHasTakenASeatInTheResultingRide()
    {
        Ride ride ;
        RideRequest rideRequest = aPersistedRideRequest();
        User requester = rideRequest.getRequester();

        try {
            ride = rideService.acceptRideRequest(rideRequest);

            assertTrue(ride.isPassenger(requester));
        } catch (NoSeatsAvailableException e){
            fail("Expected request to be accepted but NoSeatsAvailableException is thrown");
        }
    }

    @Test
    public void test_acceptRideRequest_ifDriverHasntARideSuitingTheRequestTheRideIsCreated()
    {
        boolean rideExists ;

        RideRequest rideRequest = aPersistedRideRequest();

        rideExists = rideService.getRideOfDriverSuitableForRideRequest(rideRequest).isPresent();
        assertFalse(rideExists);

        try {
            rideService.acceptRideRequest(rideRequest);
            rideExists = rideService.getRideOfDriverSuitableForRideRequest(rideRequest).isPresent();
            assertTrue(rideExists);

        } catch (NoSeatsAvailableException e){
            fail("Expected request to be accepted but NoSeatsAvailableException is thrown");
        }
    }

    @Test
    public void test_acceptRideRequest_ifDriverHasARideSuitingTheRequestThenPassengerIsAddedToThatRide()
    {
        Ride ride = null ;
        RideRequest rideRequest1 = aPersistedRideRequest();

        RideRequest rideRequest2 = aPersistedRideRequest();
        rideRequest2.setDriver(rideRequest1.getDriver());
        rideRequest2.setRoute(rideRequest1.getRoute());
        rideRequestRepo.update(rideRequest2);

        try {
            ride = rideService.acceptRideRequest(rideRequest1);
        } catch (NoSeatsAvailableException e)
        {
            fail("Expected request to be accepted and get a ride but NoSeatsAvailableException is thrown");
        }

        try {
            rideService.acceptRideRequest(rideRequest2);

            User rideReq2Requester = rideRequest2.getRequester();
            assertTrue(ride.isPassenger(rideReq2Requester));

        } catch (NoSeatsAvailableException e){
            fail("Expected request to be accepted but NoSeatsAvailableException is thrown");
        }
    }





    protected RideRequest aPersistedRideRequest()
    {
        Route route = aCommonRouteWithLocations(2,50,0);
        User requester = aPassenger();
        User driver = aDriver();
        DateTime date = new DateTime(2016,6,1,0,0,0);
        RoutePoint board = route.getRoutePoints().get(0);
        RoutePoint getOff = route.getRoutePoints().get(1);

        userRepo.save(requester);
        userRepo.save(driver);
        routeRepo.save(route);

        return rideService.requestRide(requester, driver, date, route, board , getOff);
    }



//    @Test
//    public void test_getRidesAsDriver_returnsAListContainingAllTheRidesWhereUserIsDriver()
//    {
//
//        User user = UserBuilder.aUser().build();
//
//        Ride ride1 = mock(Ride.class);
//        when(ride1.isDriver(user)).thenReturn(true);
//
//        Ride ride2 = mock(Ride.class);
//        when(ride2.isDriver(user)).thenReturn(true);
//
//        Ride ride3 = mock(Ride.class);
//        when(ride3.isDriver(user)).thenReturn(false);
//
//        user.addRide(ride1);
//        user.addRide(ride2);
//        user.addRide(ride3);
//
//        user.getRidesAsDriver().stream().forEach(
//                ride -> Assert.assertTrue(ride.isDriver(user))
//        );
//
//        Assert.assertFalse(user.getRidesAsDriver().contains(ride3));
//
//    }

}
