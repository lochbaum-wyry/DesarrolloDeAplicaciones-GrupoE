package domain;

import domain.builders.*;
import domain.exceptions.NoSeatsAvailableException;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RideTest extends AbstractDomainTest
{

    @Test
    public void test_seatTakenBy_whenPassengerIsIncludedInRideThenIsPresentIsTrue()
    {
        User driverMario = driverWithVehicle(2);

        User passengerFede = UserBuilder.aUser().withName("Fede").build();

        RoutePoint boardingAt1 = RoutePointBuilder.aRoutePoint().withLatitude(50.5).withLongitude(60.4).build();
        RoutePoint getOffAt1 = RoutePointBuilder.aRoutePoint().withLatitude(38.6).withLongitude(40.4).build();

        TakenSeat takenSeatFede = TakenSeatBuilder.aTakenSeat()
                .withPassenger(passengerFede)
                .withboardingAtLocation(boardingAt1)
                .withgetOffAtLocation(getOffAt1)
                .build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario).withTakenSeatAt(takenSeatFede).build();

        Assert.assertTrue(ride.getSeatTakenBy(passengerFede).isPresent());
    }

    @Test
    public void test_seatTakenBy_whenPassengerNotIsIncludedInRideThenIsPresentIsFalse()
    {
        User driverMario = driverWithVehicle(2);

        User passengerFede = UserBuilder.aUser().withName("Fede").build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario).build();

        Assert.assertFalse(ride.getSeatTakenBy(passengerFede).isPresent());
    }



    @Test
    public void test_seatIsTakenInSection_whenSeatBoardAndGetOffRoutePointsOverlapFromToThenItReturnsTrue()
    {
        User passengerFede = UserBuilder.aUser().withName("Fede").build();
        User driverMario = driverWithVehicle(3);

        Route route = aCommonRouteWithLocations(4,50,50);

        RoutePoint route1 = route.getRoutePoints().get(0);
        RoutePoint route2 = route.getRoutePoints().get(1);
        RoutePoint route3 = route.getRoutePoints().get(2);
        RoutePoint route4 = route.getRoutePoints().get(3);

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
        User passengerFede = UserBuilder.aUser().withName("Fede").build();
        User driverMario = driverWithVehicle(2);

        Route route = aCommonRouteWithLocations(4,50,50);

        RoutePoint route1 = route.getRoutePoints().get(0);
        RoutePoint route2 = route.getRoutePoints().get(1);
        RoutePoint route3 = route.getRoutePoints().get(2);
        RoutePoint route4 = route.getRoutePoints().get(3);

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
        User occupier = aPassenger();
        User notAnOccupier = aPassenger();
        User driverMario = driverWithVehicle(2);

        Route route = aCommonRouteWithLocations(5,50,50);

        RoutePoint route1 = route.getRoutePoints().get(0);
        RoutePoint route2 = route.getRoutePoints().get(1);
        RoutePoint route3 = route.getRoutePoints().get(2);
        RoutePoint route4 = route.getRoutePoints().get(3);
        RoutePoint route5 = route.getRoutePoints().get(4);

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

        List<TakenSeat> takenSeatsInSection = ride.takenSeatsInSection(route1, route2);

        Assert.assertTrue(takenSeatsInSection.contains(occupiersSeat));
        Assert.assertFalse(takenSeatsInSection.contains(notAnOccupiersSeat));

    }

    @Test
    public void test_availableSeatsCountInSection_resultMatchesNumberOfNotOccupiedSeatsInTheGivenSectionOfTheRoute()
    {
        User occupier = UserBuilder.aUser().withName("Dady Brieva").build();
        User driverMario = driverWithVehicle(2);

        Vehicle vehicle = VehicleBuilder.aVehicle().withOilWasterPerHour(23.5f).withCapacity(2).build();

        RoutePoint route1 = RoutePointBuilder.aRoutePoint().withLatitude(50.0).withLongitude(50.0).build();
        RoutePoint route2 = RoutePointBuilder.aRoutePoint().withLatitude(100.0).withLongitude(100.0).build();
        RoutePoint route3 = RoutePointBuilder.aRoutePoint().withLatitude(150.0).withLongitude(150.0).build();
        RoutePoint route4 = RoutePointBuilder.aRoutePoint().withLatitude(200.0).withLongitude(200.0).build();
        RoutePoint route5 = RoutePointBuilder.aRoutePoint().withLatitude(250.0).withLongitude(250.0).build();

        Route route = RouteBuilder.aRoute()
                .withRoutePoint(route1)
                .withRoutePoint(route2)
                .withRoutePoint(route3)
                .withRoutePoint(route4)
                .withRoutePoint(route5)
                .build();

        TakenSeat occupiersSeat = TakenSeatBuilder.aTakenSeat()
                .withPassenger(occupier)
                .withboardingAtLocation(route1)
                .withgetOffAtLocation(route2)
                .build();

        Ride ride = RideBuilder.aRide().withDriver(driverMario)
                .withRoute(route)
                .withTakenSeatAt(occupiersSeat)
                .withVehicle(vehicle)
                .build();

        Assert.assertEquals((long) 0,  (long) ride.availableSeatsCountInSection(route1,route2));
        Assert.assertEquals((long) 1,  (long) ride.availableSeatsCountInSection(route2,route4));

    }


    @Test
    public void test_getTotalCost_isValueOfFixedCostsPlusOilCostsByDistance()
    {
        Float oilPrice = 10f;
        float routeDistanceInKms = 20f;

        Route route = mock(Route.class);
        when(route.getRoutePoints()).thenReturn(alistOfLocations());
        when(route.getFixedCosts()).thenReturn(100f);
        when(route.getDistanceInKms()).thenReturn(routeDistanceInKms);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getOilUsePerKmInLts()).thenReturn(0.05f);

        double oilCostPerKm = oilPrice * vehicle.getOilUsePerKmInLts();

        User driver = mock(User.class);
        when(driver.getVehicle()).thenReturn(vehicle);

        Ride ride = RideBuilder.aRide()
                .withRoute(route)
                .withDriver(driver)
                .withVehicle(vehicle)
                .withOilPrice(oilPrice)
                .build();

        Double expected = (oilCostPerKm * routeDistanceInKms) + route.getFixedCosts();
        Double totalCost = (double) ride.getTotalCost();

        Assert.assertEquals(expected, totalCost);


    }

    private List<RoutePoint> alistOfLocations() {

        List<RoutePoint> list = new ArrayList<RoutePoint>();

        Double latLongValue = 50d;
        for (int i = 0 ; i < 5 ; i++ , latLongValue += 50);
        {
            RoutePoint loc = new RoutePoint(latLongValue, latLongValue);
            list.add(loc);
        }
        return list;
    }


    @Test
    public void test_costPerPassenger_isTotalCostDividedByPassengersNumberInRide()
    {
        Float oilPrice = 10f;
        float routeDistanceInKms = 20f;

        Route route = mock(Route.class);
        when(route.getRoutePoints()).thenReturn(alistOfLocations());
        when(route.getFixedCosts()).thenReturn(90f);
        when(route.getDistanceInKms()).thenReturn(routeDistanceInKms);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getOilUsePerKmInLts()).thenReturn(0.05f);

        double oilCostPerKm = oilPrice * vehicle.getOilUsePerKmInLts();

        User driver = mock(User.class);
        when(driver.getVehicle()).thenReturn(vehicle);

        TakenSeat seat1 = mock(TakenSeat.class);
        TakenSeat seat2 = mock(TakenSeat.class);
        TakenSeat seat3 = mock(TakenSeat.class);

        RideCostCalculator rideCostCalculator = new RideCostPassengerDivision();
        Ride ride = RideBuilder.aRide()
                .withRoute(route)
                .withDriver(driver)
                .withVehicle(vehicle)
                .withOilPrice(oilPrice)
                .withRideCostCalculator(rideCostCalculator)
                .withTakenSeatAt(seat1)
                .withTakenSeatAt(seat2)
                .withTakenSeatAt(seat3)
                .build();


        Float expected = 25f;

        User passenger1 = mock(User.class);
        Assert.assertEquals(expected, ride.getCostForPassenger(passenger1));
    }

    @Test
    public void test_getDriver()
    {
        User driver = mock(User.class) ;
        Ride ride = RideBuilder.aRide()
                .withDriver(driver)
                .build();

        Assert.assertEquals(driver, ride.getDriver());

    }

    @Test
    public void test_getDate()
    {
        User driver = mock(User.class) ;
        DateTime date = new DateTime();
        Ride ride = RideBuilder.aRide()
                .withDriver(driver)
                .withDate(date)
                .build();

        Assert.assertEquals(date, ride.getDate());

    }

    @Test
    public void test_getOilPrice()
    {
        User driver = mock(User.class) ;
        Float expected = 100f;
        Ride ride = RideBuilder.aRide()
                .withDriver(driver)
                .withOilPrice(expected)
                .build();

        Assert.assertEquals(expected, ride.getOilPrice());

    }

    @Test
    public void test_getSavedAmount_isTheSumOfCostsPerPassengerCalculatedByRideCostCalculator(){
        User driver = mock(User.class) ;
        User passenger1 = mock(User.class) ;
        User passenger2 = mock(User.class) ;

        RideCostCalculator rideCostCalculator = mock(RideCostCalculator.class);
        when(rideCostCalculator.calculateCostForPassenger(any(User.class))).thenReturn(15f);

        Ride ride = RideBuilder.aRide()
                .withDriver(driver)
                .withTakenSeatAt(TakenSeatBuilder.aTakenSeat().withPassenger(passenger1).build())
                .withTakenSeatAt(TakenSeatBuilder.aTakenSeat().withPassenger(passenger2).build())
                .withRideCostCalculator(rideCostCalculator)
                .build();

        Assert.assertEquals(ride.getSavedAmount().intValue(),30);
    }

    @Test
    public void test_isDriver_whenUserIsDriverInRide(){
        User driver = mock(User.class) ;

        Ride ride = RideBuilder.aRide()
                .withDriver(driver)
                .build();

        Assert.assertTrue(ride.isDriver(driver));
    }

    @Test
    public void test_getEfficiencyPercentage_isThePercentageOfTheTotalCostOfRepresentedByTheSavedAmountForCarryingPassengers()
    {
        Float oilPrice = 10f;
        Float vehicleOilWastePerKm = 1f;
        Float routeFixedCosts = 80f;
        Float routeDistanceInKms = 1f;

        User driver = mock(User.class) ;
        User passenger1 = mock(User.class) ;
        User passenger2 = mock(User.class) ;

        RideCostCalculator rideCostCalculator = mock(RideCostPassengerDivision.class);
        when(rideCostCalculator.calculateCostForPassenger(any(User.class))).thenReturn(20f);


        Route route = mock(Route.class);
        when(route.getFixedCosts()).thenReturn(routeFixedCosts);
        when(route.getDistanceInKms()).thenReturn(routeDistanceInKms);


        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getOilUsePerKmInLts()).thenReturn(vehicleOilWastePerKm);


        Ride ride = RideBuilder.aRide()
                .withDriver(driver)
                .withTakenSeatAt(TakenSeatBuilder.aTakenSeat().withPassenger(passenger1).build())
                .withTakenSeatAt(TakenSeatBuilder.aTakenSeat().withPassenger(passenger2).build())
                .withRideCostCalculator(rideCostCalculator)
                .withRoute(route)
                .withVehicle(vehicle)
                .withOilPrice(oilPrice)
                .build();


        Float savedAmount = rideCostCalculator.calculateCostForPassenger(passenger1) +
                            rideCostCalculator.calculateCostForPassenger(passenger2);

        Float expected = (savedAmount*100)/ride.getTotalCost() ;

        Assert.assertEquals(ride.getEfficiencyPercentage(), expected);
    }

    @Test
    public void test_getCostRideCostCalculator_returnsTheActualRideCostCalculatorInstanceWhenIsSet()
    {
        User driver = mock(User.class);
        Ride ride = RideBuilder.aRide().withDriver(driver).withRideCostCalculator(new RideCostPassengerDivision()).build();
        Assert.assertTrue(ride.getRideCostCalculator() instanceof RideCostCalculator);
    }

    @Test
    public void test_cancelRide_isCancelledReturnsTrueAfterCancellingRide()
    {
        User driver = mock(User.class);
        Ride ride = RideBuilder.aRide().withDriver(driver).withRideCostCalculator(new RideCostPassengerDivision()).build();
        ride.cancelRide();
        Assert.assertTrue(ride.isCancelled());
    }


    @Test
    public void test_takeSeat_addsATakenSeatToRideTakenSeatsIfThereIsSpaceAvailableForTheRequestedSectionOfTheRide()
            throws NoSeatsAvailableException {

        User driver = mock(User.class);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getCapacity()).thenReturn(2);

        User occupierPassenger = mock(User.class);

        Route route = RouteBuilder.aRoute().withRoutePointAt(100.0,100.0).withRoutePointAt(200.0,200.0).build();
        RoutePoint boardAt = route.getRoutePoints().get(0);
        RoutePoint getOffAt = route.getRoutePoints().get( route.getRoutePoints().size()-1 );

        Ride ride = RideBuilder.aRide()
                .withDriver(driver)
                .withVehicle(vehicle)
                .withRoute(route)
                .build();

        Assert.assertTrue(ride.getTakenSeats().size() == 0);

        ride.takeSeat(occupierPassenger ,boardAt, getOffAt);

        Assert.assertTrue(ride.getTakenSeats().size() == 1);

    }

    @Test
    public void test_setAndGetCancelled(){
        User driver = mock(User.class);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getCapacity()).thenReturn(2);

        User occupierPassenger = mock(User.class);

        Route route = RouteBuilder.aRoute().withRoutePointAt(100.0,100.0).withRoutePointAt(200.0,200.0).build();
        RoutePoint boardAt = route.getRoutePoints().get(0);
        RoutePoint getOffAt = route.getRoutePoints().get( route.getRoutePoints().size()-1 );

        Ride ride = RideBuilder.aRide()
                .withDriver(driver)
                .withVehicle(vehicle)
                .withRoute(route)
                .build();

        ride.setCancelled(true);

        Assert.assertTrue(ride.getCancelled());
    }

    @Test
    public void test_setAndGetTakenSeats(){
        User driver = mock(User.class);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getCapacity()).thenReturn(2);

        User occupierPassenger = mock(User.class);

        Route route = RouteBuilder.aRoute().withRoutePointAt(100.0,100.0).withRoutePointAt(200.0,200.0).build();
        RoutePoint boardAt = route.getRoutePoints().get(0);
        RoutePoint getOffAt = route.getRoutePoints().get( route.getRoutePoints().size()-1 );

        Ride ride = RideBuilder.aRide()
                .withDriver(driver)
                .withVehicle(vehicle)
                .withRoute(route)
                .build();

        List<TakenSeat> takenSeats = new ArrayList<TakenSeat>();
        takenSeats.add(TakenSeatBuilder.aTakenSeat().build());
        ride.setTakenSeats(takenSeats);

        Assert.assertEquals(ride.getTakenSeats(),takenSeats);
    }

}
