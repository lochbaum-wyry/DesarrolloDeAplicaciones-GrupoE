package domain;

import domain.exceptions.NoSeatsAvailableException;

import java.lang.*;
import java.lang.System;
import java.util.*;


public class User {

    private String name;
    private String lastName;
    private String userName;
    private String email;
    private Vehicle vehicle ;
    private List<Route> routes ;
    private List<Ride> rides;
    private List<RideRequest> rideRequests;
    private List<RideRequest> requestedRides;
    private List<Rate> rates;
    private Integer points;
    private List<Chat> chats;
    private Wall wall;

    public User(String name,String lastName,String userName,String email)
    {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.vehicle = null;
        this.routes = new ArrayList<Route>();
        this.rides = new ArrayList<Ride>();
        this.rideRequests = new ArrayList<RideRequest>();
        this.requestedRides = new ArrayList<RideRequest>();
        this.rates = new ArrayList<Rate>();
        this.points = 0;
        this.chats = new ArrayList<Chat>();
        this.wall = new Wall();
    }

    public User(String name,String lastName,String userName,String email,Vehicle vehicle)
    {
        this(name, lastName, userName, email);
        this.vehicle = vehicle;
    }

    public void requestRide(User driver, RideRequest rideReq)
    {
        driver.addRideRequest(rideReq);
        this.addRequestedRide(rideReq);
    }

    public List<Route> getRoutes()
    {
        return routes;
    }

    public void rejectRideRequest(RideRequest rideRequest)
    {
        rideRequest.reject();
        this.removeRideRequest(rideRequest);
    }

    public void rate(User user, Ride ride, RateType rateType, RateValue rateValue, String comment)
    {
        Rate rate = new Rate(rateType, user, ride, rateValue, comment);
        user.addRate(rate);
    }

    public void addRate(Rate rate)
    {
        this.rates.add(rate);
    }

    public void sendMessage(User user, String content)
    {
        Optional<Chat> chat = getChatWith(user);

        if(chat.isPresent())
        {
            chat.get().addMessage(this, content);
        } else
        {
            Chat newchat = new Chat(this.name.toString(),this,user);
            addChat(newchat);
            user.addChat(newchat);
            newchat.addMessage(this,content);
        }
    }

    private Optional<Chat> getChatWith(User user)
    {
        return this.getChats().stream().filter(p -> p.getUsers().contains(user)).findFirst();
    }

    private void addChat(Chat newchat)
    {
        this.getChats().add(newchat);
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public Wall getWall(){
        return this.wall;
    }


    public void acceptRideRequest(RideRequest rideRequest) throws NoSeatsAvailableException {
        Ride ride = this.getOrAddRideForRequest(rideRequest);
        ride.takeSeat(rideRequest.getPassenger(), rideRequest.getBoardingAt(), rideRequest.getGetOffAt());
        this.removeRideRequest(rideRequest);
        rideRequest.accept();
    }

    private Ride getOrAddRideForRequest(RideRequest rideRequest)
    {
        Ride ride ;
        Optional<Ride> maybeRide = this.getRideSuitableForRideRequest(rideRequest);
        if (maybeRide.isPresent())
        {
            ride = maybeRide.get();
        } else {
            ride = Ride.fromRideRequest(this,rideRequest);
            addRide(ride);
        }
        return ride;
    }

    private Optional<Ride> getRideSuitableForRideRequest(RideRequest rideRequest)
    {
        return this.rides.stream().
            filter( ride -> ride.suitsRideRequest(rideRequest)).findFirst();
    }

    public void addRideRequest(RideRequest rideRequest)
    {
        this.rideRequests.add(rideRequest);
    }

    public void addRequestedRide(RideRequest rideRequest)
    {
        this.requestedRides.add(rideRequest);
    }

    public void removeRideRequest(RideRequest rideRequest)
    {
        this.rideRequests.remove(rideRequest);
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public List<Ride> getRides()
    {
        return rides;
    }

    public List<RideRequest> getRideRequests()
    {
        return rideRequests;
    }

    public List<Chat> getChats()
    {
        return chats;
    }

    public void addRoute(Route route)
    {
        routes.add(route);
    }

    public void addRide(Ride ride)
    {
        rides.add(ride);
    }
}
