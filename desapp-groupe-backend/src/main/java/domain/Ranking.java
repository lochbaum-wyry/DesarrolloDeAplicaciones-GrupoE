package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Ranking {
    private List<User> bestDrivers;
    private List<User> worstDrivers;
    private List<User> bestPassenger;
    private List<User> worstPassenger;
    private List<Vehicle> bestVehicles;
    private List<Vehicle> worstVehicles;

    public Ranking(Integer month,Integer year,List<User> users){
        bestDrivers = calculateBestDrivers(users,month,year);
        worstDrivers = calculateWorstDrivers(users,month,year);
        //bestPassenger = calculateBestPassenger(users,month,year);
        //worstPassenger = calculateWorstPassenger(users,month,year);
        //bestVehicles = calculateBestVehicles(users,month,year);
        //worstVehicles = calculateWorstVehicles(users,month,year);
    }

    private List<User> driversIn(List<User> users){
        return users.stream().filter(user -> user.isDriver()).collect(Collectors.toList());
    }

    private List<User> calculateWorstDrivers(List<User> users, Integer month, Integer year) {
        return orderUsersbyPointMinToMax(driversIn(users)).subList(0,19);
    }

    private List<User> calculateBestDrivers(List<User> users, Integer month, Integer year) {
        return orderUsersbyPointMaxToMin(driversIn(users)).subList(0,19);
    }

    //TODO : revisar, no estoy seguro de que cambiando el orden cambia el orden del resultado
    private List<User> orderUsersbyPointMaxToMin(List<User> users) {
        return users.stream().sorted((user1,user2) -> Integer.compare(user2.getPoints(),user1.getPoints())).collect(Collectors.toList());
    }

    private List<User> orderUsersbyPointMinToMax(List<User> users) {
        return users.stream().sorted((user1,user2) -> Integer.compare(user1.getPoints(),user2.getPoints())).collect(Collectors.toList());
    }

    public List<User> getBestDrivers() {
        return bestDrivers;
    }

    public List<User> getBestPassenger() {
        return bestPassenger;
    }

    public List<Vehicle> getBestVehicles() {
        return bestVehicles;
    }

    public List<User> getWorstDrivers() {
        return worstDrivers;
    }

    public List<User> getWorstPassenger() {
        return worstPassenger;
    }

    public List<Vehicle> getWorstVehicles() {
        return worstVehicles;
    }
}
