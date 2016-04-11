package domain.builders;

import domain.gaming_service.product_service.Product;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {
    private String name;
    private List<Image> pictures;
    private Integer cost;
    private Integer stock;

    public ProductBuilder(){
        name = "";
        pictures = new ArrayList<Image>();
        cost = 0;
        stock = 1;
    }


    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withCost(int cost) {
        this.cost = cost;
        return this;
    }

    public ProductBuilder withStock(int stock) {
        this.stock = stock;
        return this;
    }

    public Product build() {
        return new Product(name,cost,stock);
    }
}
