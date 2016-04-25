package domain.gaming_service;

import domain.System;
import domain.gaming_service.product_service.ProductsService;
import domain.gaming_service.ranking.MonthlyRanking;
import domain.gaming_service.scoring_service.ScoringService;

public class GamingService
{
    private ScoringService scoringService;
    private ProductsService productsService;
    private System system;

    public GamingService()
    {
        scoringService = new ScoringService();
        productsService = new ProductsService();
    }

    public GamingService(System system){
        this();
        this.system = system;
    }

    public ProductsService getProductsService() {
        return productsService;
    }

    public ScoringService getScoringService() {
        return scoringService;
    }

    public MonthlyRanking createRanking(Integer month, Integer year)
    {
        MonthlyRanking ranking = new MonthlyRanking(month,year);
        ranking.generate();
        return ranking ;
    }

    public void setSystem(System system) {
        this.system = system;
    }
}
