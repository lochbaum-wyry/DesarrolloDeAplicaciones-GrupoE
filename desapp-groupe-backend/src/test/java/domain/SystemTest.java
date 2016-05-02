package domain;

public class SystemTest {

/*
    @Test
    public void testFindUserByRoute_whenNoRoutesFoundResultIsEmpty(){

        List<Route> routes = new ArrayList<Route>();
        List<User> users = new ArrayList<User>();

        Route route = RouteBuilder.aRoute().withLocationAt(100.0,239.0).withLocationAt(150.0,300.0).build();
        routes.add(route);

        User user = UserBuilder.aUser().withRoutes(routes).build();
        users.add(user);

        System system = SystemBuilder.aSystem().withUsers(users).build();

        RoutePoint initiallocation = RoutePointBuilder.aRoutePoint().withLongitude(230.0).withLatitude(400.0).build();
        RoutePoint endinglocation = RoutePointBuilder.aRoutePoint().withLongitude(300.0).withLatitude(450.0).build();

        List<Route> received = system.findRoutesSatisfying(new DateTime(), 1000,initiallocation,endinglocation, 200f);
        List<Route> expected = new ArrayList<Route>();

        Assert.assertEquals(expected,received);

    }

//    @Test
    public void testFindUserByRoute_whenARoutesIsFoundTheResultContainsThatRoute(){
        List<Route> routes = new ArrayList<Route>();
        List<User> users = new ArrayList<User>();

        Route route = RouteBuilder.aRoute().withLocationAt(100.0,100.0).withLocationAt(300.0,500.0).build();

        Schedule schedule = Mockito.mock(Schedule.class);
        Mockito.when(schedule.dayAndHourIsNear(Mockito.any(DateTime.class),Mockito.anyInt())).thenReturn(Boolean.TRUE);

        route.addSchedule(schedule);

        routes.add(route);

        User user = UserBuilder.aUser().withRoutes(routes).build();
        users.add(user);

        System system = SystemBuilder.aSystem().withUsers(users).build();

        RoutePoint initiallocation = RoutePointBuilder.aRoutePoint().withLongitude(200.0).withLatitude(200.0).build();
        RoutePoint endinglocation = RoutePointBuilder.aRoutePoint().withLongitude(230.0).withLatitude(230.0).build();

        List<Route> received = system.findRoutesSatisfying(new DateTime(),1000,initiallocation,endinglocation, 200f);
        List<Route> expected = new ArrayList<Route>();
        expected.add(route);

        Assert.assertEquals(expected,received);

    }
    */

}
