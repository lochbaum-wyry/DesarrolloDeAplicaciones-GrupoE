package domain.gaming_service.ranking;

import domain.Rateable;
import domain.User;
import domain.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Ranking {

    private List<Rateable> bestDrivers;
    private List<Rateable> worstDrivers;
    private List<Rateable> bestPassenger;
    private List<Rateable> worstPassenger;
    private List<Rateable> bestVehicles;
    private List<Rateable> worstVehicles;
    private List<User> mostEfficientDrivers;
    private int id;

    public Ranking(Integer month,Integer year,List<User> users)
    {
        //TODO : aca creo que deberiamos guardar el mes y año
        bestDrivers = calculateBestDrivers(users,month,year);
        worstDrivers = calculateWorstDrivers(users,month,year);
        bestPassenger = calculateBestPassenger(users,month,year);
        worstPassenger = calculateWorstPassenger(users,month,year);
        bestVehicles = calculateBestVehicles(users,month,year);
        worstVehicles = calculateWorstVehicles(users,month,year);
        mostEfficientDrivers = calculateMostEfficientDrivers(users, month, year);
    }

    private List<Rateable> calculateWorstVehicles(List<User> users, Integer month, Integer year) {
        List<Rateable> list = getVehicles(driversIn(users)).stream().map(user -> ((Rateable) user)).collect(Collectors.toList());
        return getSortedByCriteria(list,new RankingRateCount(year,month, CriteriaOrder.DESC),20);
    }

    private List<Rateable> calculateBestVehicles(List<User> users, Integer month, Integer year) {
        List<Rateable> list = getVehicles(driversIn(users)).stream().map(user -> ((Rateable) user)).collect(Collectors.toList());
        return getSortedByCriteria(list,new RankingRateCount(year,month, CriteriaOrder.ASC),20);
    }

    private List<Rateable> calculateWorstPassenger(List<User> users, Integer month, Integer year) {
        List<Rateable> list = passengerIn(users).stream().map(user -> ((Rateable) user)).collect(Collectors.toList());
        return getSortedByCriteria(list,new RankingRateCount(year,month,CriteriaOrder.DESC),20);
    }

    private List<Rateable> calculateBestPassenger(List<User> users, Integer month, Integer year) {
        List<Rateable> list = passengerIn(users).stream().map(user -> ((Rateable) user)).collect(Collectors.toList());
        return getSortedByCriteria(list,new RankingRateCount(year,month,CriteriaOrder.ASC),20);
    }

    private List<Rateable> calculateWorstDrivers(List<User> users, Integer month, Integer year) {
        List<Rateable> list = driversIn(users).stream().map(user -> ((Rateable) user)).collect(Collectors.toList());
        return getSortedByCriteria(list,new RankingRateCount(year,month,CriteriaOrder.DESC),20);
    }

    private List<Rateable> calculateBestDrivers(List<User> users, Integer month, Integer year) {
        List<Rateable> list = driversIn(users).stream().map(user -> ((Rateable) user)).collect(Collectors.toList());
        return getSortedByCriteria(list,new RankingRateCount<>(year,month,CriteriaOrder.ASC),20);
    }

    private List<User> calculateMostEfficientDrivers(List<User> users, Integer month, Integer year) {

        List<Rateable> list = driversIn(users).stream().map(user -> ((Rateable) user)).collect(Collectors.toList());
        RankingEfficiency rankingCriteria = new RankingEfficiency(year, month, CriteriaOrder.ASC);

        return getSortedByCriteria(list, rankingCriteria, 20).stream()
                .map(rateable -> (User) rateable)
                .collect(Collectors.toList());
    }

    private List<Rateable> getSortedByCriteria(List<Rateable> list, RankingCriteria rc, Integer limitCount)
    {
        Collections.sort(list,rc);
        Integer limit = list.size() <= limitCount-1 ? list.size() : limitCount ;

        return list.subList(0,limit);
    }

    private List<User> passengerIn(List<User> users) {
        return users.stream().filter(user -> user.isPassenger()).collect(Collectors.toList());
    }

    private List<User> driversIn(List<User> users){
        return users.stream().filter(user -> user.isDriver()).collect(Collectors.toList());
    }

    private List<Vehicle> getVehicles(List<User> users) {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        users.stream().forEach(user -> vehicles.add(user.getVehicle()));
        return vehicles;
    }

    public List<Rateable> getBestDrivers() {
        return bestDrivers;
    }

    public List<Rateable> getBestPassenger() {
        return bestPassenger;
    }

    public List<Rateable> getBestVehicles() {
        return bestVehicles;
    }

    public List<Rateable> getWorstDrivers() {
        return worstDrivers;
    }

    public List<Rateable> getWorstPassenger() {
        return worstPassenger;
    }

    public List<Rateable> getWorstVehicles() {
        return worstVehicles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
