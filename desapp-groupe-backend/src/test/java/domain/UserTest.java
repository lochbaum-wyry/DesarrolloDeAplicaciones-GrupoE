package domain;

import domain.builders.RideRequestBuilder;
import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;
import domain.exceptions.NoSeatsAvailableException;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class UserTest extends AbstractDomainTest
{
    @Test
    public void test_requestRide_addsRideRequestToDriverRideRequests()
    {
        User passenger = UserBuilder.aUser().build();
        User driver = UserBuilder.aUser().build();
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        passenger.requestRide(driver, rideRequest);

        Assert.assertTrue(driver.getRideRequests().contains(rideRequest));
    }

    @Test
    public void test_requestRide_addsRideRequestToPassengerRequestedRides()
    {
        User passenger = UserBuilder.aUser().build();
        User driver = UserBuilder.aUser().build();
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        passenger.requestRide(driver,rideRequest);

        Assert.assertTrue(driver.getRideRequests().contains(rideRequest));
    }

    @Test
    public void test_acceptRideRequest_setsRideRequestStatusToAccepted()
    {
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();
        User driver = driverWithVehicle(2);

        driver.addRideRequest(rideRequest);

        try {
            driver.acceptRideRequest(rideRequest);
            Assert.assertEquals(rideRequest.getStatus(), RequestStatus.Accepted);
        } catch (NoSeatsAvailableException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_acceptRideRequest_passengerIsIncludedInARideCorrespondingToRequestDetails()
    {
        User driver = driverWithVehicle(2);

        User passenger = UserBuilder.aUser().build();
        Route route = RouteBuilder.aRoute().withLocationAt(100.0,100.0).withLocationAt(200.0,200.0).build();

        Location boardAt = route.getLocations().get(0);
        Location getOffAt = route.getLocations().get( route.getLocations().size()-1 );

        RideRequest rideRequest = RideRequestBuilder.aRideRequest()
                                                        .withPassenger(passenger)
                                                        .withBoardingAt(boardAt)
                                                        .withGetoffAt(getOffAt)
                                                        .withDate( new DateTime() ).build();

        passenger.requestRide(driver, rideRequest);

        try {

            driver.acceptRideRequest(rideRequest);
            Assert.assertTrue( this.aRideContainsPassengerMatchingRideRequestDetails(driver.getRides() , rideRequest) );

        } catch (NoSeatsAvailableException e) {
            e.printStackTrace();
        }

    }

    public Boolean aRideContainsPassengerMatchingRideRequestDetails(List<Ride> rideList , RideRequest rideRequest)
    {
        User passenger = rideRequest.getPassenger();
        return rideList.stream().anyMatch(ride -> {
            return ride.suitsRideRequest(rideRequest) && ride.seatTakenBy(passenger).isPresent();
        });
    }



    @Test(expected = NoSeatsAvailableException.class)
    public void test_acceptRideRequest_NoSeatsAvailableExceptionIsThrownIfThereIsNoSeatsAvailbleForTheRequestedRide()
            throws NoSeatsAvailableException {

        User driver = driverWithVehicle(2);

        User seatOccupier = UserBuilder.aUser().build();
        User failerPassenger = UserBuilder.aUser().build();

        Route route = RouteBuilder.aRoute().withLocationAt(100.0,100.0).withLocationAt(200.0,200.0).build();
        Location boardAt = route.getLocations().get(0);
        Location getOffAt = route.getLocations().get( route.getLocations().size()-1 );

        DateTime date = new DateTime();
        RideRequest rideRequestOccupier = RideRequestBuilder.aRideRequest()
                .withPassenger(seatOccupier)
                .withBoardingAt(boardAt)
                .withGetoffAt(getOffAt)
                .withDate(date)
                .withRoute(route)
                .build();

        RideRequest rideRequestFailer = RideRequestBuilder.aRideRequest()
                .withPassenger(failerPassenger)
                .withBoardingAt(boardAt)
                .withGetoffAt(getOffAt)
                .withDate( date )
                .withRoute(route)
                .build();

        driver.addRoute(route);

        seatOccupier.requestRide(driver, rideRequestOccupier);
        seatOccupier.requestRide(driver, rideRequestFailer);

        driver.acceptRideRequest(rideRequestOccupier);
        driver.acceptRideRequest(rideRequestFailer);

    }

    public void test_rejectRideRequest_rideRequestIsSetToRejectedStatus()
    {
        User driver = driverWithVehicle(2);
        User passenger = UserBuilder.aUser().build();
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        passenger.requestRide(driver, rideRequest);
        driver.rejectRideRequest(rideRequest);

        Assert.assertEquals(RequestStatus.Rejected, rideRequest.getStatus());
    }

    public void test_rejectRideRequest_rideRequestIsRemovedFromDriversRideRequests()
    {
        User driver = driverWithVehicle(2);
        User passenger = UserBuilder.aUser().build();
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        passenger.requestRide(driver, rideRequest);
        driver.rejectRideRequest(rideRequest);

        Assert.assertFalse(driver.getRideRequests().contains(rideRequest));

    }

}