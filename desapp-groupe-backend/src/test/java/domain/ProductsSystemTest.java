package domain;

import domain.builders.*;
import domain.exceptions.NotEnoughPointsException;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.lang.*;
import java.lang.System;

public class ProductsSystemTest {

    @Test
    public void test_exchangeableProductFor_aUserExchanged4ProductAndInsideInProductsSystem(){
        //TODO :quise poner, un usuario canjea 4 productos y estan dentro del ProductsSystem
        User user = UserBuilder.aUser().build();
        User fede = UserBuilder.aUser().build();

        Product product = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(500).withStock(99).build();
        Product product1 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(250).withStock(99).build();
        Product product2 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(200).withStock(99).build();

        ProductsSystem productsSystem = ProductsSystemBuilder.aProductSystem().withProduct(product).withProduct(product1).withProduct(product2).build();

        Route route = RouteBuilder.aRoute().withLocationAt(23.4,12.3).build();

        Ride ride = RideBuilder.aRide().withRoute(route).withDate(new DateTime()).withDriver(user).build();

        user.rateUser(fede,ride,RateValue.GOOD,"es un gran cebador de mate");
        user.rateUser(fede,ride,RateValue.GOOD,"es un gran amigo");
        user.rateUser(fede,ride,RateValue.GOOD,"es la onda");
        try {
            productsSystem.userExchangeaProduct(fede,product);
            productsSystem.userExchangeaProduct(fede,product2);

            Assert.assertEquals(productsSystem.exchangeableProductFor(fede).size(),2);

        } catch (NotEnoughPointsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_userExchangeaProduct_aUserWith2000PointsCanExchangeaProductto1000Points(){
        //TODO : quise poner,un usuario con 2000 puntos puede canjear un producto a 1000 puntos
        User fede = UserBuilder.aUser().withPoints(2000).build();

        Product product = ProductBuilder.aProduct().withName("1000 pesos de nafta").withCost(1000).withStock(2).build();

        ProductsSystem productsSystem = ProductsSystemBuilder.aProductSystem().withProduct(product).build();

        try {
            productsSystem.userExchangeaProduct(fede,product);

            Assert.assertEquals(productsSystem.exchangeableProductFor(fede).size(),1);

        } catch (NotEnoughPointsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NotEnoughPointsException.class)
    public void test_userExchangeaProduct_aUserWith1000PointsNoCanExchangeaProductto2000Points() throws NotEnoughPointsException {
        //TODO : quise poner,un usuario con 1000 puntos NO puede canjear un producto a 2000 puntos
        GamingSystem gamingSystem = GamingSystemBuilder.aGamingSystem().build();
        User fede = UserBuilder.aUser().withPoints(1000).build();

        Product product = ProductBuilder.aProduct().withName("1000 pesos de nafta").withCost(2000).withStock(2).build();

        ProductsSystem productsSystem = ProductsSystemBuilder.aProductSystem().withProduct(product).build();

        productsSystem.userExchangeaProduct(fede, product);
    }
}
