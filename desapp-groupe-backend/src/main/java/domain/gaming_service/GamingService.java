package domain.gaming_service;

import domain.System;
import domain.gaming_service.product_service.ProductsService;
import domain.gaming_service.ranking.Ranking;
import domain.gaming_service.scoring_service.ScoringService;

public class GamingService {
    private ScoringService scoringService;
    private ProductsService productsService;
    private System system;

    public GamingService(System system){
        this.system = system;
        scoringService = new ScoringService();
        productsService = new ProductsService();
    }

    public ProductsService getProductsService() {
        return productsService;
    }

    public ScoringService getScoringService() {
        return scoringService;
    }

    public Ranking createRanking(Integer month, Integer year){
        return new Ranking(month,year,system.getUsers());
    }

}
