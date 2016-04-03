package domain;

import domain.builders.*;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RideTest {

    @Test
    public void test_seatTakenBy_whenPassengerIsIncludedInRideThenIsPresentIsTrue()
    {
        Vehicle vehicle = VehicleBuilder.aVehicle().withCapacity(2).withOilWasterPerHour(29.2).build();
        User driverMario = UserBuilder.aUser().withName("Mario").withVehicle(vehicle).build();

        User passengerFede = UserBuilder.aUser().withName("Fede").build();

        Location boardingAt1 = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();
        Location getOffAt1 = LocationBuilder.aLocation().withLatitude(38.6).withLongitude(40.4).build();

        TakenSeat takenSeatFede = TakenSeatBuilder.aTakenSeat()
                .withPassenger(passengerFede)
                .withboardingAtLocation(boardingAt1)
                .withgetOffAtLocation(getOffAt1)
                .build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario).withTakenSeatAt(takenSeatFede).build();

        Assert.assertTrue(ride.seatTakenBy(passengerFede).isPresent());
    }

    @Test
    public void test_seatTakenBy_whenPassengerNotIsIncludedInRideThenIsPresentIsFalse()
    {
        Vehicle vehicle = VehicleBuilder.aVehicle().withCapacity(2).withOilWasterPerHour(29.2).build();
        User driverMario = UserBuilder.aUser().withName("Mario").withVehicle(vehicle).build();

        User passengerFede = UserBuilder.aUser().withName("Fede").build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario).build();

        Assert.assertFalse(ride.seatTakenBy(passengerFede).isPresent());
    }



    @Test
    public void test_seatIsTakenInSection_whenSeatBoardAndGetOffLocationsOverlapFromToThenItReturnsTrue()
    {
        Vehicle vehicle = VehicleBuilder.aVehicle().withCapacity(2).withOilWasterPerHour(29.2).build();

        User passengerFede = UserBuilder.aUser().withName("Fede").build();
        User driverMario = UserBuilder.aUser().withName("Mario").withVehicle(vehicle).build();

        Location route1 = LocationBuilder.aLocation().withLatitude(50.0).withLongitude(50.0).build();
        Location route2 = LocationBuilder.aLocation().withLatitude(100.0).withLongitude(100.0).build();
        Location route3 = LocationBuilder.aLocation().withLatitude(150.0).withLongitude(150.0).build();
        Location route4 = LocationBuilder.aLocation().withLatitude(200.0).withLongitude(200.0).build();

        Route route = RouteBuilder.aRoute()
                .withLocation(route1)
                .withLocation(route2)
                .withLocation(route3)
                .withLocation(route4)
                .build();

        TakenSeat takenSeatFede = TakenSeatBuilder.aTakenSeat()
                                    .withPassenger( passengerFede )
                                    .withboardingAtLocation( route2 )
                                    .withgetOffAtLocation( route3 )
                                    .build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario)
                        .withRoute(route)
                        .withTakenSeatAt(takenSeatFede)
                        .build();

        Assert.assertTrue(ride.seatIsTakenInSection(takenSeatFede, route2, route3));
    }

    @Test
    public void test_seatIsTakenInSection_whenSeatBoardAndGetOffLocationsDontOverlapFromToThenItReturnsFalse()
    {
        Vehicle vehicle = VehicleBuilder.aVehicle().withCapacity(2).withOilWasterPerHour(29.2).build();

        User passengerFede = UserBuilder.aUser().withName("Fede").build();
        User driverMario = UserBuilder.aUser().withName("Mario").withVehicle(vehicle).build();

        Location route1 = LocationBuilder.aLocation().withLatitude(50.0).withLongitude(50.0).build();
        Location route2 = LocationBuilder.aLocation().withLatitude(100.0).withLongitude(100.0).build();
        Location route3 = LocationBuilder.aLocation().withLatitude(150.0).withLongitude(150.0).build();
        Location route4 = LocationBuilder.aLocation().withLatitude(200.0).withLongitude(200.0).build();

        Route route = RouteBuilder.aRoute()
                .withLocation(route1)
                .withLocation(route2)
                .withLocation(route3)
                .withLocation(route4)
                .build();

        TakenSeat takenSeatFede = TakenSeatBuilder.aTakenSeat()
                .withPassenger(passengerFede)
                .withboardingAtLocation(route3)
                .withgetOffAtLocation(route4)
                .build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario)
                .withRoute(route)
                .withTakenSeatAt(takenSeatFede)
                .build();

        Assert.assertFalse(ride.seatIsTakenInSection(takenSeatFede, route1,route2));
    }

    @Test
    public void test_takenSeatsCountInSection_resultContainsPassengersThatOccupySeatsInTheGivenSectionOfTheRoute()
    {
        Vehicle vehicle = VehicleBuilder.aVehicle().withCapacity(2).build();

        User occupier = UserBuilder.aUser().withName("Dady Brieva").build();
        User notAnOccupier = UserBuilder.aUser().withName("Miguel Del Sel").build();
        User driverMario = UserBuilder.aUser().withName("Mario").withVehicle(vehicle).build();

        Location route1 = LocationBuilder.aLocation().withLatitude(50.0).withLongitude(50.0).build();
        Location route2 = LocationBuilder.aLocation().withLatitude(100.0).withLongitude(100.0).build();
        Location route3 = LocationBuilder.aLocation().withLatitude(150.0).withLongitude(150.0).build();
        Location route4 = LocationBuilder.aLocation().withLatitude(200.0).withLongitude(200.0).build();
        Location route5 = LocationBuilder.aLocation().withLatitude(250.0).withLongitude(250.0).build();

        Route route = RouteBuilder.aRoute()
                .withLocation(route1)
                .withLocation(route2)
                .withLocation(route3)
                .withLocation(route4)
                .withLocation(route5)
                .build();

        TakenSeat occupiersSeat = TakenSeatBuilder.aTakenSeat()
                .withPassenger(occupier)
                .withboardingAtLocation(route1)
                .withgetOffAtLocation(route3)
                .build();

        TakenSeat notAnOccupiersSeat = TakenSeatBuilder.aTakenSeat()
                .withPassenger(notAnOccupier)
                .withboardingAtLocation(route2)
                .withgetOffAtLocation(route5)
                .build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario)
                .withRoute(route)
                .withTakenSeatAt(occupiersSeat)
                .withTakenSeatAt(notAnOccupiersSeat)
                .build();

        List<TakenSeat> takenSeatsInSection = ride.takenSeatsCountInSection(route1, route2);

        Assert.assertTrue(takenSeatsInSection.contains(occupiersSeat));
        Assert.assertFalse(takenSeatsInSection.contains(notAnOccupiersSeat));

    }

    @Test
    public void test_availableSeatsCountInSection_resultMatchesNumberOfNotOccupiedSeatsInTheGivenSectionOfTheRoute()
    {
        Vehicle vehicle = VehicleBuilder.aVehicle()
                .withCapacity(2)
                .build();

        User occupier = UserBuilder.aUser().withName("Dady Brieva").build();
        User driverMario = UserBuilder.aUser().withName("Mario").withVehicle(vehicle).build();

        Location route1 = LocationBuilder.aLocation().withLatitude(50.0).withLongitude(50.0).build();
        Location route2 = LocationBuilder.aLocation().withLatitude(100.0).withLongitude(100.0).build();
        Location route3 = LocationBuilder.aLocation().withLatitude(150.0).withLongitude(150.0).build();
        Location route4 = LocationBuilder.aLocation().withLatitude(200.0).withLongitude(200.0).build();
        Location route5 = LocationBuilder.aLocation().withLatitude(250.0).withLongitude(250.0).build();

        Route route = RouteBuilder.aRoute()
                .withLocation(route1)
                .withLocation(route2)
                .withLocation(route3)
                .withLocation(route4)
                .withLocation(route5)
                .build();

        TakenSeat occupiersSeat = TakenSeatBuilder.aTakenSeat()
                .withPassenger(occupier)
                .withboardingAtLocation(route1)
                .withgetOffAtLocation(route2)
                .build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario)
                .withRoute(route)
                .withTakenSeatAt(occupiersSeat)
                .build();

        Assert.assertEquals((long)0,  (long) ride.availableSeatsCountInSection(route1,route2));
        Assert.assertEquals((long)1,  (long) ride.availableSeatsCountInSection(route2,route4));

    }

    @Test
    public void test_fromRideRequest_createdRideSuitsRideRequestDetails()
    {
        Vehicle vehicle = VehicleBuilder.aVehicle().withCapacity(2).withOilWasterPerHour(29.2).build();
        User driverMario = UserBuilder.aUser().withName("Mario").withVehicle(vehicle).build();
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        RideRequest rideRequest = RideRequestBuilder.aRideRequest()
                .withRoute(route)
                .withDate(new DateTime(2016,05,16,12,0,0))
                .build();

        Ride received = Ride.fromRideRequest(driverMario,rideRequest);

        Assert.assertTrue(received.suitsRideRequest(rideRequest));

    }
}
