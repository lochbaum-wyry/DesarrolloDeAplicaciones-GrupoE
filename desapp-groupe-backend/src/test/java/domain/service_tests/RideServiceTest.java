package domain.service_tests;


import domain.*;
import domain.exceptions.NoSeatsAvailableException;
import domain.repositories.*;
import domain.services.RideService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class RideServiceTest extends AbstractServiceTest
{
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

        RideRequest rideRequest2 = aPersistedRideRequestMatchingOtherRequest(rideRequest1);


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

    @Test (expected = NoSeatsAvailableException.class)
    public void test_acceptRideRequest_whenAllSeatsAreTakenInSectionThenThrowsNoSeatsAvailableException() throws NoSeatsAvailableException {
        Ride ride = null ;

        RideRequest rideRequest1 = aPersistedRideRequest();
        RideRequest rideRequest2 = aPersistedRideRequestMatchingOtherRequest(rideRequest1);
        RideRequest rideRequest3 = aPersistedRideRequestMatchingOtherRequest(rideRequest1);
        RideRequest rideRequest4 = aPersistedRideRequestMatchingOtherRequest(rideRequest1);

        rideService.acceptRideRequest(rideRequest1);
        rideService.acceptRideRequest(rideRequest2);
        rideService.acceptRideRequest(rideRequest3);
        rideService.acceptRideRequest(rideRequest4);

        List<Ride> rideList = rideRepo.findAll();
    }

    @Test
    public void test_acceptRideRequest_whenSeatsTakenDontOverlapWithRequestThenPassengerIsIncludedInRide() throws NoSeatsAvailableException
    {
        Ride ride = null ;

        RideRequest rideRequest1 = aPersistedRideRequest();
        RideRequest rideRequest2 = aPersistedRideRequestMatchingOtherRequest(rideRequest1);
        RideRequest rideRequest3 = aPersistedRideRequestMatchingOtherRequest(rideRequest1);
        RideRequest rideRequest4 = aPersistedRideRequestMatchingOtherRequest(rideRequest1);

        Route route = rideRequest4.getRoute();
        rideRequest4.setBoardingAt(route.getRoutePoints().get(1));
        rideRequest4.setGetOffAt(route.getRoutePoints().get(2));
        rideRequestRepo.update(rideRequest4);

        rideService.acceptRideRequest(rideRequest1);
        rideService.acceptRideRequest(rideRequest2);
        rideService.acceptRideRequest(rideRequest3);

        try {
            ride = rideService.acceptRideRequest(rideRequest4);
            assertTrue(ride.isPassenger(rideRequest4.getRequester()));

        } catch (NoSeatsAvailableException e)
        {
            fail();
        }
    }



}
