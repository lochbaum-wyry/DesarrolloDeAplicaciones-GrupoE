package domain;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ProductExchangedTest {

    @Test
    public void test_getProduct(){
        User user = Mockito.mock(User.class);
        Product product = Mockito.mock(Product.class);

        ProductExchange productExchange = new ProductExchange(user,product);

        Assert.assertEquals(productExchange.getProduct(),product);
    }

}
