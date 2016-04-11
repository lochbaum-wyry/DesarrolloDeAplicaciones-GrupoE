package domain;

import domain.builders.ProductBuilder;
import domain.gaming_service.product_service.Product;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTest {

    @Test
    public void test_setAndGetCost(){
        Product product = ProductBuilder.
                aProduct().
                withCost(30).
                withStock(10).
                withName("producto").
                build();

        Assert.assertEquals(product.getCost().intValue(),30);

        product.setCost(20);

        Assert.assertEquals(product.getCost().intValue(),20);
    }

    @Test
    public void test_setAndGetName(){
        Product product = ProductBuilder.
                aProduct().
                withCost(30).
                withStock(10).
                withName("producto").
                build();

        Assert.assertEquals(product.getName(),"producto");

        product.setName("productoNuevo");

        Assert.assertEquals(product.getName(),"productoNuevo");
    }

    @Test
    public void test_setGetAndAddPictures(){
        Product product = ProductBuilder.
                aProduct().
                withCost(30).
                withStock(10).
                withName("producto").
                build();

        List<Image> pictures = new ArrayList<Image>();

        Image image = Mockito.mock(Image.class);

        Assert.assertEquals(product.getPictures().size(),0);

        product.setPictures(pictures);

        product.addPicture(image);

        Assert.assertEquals(product.getPictures().size(),1);
    }


}
