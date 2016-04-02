package domain.builders;

import domain.Route;
import domain.User;
import domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class UserBuilder {
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private Vehicle vehicle;
    private List<Route> routes;

    public UserBuilder(){
        this.name = "";
        this.lastName = "";
        this.userName = "";
        this.email = "";
        this.vehicle = null;
        this.routes  = new ArrayList<Route>();
    }
    public static UserBuilder aUser(){
        return new UserBuilder();
    }

    public User build(){
        User user =  new User(name,lastName,userName,email,vehicle);
        routes.stream().forEach(route -> {user.addRoute(route);});
        return user;
    }

    public UserBuilder withName(String name){
        this.name = name;
        return this;
    }

    public UserBuilder withLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withUserName(String UserName){
        this.userName = userName;
        return this;
    }

    public UserBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public UserBuilder withVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        return this;
    }


    public UserBuilder withRoutes(List<Route> routes) {
        this.routes = routes;
        return this;
    }
}
