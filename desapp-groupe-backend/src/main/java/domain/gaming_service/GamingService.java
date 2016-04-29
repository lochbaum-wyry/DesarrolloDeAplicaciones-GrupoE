package domain.gaming_service;

import domain.Repositories.UserRepository;
import domain.Repositories.VehicleRepository;
import domain.System;
import domain.gaming_service.product_service.ProductsService;
import domain.gaming_service.ranking.MonthlyRanking;
import domain.gaming_service.scoring_service.ScoringService;

public class GamingService
{
    private ScoringService scoringService;
    private UserRepository userRepository ;
    private VehicleRepository vehicleRepository;
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
        ranking.setBestDrivers(userRepository.getBestDriversInMonthYear(month, year,20));
        ranking.setWorstDrivers(userRepository.getWorstDriversInMonthYear(month,year,20));
        ranking.setBestPassenger(userRepository.getBestPassengersInMonthYear(month,year,20));
        ranking.setWorstPassenger(userRepository.getWorstPassengersInMonthYear(month,year,20));
        ranking.setBestVehicles(vehicleRepository.getBestVehiclesInMonthYear(month,year,20));
        ranking.setWorstVehicles(vehicleRepository.getWorstVehiclesInMonthYear(month,year,20));
        ranking.setMostEfficientDrivers(userRepository.getMostEfficientDriversInMonthYear(month, year));
        return ranking ;
    }

    public void setSystem(System system) {
        this.system = system;
    }
}

