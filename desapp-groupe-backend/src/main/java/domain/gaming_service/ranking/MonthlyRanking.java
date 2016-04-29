package domain.gaming_service.ranking;

import domain.Entity;
import domain.Repositories.UserRepository;
import domain.Repositories.VehicleRepository;
import domain.User;
import domain.Vehicle;

import java.util.List;


public class MonthlyRanking extends Entity
{
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


    public List<User> getBestDrivers() {
        return bestDrivers;
    }

    public void setBestDrivers(List<User> bestDrivers) {
        this.bestDrivers = bestDrivers;
    }

    public List<User> getBestPassenger() {
        return bestPassenger;
    }

    public void setBestPassenger(List<User> bestPassenger) {
        this.bestPassenger = bestPassenger;
    }

    public List<Vehicle> getBestVehicles() {
        return bestVehicles;
    }

    public void setBestVehicles(List<Vehicle> bestVehicles) {
        this.bestVehicles = bestVehicles;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public List<User> getMostEfficientDrivers() {
        return mostEfficientDrivers;
    }

    public void setMostEfficientDrivers(List<User> mostEfficientDrivers) {
        this.mostEfficientDrivers = mostEfficientDrivers;
    }

    public List<User> getWorstDrivers() {
        return worstDrivers;
    }

    public void setWorstDrivers(List<User> worstDrivers) {
        this.worstDrivers = worstDrivers;
    }

    public List<User> getWorstPassenger() {
        return worstPassenger;
    }

    public void setWorstPassenger(List<User> worstPassenger) {
        this.worstPassenger = worstPassenger;
    }

    public List<Vehicle> getWorstVehicles() {
        return worstVehicles;
    }

    public void setWorstVehicles(List<Vehicle> worstVehicles) {
        this.worstVehicles = worstVehicles;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


}
