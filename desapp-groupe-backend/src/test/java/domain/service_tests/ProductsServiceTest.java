package domain.service_tests;

import domain.User;
import domain.builders.*;
import domain.exceptions.NotEnoughPointsException;
import domain.Product;
import domain.services.ProductsService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductsServiceTest extends AbstractServiceTest{

    @Autowired
    public ProductsService productsService;

    @Test
    public void test_exchangedProductsBy_exchangedProductsByUserAreRegistered()
    {
        //TODO :quise poner, un usuario canjea 4 productos y estan dentro del ProductsService
        User fede = UserBuilder.aUser().withPoints(1000).build();
        productsService.getUserRepository().save(fede);

        Product product = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(500).withStock(99).build();
        Product product1 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(250).withStock(99).build();
        Product product2 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(200).withStock(99).build();

        productsService.getProductRepository().save(product);
        productsService.getProductRepository().save(product1);
        productsService.getProductRepository().save(product2);

        try {
            productsService.userExchangesAProduct(fede.getId(),product);
            productsService.userExchangesAProduct(fede.getId(),product1);
            productsService.userExchangesAProduct(fede.getId(),product2);

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
        productsService.getUserRepository().save(fede);

        Product product = ProductBuilder.aProduct().withName("1000 pesos de nafta").withCost(1000).withStock(2).build();
        productsService.getProductRepository().save(product);

        try {
            productsService.userExchangesAProduct(fede.getId(),product);

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
        productsService.getUserRepository().save(fede);

        Product product = ProductBuilder.aProduct().withName("1000 pesos de nafta").withCost(2000).withStock(2).build();
        productsService.getProductRepository().save(product);

        productsService.userExchangesAProduct(fede.getId(), product);
    }

    @Test
    public void test_products(){
        Product product = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(500).withStock(99).build();
        Product product1 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(250).withStock(99).build();
        Product product2 = ProductBuilder.aProduct().withName("500 pesos de nafta").withCost(200).withStock(99).build();

        productsService.getProductRepository().save(product);
        productsService.getProductRepository().save(product1);
        productsService.getProductRepository().save(product2);

        Assert.assertEquals(productsService.products().size(),3);
    }

}
