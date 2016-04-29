package domain.builders;

import domain.Product;
import domain.ProductExchange;
import domain.services.ProductsService;

import java.util.ArrayList;
import java.util.List;

public class ProductsSystemBuilder {
    private List<Product> availableProducts;
    private List<ProductExchange> productExchanges;

    public ProductsSystemBuilder(){
        this.availableProducts = new ArrayList<Product>();
        this.productExchanges = new ArrayList<ProductExchange>();
    }

    public static ProductsSystemBuilder aProductSystem() {
        return new ProductsSystemBuilder();
    }

    public ProductsService build() {
        ProductsService productsService = new ProductsService();
        productsService.getAvailableProducts().addAll(availableProducts);
        productsService.getProductExchanges().addAll(productExchanges);
        return productsService;
    }

    public ProductsSystemBuilder withProduct(Product product) {
        this.availableProducts.add(product);
        return this;
    }
}
