package domain;

import domain.builders.GamingSystemBuilder;
import domain.builders.RideBuilder;
import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class GamingSystemTest {

    @Test
    public void test_calculateUserPoints_UserHave5RatesGoodAnd3RatesBad(){
        //quiser poner ususario tiene 5 rates buenos y 3 malos
        User user = UserBuilder.aUser().build();
        User fede = UserBuilder.aUser().build();
        User jorge = UserBuilder.aUser().build();

        GamingSystem gamingSystem = GamingSystemBuilder.aGamingSystem().build();

        Route route = RouteBuilder.aRoute().withLocationAt(23.4,12.3).build();

        Ride ride = RideBuilder.aRide().withRoute(route).withDate(new DateTime()).withDriver(user).build();

        //Rates GOOD
        user.rateUser(fede,ride,RateValue.GOOD,"es un gran cebador de mate");
        user.rateUser(fede,ride,RateValue.GOOD,"es un gran amigo");
        user.rateUser(fede,ride,RateValue.GOOD,"es la onda");
        jorge.rateUser(fede,ride,RateValue.GOOD,"este pibe es un buen acompaniante");
        jorge.rateUser(fede,ride,RateValue.GOOD,"como sabe de futbol");

        //Rates BAD
        user.rateUser(fede,ride,RateValue.BAD,"se volvio muy molesto");
        jorge.rateUser(fede,ride,RateValue.BAD,"es un asco este chabon");
        jorge.rateUser(fede,ride,RateValue.BAD,"el pibe no se bania");

        Assert.assertEquals(gamingSystem.calculateUserPoints(fede),new Float(1500));
    }
}
