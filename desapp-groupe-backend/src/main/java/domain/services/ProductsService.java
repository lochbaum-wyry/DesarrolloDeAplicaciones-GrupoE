package domain.services;

import domain.User;
import domain.exceptions.NotEnoughPointsException;
import domain.Product;
import domain.ProductExchange;
import domain.repositories.ProductExchangeRepository;
import domain.repositories.ProductRepository;
import domain.repositories.UserRepository;
import java.util.List;

public class ProductsService {

    ProductRepository productRepository;
    ProductExchangeRepository productExchangeRepository;
    UserRepository userRepository;

    public ProductsService(ProductRepository productRepository, ProductExchangeRepository productExchangeRepository,UserRepository userRepository){
        this.productExchangeRepository = productExchangeRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ProductExchangeRepository getProductExchangeRepository() {
        return productExchangeRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public List<ProductExchange> exchangedProductsBy(User user)
    {
        return productExchangeRepository.exchangedProductsBy(user.getId());
    }

    public List<Product> products(){
        return productRepository.findAll();
    }

    public void userExchangesAProduct(User user, Product product) throws NotEnoughPointsException
    {
        if(user.getPoints() >= product.getCost() && product.getStock()>0)
        {
            ProductExchange productExchange = new ProductExchange(user, product);
            productExchangeRepository.save(productExchange);

            product.setStock(product.getStock()-1);
            productRepository.update(product);

            user.addPoints(-product.getCost());
            userRepository.update(user);

        }else
            throw new NotEnoughPointsException(user,product);
    }

}

