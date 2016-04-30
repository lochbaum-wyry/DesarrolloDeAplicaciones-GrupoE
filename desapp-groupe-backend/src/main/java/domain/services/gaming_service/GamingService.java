package domain.services.gaming_service;

import domain.repositories.UserRepository;
import domain.repositories.VehicleRepository;
import domain.services.System;
import domain.services.ProductsService;
import domain.services.MonthlyRanking;
import domain.services.gaming_service.scoring_service.ScoringService;

public class GamingService
{
    private UserRepository userRepository ;
    private System system;
    private VehicleRepository vehicleRepository;

    public GamingService(UserRepository userRepository, VehicleRepository vehicleRepository,System system)
    {
        this.userRepository = userRepository ;
        this.vehicleRepository = vehicleRepository ;
        this.system = system;
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


    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

}

