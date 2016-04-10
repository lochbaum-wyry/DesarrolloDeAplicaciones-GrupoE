package domain;
public class GamingSystem {
    private ScoringSystem scoringSystem;
    private ProductsSystem productsSystem;
    private System system;
    private ReputationSystem reputationSystem;

    public GamingSystem(System system){
        this.system = system;
        scoringSystem = new ScoringSystem();
        productsSystem = new ProductsSystem();
        reputationSystem = new ReputationSystem();
    }

    public ProductsSystem getProductsSystem() {
        return productsSystem;
    }

    public ReputationSystem getReputationSystem() {
        return reputationSystem;
    }

    public ScoringSystem getScoringSystem() {
        return scoringSystem;
    }

    public Ranking createRanking(Integer month,Integer year){
        return new Ranking(month,year,system.getUsers());
    }

    public Float calculateReputation(User user){
        return 0f;
    }

//    public Float calculateEfficiency(User user){
        //TODO: deberia hacerlo GameSystem?
//    }

}
