package domain;

import domain.builders.*;
import domain.exceptions.NotEnoughPointsException;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class ProductsSystemTest {

    @Test
    public void test_exchangeableProductFor_aUserExchanged4ProductAndInsideInGamingSystem(){
        //quise poner, un usuario canjea 4 productos y estan dentro del gamingSystem
        User user = UserBuilder.aUser().build();
        User fede = UserBuilder.aUser().build();

        Product product = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(500).withStock(99).build();
        Product product1 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(250).withStock(99).build();
        Product product2 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(200).withStock(99).build();

        ProductsSystem productsSystem = ProductsSystemBuilder.aGamingSystem().withProduct(product).withProduct(product1).withProduct(product2).build();

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

    //TODO : falta testear userExchangeaProduct
}
