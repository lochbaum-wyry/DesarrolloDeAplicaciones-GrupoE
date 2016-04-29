package domain;

import domain.exceptions.NoSeatsAvailableException;
import domain.rating_service.Rate;

import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;



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
    private List<Chat> chats;

    private Integer totalRateCount = 0 ;
    private Integer goodRateCount = 0 ;
    private Integer badRateCount = 0 ;


    public User(String name, String lastName, String userName, String email)
    {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.vehicle = null;
        this.routes = new ArrayList<Route>();
        this.points = 0;
        this.chats = new ArrayList<Chat>();
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

    public Vehicle getVehicle()
    {
        return vehicle;
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
