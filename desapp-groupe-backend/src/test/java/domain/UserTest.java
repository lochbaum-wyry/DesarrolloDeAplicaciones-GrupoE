package domain;

import domain.*;
import domain.builders.RideRequestBuilder;
import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.lang.*;
import java.util.List;


public class UserTest
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
        User driver = UserBuilder.aUser().build();

        driver.addRideRequest(rideRequest);
        driver.acceptRideRequest(rideRequest);

        Assert.assertEquals(rideRequest.getStatus(), RequestStatus.Accepted);

    }

    @Test
    public void test_acceptRideRequest_passengerIsIncludedInARideCorrespondingToRequestDetails()
    {
        User driver = UserBuilder.aUser().build();

        User passenger = UserBuilder.aUser().build();
        Route route = RouteBuilder.aRoute().withLocationAt(100.0,100.0).withLocationAt(200.0,200.0).build();
        DateTime date = new DateTime();
        Location boardAt = route.getLocations().get(0);
        Location getOffAt = route.getLocations().get( route.getLocations().size()-1 );

        RideRequest rideRequest = RideRequestBuilder.aRideRequest().withPassenger(passenger).withBoardingAt(boardAt).withGetoffAt(getOffAt).withDate(date).build();

        passenger.requestRide(driver, rideRequest);

        driver.addRideRequest(rideRequest);
        driver.acceptRideRequest(rideRequest);

        Assert.assertTrue( this.aRideContainsPassengerMatchingRideRequestDetails(driver.getRides() , rideRequest) );
    }

    public Boolean aRideContainsPassengerMatchingRideRequestDetails(List<Ride> rideList , RideRequest rideRequest)
    {
        return rideList.stream().anyMatch(ride -> {
            return ride.getDate() == rideRequest.getDate() &&
                    ride.getRoute() == rideRequest.getRoute() &&
                    ride.seatTakenBy(rideRequest.getPassenger()).isPresent();
        });
    }
}