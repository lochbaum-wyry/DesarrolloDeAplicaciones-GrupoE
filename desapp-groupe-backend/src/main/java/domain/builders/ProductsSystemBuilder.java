package domain.builders;

import domain.GamingSystem;
import domain.Product;
import domain.ProductExchange;
import domain.ProductsSystem;

import java.util.ArrayList;
import java.util.List;

public class ProductsSystemBuilder {
    private List<Product> availableProducts;
    private List<ProductExchange> productExchanges;

    public ProductsSystemBuilder(){
        this.availableProducts = new ArrayList<Product>();
        this.productExchanges = new ArrayList<ProductExchange>();
    }

    public static ProductsSystemBuilder aGamingSystem() {
        return new ProductsSystemBuilder();
    }

    public ProductsSystem build() {
        ProductsSystem productsSystem = new ProductsSystem();
        productsSystem.getAvailableProducts().addAll(availableProducts);
        productsSystem.getProductExchanges().addAll(productExchanges);
        return productsSystem;
    }

    public ProductsSystemBuilder withProduct(Product product) {
        this.availableProducts.add(product);
        return this;
    }
}
