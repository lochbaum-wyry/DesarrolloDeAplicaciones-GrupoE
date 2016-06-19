package domain.services.gaming_service;

import domain.User;
import domain.repositories.RankingRepository;
import domain.repositories.UserRepository;
import domain.repositories.VehicleRepository;
import domain.services.MonthlyRanking;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class GamingService
{
    private static int MAX_NUMBER_USERS_RANKING = 20 ;

    private UserRepository userRepository ;
    private VehicleRepository vehicleRepository;
    private RankingRepository rankingRepository;

    public GamingService(){}

    public GamingService(UserRepository userRepository, VehicleRepository vehicleRepository,RankingRepository rankingRepository)
    {
        this.userRepository = userRepository ;
        this.vehicleRepository = vehicleRepository ;
        this.rankingRepository = rankingRepository;
    }


    @Transactional
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
            assignEfficiencyPoints(ranking.getMostEfficientDrivers());
            rankingRepository.save(ranking);
            ranking = rankingRepository.getRankingIn(month,year);

        }
        return ranking ;
    }

    public void assignEfficiencyPoints(List<User> users)
    {
        for (User user : users){
            user.addPoints(5000);
            userRepository.update(user);
        }
    }
}

