package domain;

import domain.exceptions.NoSeatsAvailableException;

import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;


public class User implements Rateable{

    private String name;
    private String lastName;
    private String userName;
    private String email;
    private Vehicle vehicle ;
    private List<Route> routes ;
    private List<Ride> rides;
    private List<RideRequest> rideRequests;
    private List<RideRequest> requestedRides;
    private Integer points;
    private List<Chat> chats;
    private List<Rate> rates;

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
        this.points = 0;
        this.chats = new ArrayList<Chat>();
        this.rates = new ArrayList<Rate>();
    }

    @Override
    public List<Rate> getRates() {
        return rates;
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

    public void rejectRideRequest(RideRequest rideRequest)
    {
        rideRequest.reject();
        this.removeRideRequest(rideRequest);
    }

    public void rateUser(User user, Ride ride, RateValue rateValue, String comment)
    {
        Rate rate = new Rate(this, ride, rateValue, comment);
        user.addRate(rate);
    }

    public void rateCar(Ride ride, RateValue rateValue, String comment)
    {
        Rate rate = new Rate(this, ride, rateValue, comment);
        ride.getVehicle().addRate(rate);
    }

    public void sendMessage(User user, String content)
    {
        Chat chat = this.getOrAddChatWith(user);
        chat.addMessage(this,content);
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }


    private Chat getOrAddChatWith(User user)
    {
        Chat chat ;
        Optional<Chat> maybeChat = getChatWith(user);
        if(maybeChat.isPresent())
        {
            chat = maybeChat.get();
        } else
        {
            chat = new Chat(this.name.toString(),this,user);
            addChat(chat);
            user.addChat(chat);
        }
        return chat;
    }

    private Optional<Chat> getChatWith(User user)
    {
        return this.getChats().stream().filter(p -> p.getUsers().contains(user)).findFirst();
    }

    private void addChat(Chat newchat)
    {
        this.getChats().add(newchat);
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
            ride = createRideForRideRequest(rideRequest);
            addRide(ride);
        }
        return ride;
    }

    private Ride createRideForRideRequest(RideRequest rideRequest)
    {
        Ride ride = Ride.fromRideRequest(this,rideRequest);
        ride.setOilPrice( SystemSettings.getInstance().getOilPrice() );
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

    public List<Route> getRoutes()
    {
        return routes;
    }

    public void addRoute(Route route)
    {
        routes.add(route);
    }

    public void addRide(Ride ride)
    {
        rides.add(ride);
    }

    public void addPoints(Integer points) {
        this.points= this.points + points;
    }

    public boolean isDriver() {
        return getVehicle().getClass().equals(Vehicle.class);
    }

    public boolean isPassenger() {
        return getVehicle() == null;
    }

    public List<Ride> getRidesAsDriver() {
        return this.getRides().stream()
                .filter(ride -> ride.isDriver(this))
                .collect(Collectors.toList());
    }

    public Integer getRidesCount() {
        return this.getRides().size();
    }
}
