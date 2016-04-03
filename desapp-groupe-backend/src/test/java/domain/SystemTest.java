package domain;

import domain.Location;
import domain.Route;
import domain.System;
import domain.User;
import domain.builders.LocationBuilder;
import domain.builders.RouteBuilder;
import domain.builders.SystemBuilder;
import domain.builders.UserBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SystemTest {

    @Test
    public void testSignUp(){

        System system = SystemBuilder.aSystem().build();
        User user = UserBuilder.aUser().build();

        system.signUp(user);

        Assert.assertTrue(system.getUsers().contains(user));
    }

    @Test
    public void testFindUserByRoute_whenNoRoutesFoundResultIsEmpty(){

        List<Route> routes = new ArrayList<Route>();
        List<User> users = new ArrayList<User>();

        Route route = RouteBuilder.aRoute().withLocationAt(100.0,239.0).withLocationAt(150.0,300.0).build();
        routes.add(route);

        User user = UserBuilder.aUser().withRoutes(routes).build();
        users.add(user);

        System system = SystemBuilder.aSystem().withUsers(users).build();

        Location initiallocation = LocationBuilder.aLocation().withLongitude(230.0).withLatitude(400.0).build();
        Location endinglocation = LocationBuilder.aLocation().withLongitude(300.0).withLatitude(450.0).build();

        List<Route> received = system.findRoutesSatisfying(new DateTime(),initiallocation,endinglocation);
        List<Route> expected = new ArrayList<Route>();

        Assert.assertEquals(expected,received);

    }

    @Test
    public void testFindUserByRoute_whenARoutesIsFoundTheResultContainsThatRoute(){
        List<Route> routes = new ArrayList<Route>();
        List<User> users = new ArrayList<User>();

        Route route = RouteBuilder.aRoute().withLocationAt(100.0,100.0).withLocationAt(300.0,500.0).build();

        routes.add(route);

        User user = UserBuilder.aUser().withRoutes(routes).build();
        users.add(user);

        System system = SystemBuilder.aSystem().withUsers(users).build();

        Location initiallocation = LocationBuilder.aLocation().withLongitude(200.0).withLatitude(200.0).build();
        Location endinglocation = LocationBuilder.aLocation().withLongitude(230.0).withLatitude(230.0).build();

        List<Route> received = system.findRoutesSatisfying(new DateTime(),initiallocation,endinglocation);
        List<Route> expected = new ArrayList<Route>();
        expected.add(route);

        Assert.assertEquals(expected,received);

    }

}