package domain;

import domain.builders.RoutePointBuilder;
import domain.builders.RideBuilder;
import domain.builders.TakenSeatBuilder;
import domain.builders.UserBuilder;
import org.junit.Assert;
import org.junit.Test;

public class TakenSeatTest {

    @Test
    public void test_setAndGetRide(){
        TakenSeat takenSeat = TakenSeatBuilder.aTakenSeat().build();

        User driver = UserBuilder.aUser().build();
        Ride ride = RideBuilder.aRide().withDriver(driver).build();
        takenSeat.setRide(ride);

        Assert.assertEquals(takenSeat.getRide(),ride);
    }

    @Test
    public void test_setAndGetPassenger(){
        TakenSeat takenSeat = TakenSeatBuilder.aTakenSeat().build();

        User user = UserBuilder.aUser().build();
        takenSeat.setPassenger(user);

        Assert.assertEquals(takenSeat.getPassenger(),user);
    }

    @Test
    public void test_setAndGetBoardingAt(){
        TakenSeat takenSeat = TakenSeatBuilder.aTakenSeat().build();

        RoutePoint routePoint = RoutePointBuilder.aRoutePoint().build();
        takenSeat.setBoardingAt(routePoint);

        Assert.assertEquals(takenSeat.getBoardingAt(),routePoint);
    }

    @Test
    public void test_setAndGetGetoffAt(){
        TakenSeat takenSeat = TakenSeatBuilder.aTakenSeat().build();

        RoutePoint routePoint = RoutePointBuilder.aRoutePoint().build();
        takenSeat.setGetOffAt(routePoint);

        Assert.assertEquals(takenSeat.getGetOffAt(),routePoint);
    }

}
