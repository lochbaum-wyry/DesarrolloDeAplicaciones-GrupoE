package domain.gaming_service.product_service;

import domain.User;
import domain.exceptions.NotEnoughPointsException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsService {
    private List<Product> availableProducts;
    private List<ProductExchange> productExchanges;

    public ProductsService(){
        this.availableProducts = new ArrayList<Product>();
        this.productExchanges = new ArrayList<ProductExchange>();
    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }

    public List<ProductExchange> getProductExchanges() {
        return productExchanges;
    }

    public List<ProductExchange> exchangedProductsBy(User user)
    {
        return this.getProductExchanges().stream()
                .filter(productExchange -> productExchange.getUser().equals(user))
                .collect(Collectors.toList());
    }

    public void userExchangesAProduct(User user, Product product) throws NotEnoughPointsException
    {
        if(user.getPoints() >= product.getCost() && product.getStock()>0)
        {
            ProductExchange productExchange = new ProductExchange(user, product);
            product.setStock(product.getStock()-1);
            this.getProductExchanges().add(productExchange);
            user.addPoints(-product.getCost());
        }else
            throw new NotEnoughPointsException(user,product);
    }

}

