package domain;

import domain.builders.RideRequestBuilder;
import org.junit.Assert;
import org.junit.Test;

public class RideRequestTest {

    @Test
    public void testAceptedRideRequest(){

        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();;

        rideRequest.accept();

        RequestStatus  expected = RequestStatus.Accepted;
        RequestStatus received = rideRequest.getStatus();

        Assert.assertEquals(expected,received);
    }

    @Test
    public void testRejectRideRequest(){

        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();;

        rideRequest.reject();

        RequestStatus expected = RequestStatus.Rejected;
        RequestStatus received = rideRequest.getStatus();

        Assert.assertEquals(expected,received);
    }

}
