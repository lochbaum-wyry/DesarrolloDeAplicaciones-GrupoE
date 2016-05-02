package domain;

import domain.builders.ProductBuilder;
import domain.builders.UserBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ProductExchangedTest {

    @Test
    public void test_setAndGetUser(){
        User user = UserBuilder.aUser().build();
        ProductExchange productExchange = new ProductExchange(user,null);

        Assert.assertEquals(productExchange.getUser(),user);

        User user2 = UserBuilder.aUser().build();
        productExchange.setUser(user2);

        Assert.assertEquals(productExchange.getUser(),user2);
    }

    @Test
    public void test_setAndGetProduct(){
        Product product = ProductBuilder.aProduct().build();
        ProductExchange productExchange = new ProductExchange(null,product);

        Assert.assertEquals(productExchange.getProduct(),product);

        Product product2 = ProductBuilder.aProduct().build();
        productExchange.setProduct(product2);

        Assert.assertEquals(productExchange.getProduct(),product2);
    }

}