package domain.builders;

import domain.GamingSystem;
import domain.Product;
import domain.ProductExchange;

import java.util.ArrayList;
import java.util.List;

public class GamingSystemBuilder {
    private List<Product> availableProducts;
    private List<ProductExchange> productExchanges;

    public GamingSystemBuilder(){
        this.availableProducts = new ArrayList<Product>();
        this.productExchanges = new ArrayList<ProductExchange>();
    }

    public static GamingSystemBuilder aGamingSystem() {
        return new GamingSystemBuilder();
    }

    public GamingSystem build() {
        GamingSystem gamingSystem = new GamingSystem();
        gamingSystem.getAvailableProducts().addAll(availableProducts);
        gamingSystem.getProductExchanges().addAll(productExchanges);
        return gamingSystem;
    }

    public GamingSystemBuilder withProduct(Product product) {
        this.availableProducts.add(product);
        return this;
    }
}
