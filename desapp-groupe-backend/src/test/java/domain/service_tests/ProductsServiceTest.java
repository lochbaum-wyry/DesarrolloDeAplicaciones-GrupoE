package domain.service_tests;

import domain.User;
import domain.builders.*;
import domain.exceptions.NotEnoughPointsException;
import domain.Product;
import domain.services.ProductsService;
import org.junit.Assert;
import org.junit.Test;

public class ProductsServiceTest {
/*
    @Test
    public void test_exchangedProductsBy_exchangedProductsByUserAreRegistered()
    {
        //TODO :quise poner, un usuario canjea 4 productos y estan dentro del ProductsService
        User fede = UserBuilder.aUser().withPoints(1000).build();

        Product product = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(500).withStock(99).build();
        Product product1 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(250).withStock(99).build();
        Product product2 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(200).withStock(99).build();

        ProductsService productsService = ProductsSystemBuilder.aProductSystem().withProduct(product).withProduct(product1).withProduct(product2).build();

        try {
            productsService.userExchangesAProduct(fede,product);
            productsService.userExchangesAProduct(fede,product1);
            productsService.userExchangesAProduct(fede,product2);

            Assert.assertEquals(productsService.exchangedProductsBy(fede).size(),3);

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

        ProductsService productsService = ProductsSystemBuilder.aProductSystem().withProduct(product).build();

        try {
            productsService.userExchangesAProduct(fede,product);

            Assert.assertEquals(productsService.exchangedProductsBy(fede).size(),1);

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

        ProductsService productsService = ProductsSystemBuilder.aProductSystem().withProduct(product).build();

        productsService.userExchangesAProduct(fede, product);
    }
    */
}
