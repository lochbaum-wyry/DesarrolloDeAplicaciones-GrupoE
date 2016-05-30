package domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class User  extends Entity
{
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private Vehicle vehicle ;
    private List<Route> routes ;
    private Integer points;
    private List<Chat> chats;
    private String image;

    private Integer totalRateCount = 0 ;
    private Integer goodRateCount = 0 ;
    private Integer badRateCount = 0 ;

    public User(){
    }

    public User(String name, String lastName, String userName, String email)
    {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.routes = new ArrayList<Route>();
        this.chats = new ArrayList<Chat>();
        this.points = 0;
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    @JsonIgnore
    public boolean hasVehicle() {
        return getVehicle().getClass().equals(Vehicle.class);
    }

    @JsonIgnore
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

    @JsonIgnore
    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public void addChat(Chat chat){
        chats.add(chat);
    }

    @JsonIgnore
    public Chat getOrAddChatWith(User user)
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

}
