package domain;

import domain.builders.*;
import domain.services.System;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserTest extends AbstractDomainTest
{


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

        Assert.assertTrue(user.hasVehicle());
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

    @Test
    public void test_setAndGetSystem(){
        User user = UserBuilder.aUser().build();

        System system = Mockito.mock(System.class);
        user.setSystem(system);

        Assert.assertEquals(user.getSystem(),system);
    }

    @Test
    public void test_setAndGetName(){
        User user = UserBuilder.aUser().build();

        user.setName("fede");

        Assert.assertEquals(user.getName(),"fede");
    }

    @Test
    public void test_setAndGetLastName(){
        User user = UserBuilder.aUser().build();

        user.setLastName("feder");

        Assert.assertEquals(user.getLastName(),"feder");
    }

    @Test
    public void test_setAndGetUserName(){
        User user = UserBuilder.aUser().build();

        user.setUserName("trime");

        Assert.assertEquals(user.getUserName(),"trime");
    }

    @Test
    public void test_setAndGetEmail(){
        User user = UserBuilder.aUser().build();

        user.setEmail("fede@fede.fede");

        Assert.assertEquals(user.getEmail(),"fede@fede.fede");
    }

    @Test
    public void test_setAndGetVehicle(){
        User user = UserBuilder.aUser().build();

        Vehicle vehicle = Mockito.mock(Vehicle.class);
        user.setVehicle(vehicle);

        Assert.assertEquals(user.getVehicle(),vehicle);
    }

    @Test
    public void test_setAndGetRoutes(){
        User user = UserBuilder.aUser().build();

        List<Route> routes = new ArrayList<Route>();
        routes.add(RouteBuilder.aRoute().build());
        user.setRoutes(routes);

        Assert.assertEquals(user.getRoutes(),routes);
    }

    @Test
    public void test_setAndGetPoints(){
        User user = UserBuilder.aUser().build();

        user.setPoints(200);

        Assert.assertEquals(user.getPoints().intValue(),200);
    }

    @Test
    public void test_setAndGetChats(){
        User user = UserBuilder.aUser().build();

        List<Chat> chats = new ArrayList<Chat>();
        chats.add(ChatBuilder.aChat().build());
        user.setChats(chats);

        Assert.assertEquals(user.getChats(),chats);
    }

    @Test
    public void test_setAndGetTotalRateCount(){
        User user = UserBuilder.aUser().build();

        user.setTotalRateCount(200);

        Assert.assertEquals(user.getTotalRateCount().intValue(),200);
    }

    @Test
    public void test_setAndGetTotalBadRateCount(){
        User user = UserBuilder.aUser().build();

        user.setBadRateCount(200);

        Assert.assertEquals(user.getBadRateCount().intValue(),200);
    }

    @Test
    public void test_setAndGetTotalGoodRateCount(){
        User user = UserBuilder.aUser().build();

        user.setGoodRateCount(200);

        Assert.assertEquals(user.getGoodRateCount().intValue(),200);
    }

    @Test
    public void test_GetGoodRatesPercentage(){
        User user = UserBuilder.aUser().build();

        user.setGoodRateCount(200);
        user.setBadRateCount(100);
        user.setTotalRateCount(300);

        Assert.assertEquals(user.getGoodRatesPercentage().intValue(),66);
    }

    @Test
    public void test_GetBadRatesPercentage(){
        User user = UserBuilder.aUser().build();

        user.setGoodRateCount(200);
        user.setBadRateCount(100);
        user.setTotalRateCount(300);

        Assert.assertEquals(user.getBadRatesPercentage().intValue(),33);
    }

    @Test
    public void test_updateRateCounters(){
        User user = UserBuilder.aUser().build();

        user.updateRateCounters(RateBuilder.aRate().withRateValue(RateValue.GOOD).build());

        Assert.assertEquals(user.getGoodRateCount().intValue(),1);
        Assert.assertEquals(user.getBadRateCount().intValue(),0);

        user.updateRateCounters(RateBuilder.aRate().withRateValue(RateValue.BAD).build());

        Assert.assertEquals(user.getBadRateCount().intValue(),1);

    }

    @Test
    public void test_addRoute(){
        User user = UserBuilder.aUser().build();

        user.addRoute(RouteBuilder.aRoute().build());

        Assert.assertEquals(user.getRoutes().size(),1);
    }

}