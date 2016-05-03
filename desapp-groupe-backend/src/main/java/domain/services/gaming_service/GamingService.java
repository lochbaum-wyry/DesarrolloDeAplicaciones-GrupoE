package domain.services.gaming_service;

import domain.repositories.RankingRepository;
import domain.repositories.UserRepository;
import domain.repositories.VehicleRepository;
import domain.services.System;
import domain.services.ProductsService;
import domain.services.MonthlyRanking;
import domain.services.gaming_service.scoring_service.ScoringService;

public class GamingService
{
    private UserRepository userRepository ;
    private VehicleRepository vehicleRepository;
    private RankingRepository rankingRepository;

    public GamingService(UserRepository userRepository, VehicleRepository vehicleRepository,RankingRepository rankingRepository)
    {
        this.userRepository = userRepository ;
        this.vehicleRepository = vehicleRepository ;
        this.rankingRepository = rankingRepository;
    }

    public MonthlyRanking createRanking(Integer month, Integer year)
    {
        MonthlyRanking ranking = rankingRepository.getRankingIn(month,year);
        if(ranking == null) {
            ranking = new MonthlyRanking(month, year);
            ranking.setBestDrivers(userRepository.getBestDriversInMonthYear(month, year, 20));
            ranking.setWorstDrivers(userRepository.getWorstDriversInMonthYear(month, year, 20));
            ranking.setBestPassenger(userRepository.getBestPassengersInMonthYear(month, year, 20));
            ranking.setWorstPassenger(userRepository.getWorstPassengersInMonthYear(month, year, 20));
            ranking.setBestVehicles(vehicleRepository.getBestVehiclesInMonthYear(month, year, 20));
            ranking.setWorstVehicles(vehicleRepository.getWorstVehiclesInMonthYear(month, year, 20));
            ranking.setMostEfficientDrivers(userRepository.getMostEfficientDriversInMonthYear(month, year));
            rankingRepository.save(ranking);
        }
        return ranking ;
    }
}

