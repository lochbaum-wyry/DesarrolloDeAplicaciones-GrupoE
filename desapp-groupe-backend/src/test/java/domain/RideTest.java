package domain;

import domain.*;
import domain.builders.*;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class RideTest {

    @Test
    public void test_seatTakenBy()
    {
        Vehicle vehicle = VehicleBuilder.aVehicle().build();

        User passengerFede = UserBuilder.aUser().withName("Fede").build();
        User passengerJorge = UserBuilder.aUser().withName("Jorge").build();
        User passengerRoberto = UserBuilder.aUser().withName("Roberto").build();
        User driverMario = UserBuilder.aUser().withName("Mario").withVehicle(vehicle).build();

        Location boardingAt1 = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();
        Location getOffAt1 = LocationBuilder.aLocation().withLatitude(38.6).withLongitude(40.4).build();

        Location boardingAt2 = LocationBuilder.aLocation().withLatitude(70.3).withLongitude(60.4).build();
        Location getOffAt2 = LocationBuilder.aLocation().withLatitude(89.4).withLongitude(40.4).build();

        Location boardingAt3 = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(89.4).build();
        Location getOffAt3 = LocationBuilder.aLocation().withLatitude(38.6).withLongitude(150.5).build();


        TakenSeat takenSeatFede = TakenSeatBuilder.aTakenSeat().withPassenger(passengerFede).withboardingAtLocation(boardingAt1).withgetOffAtLocation(getOffAt1).build();

        TakenSeat takenSeatJorge = TakenSeatBuilder.aTakenSeat().withPassenger(passengerJorge).withboardingAtLocation(boardingAt2).withgetOffAtLocation(getOffAt2).build();

        TakenSeat takenSeatRoberto = TakenSeatBuilder.aTakenSeat().withPassenger(passengerRoberto).withboardingAtLocation(boardingAt3).withgetOffAtLocation(getOffAt3).build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario).withTakenSeatAt(takenSeatFede).withTakenSeatAt(takenSeatJorge).withTakenSeatAt(takenSeatRoberto).build();

        TakenSeat takenSeatResult = ride.seatTakenBy(passengerFede).get();

        Assert.assertEquals(takenSeatResult,takenSeatFede);
    }

    @Test
    public void test_fromRideRequest_createdRideSuitsRideRequestDetails()
    {
        Vehicle vehicle = VehicleBuilder.aVehicle().build();
        User driverMario = UserBuilder.aUser().withName("Mario").withVehicle(vehicle).build();
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        RideRequest rideRequest = RideRequestBuilder.aRideRequest().withRoute(route).withDate(new DateTime(2016,05,16,12,0,0)).build();

        Ride received = Ride.fromRideRequest(driverMario,rideRequest);

        Assert.assertTrue(received.suitsRideRequest(rideRequest));

    }
}
