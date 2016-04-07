package domain;
public class GamingSystem {
    private ScoringSystem scoringSystem;
    private ProductsSystem productsSystem;

    public GamingSystem(){
        scoringSystem = new ScoringSystem();
        productsSystem = new ProductsSystem();
    }

    public ProductsSystem getProductsSystem() {
        return productsSystem;
    }

    public ScoringSystem getScoringSystem() {
        return scoringSystem;
    }

    public Float calculateReputation(User user){
        return 0f;
    }

//    public Float calculateEfficiency(User user){
        //TODO: deberia hacerlo GameSystem?
//    }

}
