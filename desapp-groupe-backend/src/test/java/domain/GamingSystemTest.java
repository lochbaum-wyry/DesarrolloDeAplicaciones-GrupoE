package domain;

import domain.builders.*;
import domain.exceptions.NotEnoughPointsException;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class GamingSystemTest {

    @Test
    public void test_calculateUserPoints_UserHave5RatesGoodAnd3RatesBadAndExchangedaProduct(){
        //quiser pone ususario tiene 5 rates buenos y 3 malos y canjeo un producto
        User user = UserBuilder.aUser().build();
        User fede = UserBuilder.aUser().build();
        User jorge = UserBuilder.aUser().build();

        Product product = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(1000).withStock(99).build();

        GamingSystem gamingSystem = GamingSystemBuilder.aGamingSystem().withProduct(product).build();

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


        try {
            gamingSystem.userExchangeaProduct(fede,product);
            Assert.assertEquals(gamingSystem.calculateUserPoints(fede),new Float(500));

        } catch (NotEnoughPointsException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = NotEnoughPointsException.class)
    public void test_calculateUserPoints_UserHave5RatesGoodAnd3RatesBadAndCanNoExchangedaProduct() throws NotEnoughPointsException{
        //quiser pone ususario tiene 5 rates buenos y 3 malos y no canjeo un Producto
        User user = UserBuilder.aUser().build();
        User fede = UserBuilder.aUser().build();
        User jorge = UserBuilder.aUser().build();

        Product product = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(3000).withStock(99).build();

        GamingSystem gamingSystem = GamingSystemBuilder.aGamingSystem().withProduct(product).build();

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

        gamingSystem.userExchangeaProduct(fede,product);

    }

    @Test
    public void test_calculateReputation_UserHave5RatesGoodAnd3RatesBad(){
        //quise poner ususario tiene 5 rates buenos y 3 malos
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

    @Test
    public void test_exchangeableProductFor_aUserExchanged4ProductAndInsideInGamingSystem(){
        //quise poner, un usuario canjea 4 productos y estan dentro del gamingSystem
        User user = UserBuilder.aUser().build();
        User fede = UserBuilder.aUser().build();

        Product product = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(500).withStock(99).build();
        Product product1 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(250).withStock(99).build();
        Product product2 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(200).withStock(99).build();

        GamingSystem gamingSystem = GamingSystemBuilder.aGamingSystem().withProduct(product).withProduct(product1).withProduct(product2).build();

        Route route = RouteBuilder.aRoute().withLocationAt(23.4,12.3).build();

        Ride ride = RideBuilder.aRide().withRoute(route).withDate(new DateTime()).withDriver(user).build();

        user.rateUser(fede,ride,RateValue.GOOD,"es un gran cebador de mate");
        user.rateUser(fede,ride,RateValue.GOOD,"es un gran amigo");
        user.rateUser(fede,ride,RateValue.GOOD,"es la onda");

        try {
            gamingSystem.userExchangeaProduct(fede,product);
            gamingSystem.userExchangeaProduct(fede,product2);

            Assert.assertEquals(gamingSystem.exchangeableProductFor(fede).size(),2);

        } catch (NotEnoughPointsException e) {
            e.printStackTrace();
        }
    }


}
