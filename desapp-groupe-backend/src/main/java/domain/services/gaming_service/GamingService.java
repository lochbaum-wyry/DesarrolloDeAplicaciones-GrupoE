package domain.services.gaming_service;

import domain.repositories.RankingRepository;
import domain.repositories.UserRepository;
import domain.repositories.VehicleRepository;
import domain.services.MonthlyRanking;

public class GamingService
{
    private static int MAX_NUMBER_USERS_RANKING = 20 ;

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
        if(ranking == null)
        {
            ranking = new MonthlyRanking(month, year);
            ranking.setBestDrivers(userRepository.getBestDriversInMonthYear(month, year, MAX_NUMBER_USERS_RANKING));
            ranking.setWorstDrivers(userRepository.getWorstDriversInMonthYear(month, year, MAX_NUMBER_USERS_RANKING));
            ranking.setBestPassenger(userRepository.getBestPassengersInMonthYear(month, year, MAX_NUMBER_USERS_RANKING));
            ranking.setWorstPassenger(userRepository.getWorstPassengersInMonthYear(month, year, MAX_NUMBER_USERS_RANKING));
            ranking.setBestVehicles(vehicleRepository.getBestVehiclesInMonthYear(month, year, MAX_NUMBER_USERS_RANKING));
            ranking.setWorstVehicles(vehicleRepository.getWorstVehiclesInMonthYear(month, year, MAX_NUMBER_USERS_RANKING));
            ranking.setMostEfficientDrivers(userRepository.getMostEfficientDriversInMonthYear(month, year, MAX_NUMBER_USERS_RANKING));
            rankingRepository.save(ranking);
        }
        return ranking ;
    }

    public void assignEfficiencyPoints(Integer month, Integer year)
    {
//        userRepository.getMostEfficientDriversInMonthYear(month,year,MAX_NUMBER_USERS_RANKING).stream()
//            .forEach(user -> user.addPoints(5000));
    }
}

