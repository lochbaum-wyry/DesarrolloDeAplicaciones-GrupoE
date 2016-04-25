package domain.gaming_service.ranking;

import domain.Entity;
import domain.Repositories.UserRepository;
import domain.Repositories.VehicleRepository;
import domain.User;
import domain.Vehicle;

import java.util.List;


public class MonthlyRanking extends Entity
{
    private UserRepository userRepository ;
    private VehicleRepository vehicleRepository;

    private Integer month;
    private Integer year;
    private List<User> bestDrivers;
    private List<User> worstDrivers;
    private List<User> bestPassenger;
    private List<User> worstPassenger;
    private List<User> mostEfficientDrivers;
    private List<Vehicle> bestVehicles;
    private List<Vehicle> worstVehicles;

    public MonthlyRanking(Integer month, Integer year)
    {
        this.month = month;
        this.year = year ;
    }

    public void generate()
    {
        bestDrivers = userRepository.getBestDriversInMonthYear(month, year);
        worstDrivers = userRepository.getWorstDriversInMonthYear(month,year);

        bestPassenger = userRepository.getBestPassengersInMonthYear(month,year);
        worstPassenger = userRepository.getWorstPassengersInMonthYear(month,year);

        bestVehicles = vehicleRepository.getBestVehiclesInMonthYear(month,year);
        worstVehicles = vehicleRepository.getWorstVehiclesInMonthYear(month,year);

        mostEfficientDrivers = userRepository.getMostEfficientDriversInMonthYear(month, year);
    }

}
