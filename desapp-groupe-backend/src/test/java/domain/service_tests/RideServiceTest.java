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
        Location board = route.getLocations().get(0);
        Location getOff = route.getLocations().get(1);

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



    protected RideRequest aPersistedRideRequest()
    {
        Route route = aCommonRouteWithLocations(2,50,0);
        User requester = aPassenger();
        User driver = aDriver();
        DateTime date = new DateTime(2016,6,1,0,0,0);
        Location board = route.getLocations().get(0);
        Location getOff = route.getLocations().get(1);

        userRepo.save(requester);
        userRepo.save(driver);
        routeRepo.save(route);

        return rideService.requestRide(requester, driver, date, route, board , getOff);
    }


//    @Test
//    public void test_fromRideRequest_createdRideSuitsRideRequestDetails()
//    {
//        User driverMario = driverWithVehicle(2);
//        Route route = RouteBuilder.aRoute()
//                .withLocationAt(34.5,21.4)
//                .withLocationAt(59.3,100.2)
//                .withLocationAt(56.4,87.6)
//                .build();
//
//        RideRequest rideRequest = RideRequestBuilder.aRideRequest()
//                .withRoute(route)
//                .withDate(new DateTime(2016,05,16,12,0,0))
//                .build();
//
//        Ride received = Ride.newFromRideRequest(driverMario,rideRequest);
//
//        Assert.assertTrue(received.suitsRideRequest(rideRequest));
//
//    }
//
//    @Test
//    public void test_requestRide_addsRideRequestToDriverRideRequests()
//    {
//        User passenger = UserBuilder.aUser().build();
//        User driver = UserBuilder.aUser().build();
//        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();
//
//        passenger.requestRide(driver, rideRequest);
//
//        Assert.assertTrue(driver.getRideRequests().contains(rideRequest));
//    }
//
//    @Test
//    public void test_requestRide_addsRideRequestToPassengerRequestedRides()
//    {
//        User passenger = UserBuilder.aUser().build();
//        User driver = UserBuilder.aUser().build();
//        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();
//
//        passenger.requestRide(driver,rideRequest);
//
//        Assert.assertTrue(driver.getRideRequests().contains(rideRequest));
//    }
//
//    @Test
//    public void test_acceptRideRequest_setsRideRequestStatusToAccepted()
//    {
//        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();
//        User driver = driverWithVehicle(2);
//        domain.services.System system = SystemBuilder.aSystem().build();
//        driver.setSystem(system);
//        driver.addRideRequest(rideRequest);
//
//        try {
//            driver.acceptRideRequest(rideRequest);
//            Assert.assertEquals(rideRequest.getStatus(), RequestStatus.Accepted);
//        } catch (NoSeatsAvailableException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void test_acceptRideRequest_passengerIsIncludedInARideCorrespondingToRequestDetails()
//    {
//        User driver = driverWithVehicle(2);
//        System system = SystemBuilder.aSystem().build();
//        driver.setSystem(system);
//
//        User passenger = UserBuilder.aUser().build();
//
//        Route route = RouteBuilder.aRoute().withLocationAt(100.0,100.0).withLocationAt(200.0,200.0).build();
//
//        Location boardAt = route.getLocations().get(0);
//        Location getOffAt = route.getLocations().get( route.getLocations().size()-1 );
//
//        RideRequest rideRequest = RideRequestBuilder.aRideRequest()
//                .withPassenger(passenger)
//                .withBoardingAt(boardAt)
//                .withGetoffAt(getOffAt)
//                .withDate( new DateTime() ).build();
//
//        passenger.requestRide(driver, rideRequest);
//
//        try {
//
//            driver.acceptRideRequest(rideRequest);
//            Assert.assertTrue( this.aRideContainsPassengerMatchingRideRequestDetails(driver.getRides() , rideRequest) );
//
//        } catch (NoSeatsAvailableException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public Boolean aRideContainsPassengerMatchingRideRequestDetails(List<Ride> rideList , RideRequest rideRequest)
//    {
//        User passenger = rideRequest.getRequester();
//        return rideList.stream().anyMatch(ride -> {
//            return ride.suitsRideRequest(rideRequest) && ride.seatTakenBy(passenger).isPresent();
//        });
//    }
//
//
//
//    @Test(expected = NoSeatsAvailableException.class)
//    public void test_acceptRideRequest_NoSeatsAvailableExceptionIsThrownIfThereIsNoSeatsAvailbleForTheRequestedRide()
//            throws NoSeatsAvailableException {
//
//        System system = SystemBuilder.aSystem().build();
//
//        User driver = driverWithVehicle(2);
//        driver.setSystem(system);
//
//        User seatOccupier = UserBuilder.aUser().build();
//        User failerPassenger = UserBuilder.aUser().build();
//
//        Route route = RouteBuilder.aRoute().withLocationAt(100.0,100.0).withLocationAt(200.0,200.0).build();
//        Location boardAt = route.getLocations().get(0);
//        Location getOffAt = route.getLocations().get( route.getLocations().size()-1 );
//
//        DateTime date = new DateTime();
//        RideRequest rideRequestOccupier = RideRequestBuilder.aRideRequest()
//                .withPassenger(seatOccupier)
//                .withBoardingAt(boardAt)
//                .withGetoffAt(getOffAt)
//                .withDate(date)
//                .withRoute(route)
//                .build();
//
//        RideRequest rideRequestFailer = RideRequestBuilder.aRideRequest()
//                .withPassenger(failerPassenger)
//                .withBoardingAt(boardAt)
//                .withGetoffAt(getOffAt)
//                .withDate( date )
//                .withRoute(route)
//                .build();
//
//        driver.addRoute(route);
//
//        seatOccupier.requestRide(driver, rideRequestOccupier);
//        seatOccupier.requestRide(driver, rideRequestFailer);
//
//        driver.acceptRideRequest(rideRequestOccupier);
//        driver.acceptRideRequest(rideRequestFailer);
//
//    }
//
//    @Test
//    public void test_rejectRideRequest_rideRequestIsSetToRejectedStatus()
//    {
//        User driver = driverWithVehicle(2);
//        User passenger = UserBuilder.aUser().build();
//        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();
//
//        passenger.requestRide(driver, rideRequest);
//        driver.rejectRideRequest(rideRequest);
//
//        Assert.assertEquals(RequestStatus.Rejected, rideRequest.getStatus());
//    }
//
//    @Test
//    public void test_rejectRideRequest_rideRequestIsRemovedFromDriversRideRequests()
//    {
//        User driver = driverWithVehicle(2);
//        User passenger = UserBuilder.aUser().build();
//        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();
//
//        passenger.requestRide(driver, rideRequest);
//        driver.rejectRideRequest(rideRequest);
//
//        Assert.assertFalse(driver.getRideRequests().contains(rideRequest));
//
//    }

//
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
