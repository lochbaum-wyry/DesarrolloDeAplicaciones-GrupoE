package domain;

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

    public Float calculateUserPoints(User user){

        Integer countRatesGood = user.getRates().stream().filter(rate -> rate.getValue().equals(RateValue.GOOD)).collect(Collectors.toList()).size();

        Integer countRatesBad = user.getRates().stream().filter(rate -> rate.getValue().equals(RateValue.BAD)).collect(Collectors.toList()).size();

        return new Float((countRatesGood * 500) - ((countRatesBad/2) * 1000));
    }
}
