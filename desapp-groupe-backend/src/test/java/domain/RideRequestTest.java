package domain;

import domain.builders.RoutePointBuilder;
import domain.builders.RideRequestBuilder;
import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;
import org.joda.time.DateTime;
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

    @Test
    public void test_setAndGetRequester(){
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        User user = UserBuilder.aUser().build();
        rideRequest.setRequester(user);

        Assert.assertEquals(rideRequest.getRequester(),user);
    }

    @Test
    public void test_setAndGetDriver(){
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        User user = UserBuilder.aUser().build();
        rideRequest.setDriver(user);

        Assert.assertEquals(rideRequest.getDriver(),user);
    }

    @Test
    public void test_setAndGetRoute(){
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        Route route = RouteBuilder.aRoute().build();
        rideRequest.setRoute(route);

        Assert.assertEquals(rideRequest.getRoute(),route);
    }

    @Test
    public void test_setAndGetDate(){
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        DateTime date = new DateTime(2,3,4,5,6);
        rideRequest.setDate(date);

        Assert.assertEquals(rideRequest.getDate(),date);
    }

    @Test
    public void test_setAndGetDateRequested(){
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        DateTime date = new DateTime(2,3,4,5,6);
        rideRequest.setDateRequested(date);

        Assert.assertEquals(rideRequest.getDateRequested(),date);
    }

    @Test
    public void test_setAndGetBoardingAt(){
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        RoutePoint routePoint = RoutePointBuilder.aRoutePoint().build();
        rideRequest.setBoardingAt(routePoint);

        Assert.assertEquals(rideRequest.getBoardingAt(),routePoint);
    }

    @Test
    public void test_setAndGetGetOffAt(){
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        RoutePoint routePoint = RoutePointBuilder.aRoutePoint().build();
        rideRequest.setGetOffAt(routePoint);

        Assert.assertEquals(rideRequest.getGetOffAt(),routePoint);
    }

    @Test
    public void test_setAndGetStatus(){
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        RequestStatus requestStatus = RequestStatus.Accepted;
        rideRequest.setStatus(requestStatus);

        Assert.assertEquals(rideRequest.getStatus(),RequestStatus.Accepted);
    }

}
