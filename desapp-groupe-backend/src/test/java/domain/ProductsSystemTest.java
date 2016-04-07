package domain;

import domain.builders.*;
import domain.exceptions.NotEnoughPointsException;
import org.junit.Assert;
import org.junit.Test;

public class ProductsSystemTest {

    @Test
    public void test_exchangedProductsBy_exchangedProductsByUserAreRegistered()
    {
        //TODO :quise poner, un usuario canjea 4 productos y estan dentro del ProductsSystem
        User fede = UserBuilder.aUser().withPoints(1000).build();

        Product product = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(500).withStock(99).build();
        Product product1 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(250).withStock(99).build();
        Product product2 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(200).withStock(99).build();

        ProductsSystem productsSystem = ProductsSystemBuilder.aProductSystem().withProduct(product).withProduct(product1).withProduct(product2).build();

        try {
            productsSystem.userExchangesAProduct(fede,product);
            productsSystem.userExchangesAProduct(fede,product1);
            productsSystem.userExchangesAProduct(fede,product2);

            Assert.assertEquals(productsSystem.exchangedProductsBy(fede).size(),3);

        } catch (NotEnoughPointsException e) {
            Assert.fail("No tiene puntos para realizar el canje");
        }
    }

    @Test
    public void test_userExchangesAProduct_aUserCanExchangeProductIfHeHasEnoughPointsToAffordTheCost()
    {
        //TODO : quise poner,un usuario con 2000 puntos puede canjear un producto a 1000 puntos
        User fede = UserBuilder.aUser().withPoints(2000).build();

        Product product = ProductBuilder.aProduct().withName("1000 pesos de nafta").withCost(1000).withStock(2).build();

        ProductsSystem productsSystem = ProductsSystemBuilder.aProductSystem().withProduct(product).build();

        try {
            productsSystem.userExchangesAProduct(fede,product);

            Assert.assertEquals(productsSystem.exchangedProductsBy(fede).size(),1);

        } catch (NotEnoughPointsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NotEnoughPointsException.class)
    public void test_userExchangesAProduct_aUserCanNotExchangeProductIfHeHasNotEnoughPointsToAffordTheCost() throws NotEnoughPointsException
    {
        //TODO : quise poner,un usuario con 1000 puntos NO puede canjear un producto a 2000 puntos
        User fede = UserBuilder.aUser().withPoints(1000).build();
        Product product = ProductBuilder.aProduct().withName("1000 pesos de nafta").withCost(2000).withStock(2).build();

        ProductsSystem productsSystem = ProductsSystemBuilder.aProductSystem().withProduct(product).build();

        productsSystem.userExchangesAProduct(fede, product);
    }
}
