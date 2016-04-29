package domain;

import domain.services.System;

import java.lang.*;
import java.util.*;


public class User  extends Entity
{
    private int id;
    protected System system ;
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private Vehicle vehicle ;
    private List<Route> routes ;
    private Integer points;

    private Integer totalRateCount = 0 ;
    private Integer goodRateCount = 0 ;
    private Integer badRateCount = 0 ;


    public User(String name, String lastName, String userName, String email)
    {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.routes = new ArrayList<Route>();
        this.points = 0;
    }

    public void setSystem(System system)
    {
        this.system = system ;
    }

    public User(String name,String lastName,String userName,String email,Vehicle vehicle)
    {
        this(name, lastName, userName, email);
        this.vehicle = vehicle;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }


    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public List<Route> getRoutes()
    {
        return routes;
    }

    public void addRoute(Route route)
    {
        routes.add(route);
    }


    public void addPoints(Integer points) {
        this.points= this.points + points;
    }

    public boolean hasVehicle() {
        return getVehicle().getClass().equals(Vehicle.class);
    }

    public boolean isPassenger() {
        return getVehicle() == null;
    }


    public void updateRateCounters(Rate rate)
    {
        this.setTotalRateCount(this.getTotalRateCount() + 1);
        switch (rate.getValue())
        {
            case GOOD:
                this.setGoodRateCount(this.getGoodRateCount() + 1);
                break;
            case BAD:
                this.setBadRateCount(this.getBadRateCount() + 1);
                break;
        }
    }

    public Integer getTotalRateCount()
    {
        return totalRateCount;
    }

    public void setTotalRateCount(Integer totalRateCount)
    {
        this.totalRateCount = totalRateCount;
    }

    public Integer getGoodRateCount()
    {
        return goodRateCount;
    }

    public void setGoodRateCount(Integer goodRateCount)
    {
        this.goodRateCount = goodRateCount;
    }

    public Integer getBadRateCount()
    {
        return badRateCount;
    }

    public void setBadRateCount(Integer badRateCount)
    {
        this.badRateCount = badRateCount;
    }

    Float getGoodRatesPercentage()
    {
        return ((float)(getGoodRateCount() * 100)) / getTotalRateCount();
    }

    Float getBadRatesPercentage()
    {
        return ((float)getBadRateCount() * 100) / getTotalRateCount();
    }
}
