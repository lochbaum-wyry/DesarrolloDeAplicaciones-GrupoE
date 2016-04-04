package domain;

import domain.exceptions.NotEnoughPointsException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GamingSystem {
    private List<Product> availableProducts;
    private List<ProductExchange> productExchanges;

    public GamingSystem(){
        this.availableProducts = new ArrayList<Product>();
        this.productExchanges = new ArrayList<ProductExchange>();
    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }

    public List<ProductExchange> getProductExchanges() {
        return productExchanges;
    }

    public Float calculateReputation(User user){
        Integer countRatesGood = user.getRates().stream().filter(rate -> rate.getValue().equals(RateValue.GOOD)).collect(Collectors.toList()).size();

        Integer countRatesBad = user.getRates().stream().filter(rate -> rate.getValue().equals(RateValue.BAD)).collect(Collectors.toList()).size();

        return new Float((countRatesGood * 500) - ((countRatesBad/2) * 1000));
    }

    public Float calculateUserPoints(User user){
        return this.calculateReputation(user) - user.getSpentPoints();
    }

//    public Float calculateEfficiency(User user){
        //y esto debemos hablarlo no es tan facil, tal vez  hay que remodelar algo
//    }

    public List<ProductExchange> exchangeableProductFor(User user){
        return this.getProductExchanges().stream().filter(productExchange -> productExchange.getUser().equals(user)).collect(Collectors.toList());
    }

    public void userExchangeaProduct(User user,Product product) throws NotEnoughPointsException
    {
        if(calculateUserPoints(user) > product.getCost()) {
            ProductExchange productExchange = new ProductExchange(user, product);
            this.getProductExchanges().add(productExchange);
            user.setSpentPoints(user.getSpentPoints()-product.getCost());
        }else
            throw new NotEnoughPointsException(user,product);
    }




}
