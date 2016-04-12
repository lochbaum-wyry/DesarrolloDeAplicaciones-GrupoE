package domain;

import domain.builders.*;
import domain.exceptions.NoSeatsAvailableException;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

    @Test
    public void test_rejectRideRequest_rideRequestIsSetToRejectedStatus()
    {
        User driver = driverWithVehicle(2);
        User passenger = UserBuilder.aUser().build();
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        passenger.requestRide(driver, rideRequest);
        driver.rejectRideRequest(rideRequest);

        Assert.assertEquals(RequestStatus.Rejected, rideRequest.getStatus());
    }

    @Test
    public void test_rejectRideRequest_rideRequestIsRemovedFromDriversRideRequests()
    {
        User driver = driverWithVehicle(2);
        User passenger = UserBuilder.aUser().build();
        RideRequest rideRequest = RideRequestBuilder.aRideRequest().build();

        passenger.requestRide(driver, rideRequest);
        driver.rejectRideRequest(rideRequest);

        Assert.assertFalse(driver.getRideRequests().contains(rideRequest));

    }

    @Test
    public void test_sendMessage_newChatIsCreatedWhenUsersMessageForTheFirstTime()
    {
        User user = UserBuilder.aUser().build();
        User otherUser = UserBuilder.aUser().build();

        Assert.assertTrue(user.getChats().isEmpty());

        user.sendMessage(otherUser, "como estas cabezon???");

        Assert.assertFalse(user.getChats().isEmpty());
    }

    @Test
    public void test_sendMessage_newChatIsAddedToTheOtherUserWhenUsersMessageForTheFirstTime()
    {
        User user = UserBuilder.aUser().build();
        User otherUser = UserBuilder.aUser().build();

        Assert.assertTrue(otherUser.getChats().isEmpty());

        user.sendMessage(otherUser, "como estas cabezon???");

        Assert.assertFalse(otherUser.getChats().isEmpty());
    }

    @Test
    public void test_sendMessage_onlyOneChatPerUserIsCreatedOnEachUser()
    {
        User user = UserBuilder.aUser().build();
        User otherUser = UserBuilder.aUser().build();

        user.sendMessage(otherUser, "como estas cabezon???");
        user.sendMessage(otherUser, "todo bien???");

        Assert.assertEquals(1, this.numberOfChatsWithUser(user,otherUser));
    }

    @Test
    public void test_rateUser_UserContainRateCreated(){
        //quise poner test_rateUser_el usuario contiene el rate creado
        User user = UserBuilder.aUser().withName("user").build();
        User fede = mock(User.class);

        Route route = RouteBuilder.aRoute().withLocationAt(23.4,12.3).build();

        Ride ride = RideBuilder.aRide().withRoute(route).withDate(new DateTime()).withDriver(user).build();

        user.rateUser(fede,ride,RateValue.GOOD,"es un gran cebador de mate");

        Mockito.verify(fede).addRate(any(Rate.class));
    }

    @Test
    public void test_rateCar_VehicleContainRateCreated(){
        //lo mismo que arriba
        User user = UserBuilder.aUser().withName("user").build();

        Route route = RouteBuilder.aRoute().withLocationAt(23.4,12.3).build();

        Vehicle vehicle = mock(Vehicle.class);

        Ride ride = RideBuilder.aRide().withRoute(route).withDate(new DateTime()).withVehicle(vehicle).withDriver(user).build();

        user.rateCar(ride,RateValue.BAD,"este auto es una porqueria");

        Mockito.verify(vehicle).addRate(any(Rate.class));

    }

    @Test
    public void test_isDriver_ReturnTrueIfaUserHaveAvehicle(){

        Vehicle vehicle = VehicleBuilder.aVehicle().withCapacity(23).withOilWasterPerHour(23.0f).build();

        User user = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .withVehicle(vehicle)
                .build();

        Assert.assertTrue(user.isDriver());
    }

    @Test
    public void test_isPassenger_ReturnTrueIfaUserNotHaveAvehicle(){

        User user = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();

        Assert.assertTrue(user.isPassenger());
    }

    public long numberOfChatsWithUser(User user, User searchedUser)
    {
        return user.getChats().stream()
                .filter(p -> p.getUsers().contains(searchedUser))
                .count();
    }

    @Test
    public void test_getRidesAsDriver_returnsAListContainingAllTheRidesWhereUserIsDriver()
    {

        User user = UserBuilder.aUser().build();

        Ride ride1 = mock(Ride.class);
        when(ride1.isDriver(user)).thenReturn(true);

        Ride ride2 = mock(Ride.class);
        when(ride2.isDriver(user)).thenReturn(true);

        Ride ride3 = mock(Ride.class);
        when(ride3.isDriver(user)).thenReturn(false);

        user.addRide(ride1);
        user.addRide(ride2);
        user.addRide(ride3);

        user.getRidesAsDriver().stream().forEach(
            ride -> Assert.assertTrue(ride.isDriver(user))
        );

        Assert.assertFalse(user.getRidesAsDriver().contains(ride3));

    }

}