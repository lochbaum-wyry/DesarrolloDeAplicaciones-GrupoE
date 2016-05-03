package domain.service_tests;

import domain.*;
import domain.builders.RouteBuilder;
import domain.builders.RoutePointBuilder;
import domain.builders.UserBuilder;
import domain.repositories.RouteRepository;
import domain.services.RouteService;
import domain.services.UserService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RouteServiceTest extends AbstractServiceTest
{
    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteRepository routeRepo;

    @Autowired
    private UserService userService ;

    // TODO: hacer comparación por fecha
    @Test
    public void test_findRoutesSatisfying_whenARoutesIsFoundTheResultContainsThatRoute()
    {
        List<Route> routes = new ArrayList<Route>();

        // -34.703556,-58.2932116/-34.7022161,-58.2919911  // cramer 930, don bosco, buenos aires, argentina
        // -34.6942951,-58.3146194/-34.6961566,-58.3105088 //

        Route route = RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePointAt(-34.7035576,-58.2907153)
                .withRoutePointAt(-34.7035576,-58.2907153)
                .build();

//        DateTime departureTime = new DateTime(2016,5,1,9,30,0);
//        DateTime arrivalTime = new DateTime(2016,5,1,9,30,0);
//        Schedule schedule = new Schedule(DayOfWeek.FRIDAY, departureTime, arrivalTime);

        routeRepo.save(route);

        DateTime date = new DateTime();
        int secondsDateCloseness = 1000;
        Double closenessInMts = 300d;
        LatLng departurePoint = new LatLng(-34.702742,-58.2937437);
        LatLng arrivalPoint = new LatLng(-34.6961566,-58.3105088);

//        java.lang.System.out.println(routeRepo.findAll());

        List<Route> received = routeService.findRoutesSatisfying(date, secondsDateCloseness, departurePoint, arrivalPoint, closenessInMts);
        java.lang.System.out.println(received);

//        -34.7035576,-58.2907153/-34.702742,-58.2937437   -- 290m

//        Double c = -34.7035576 * 3.141592653589793 /180;
//        Double d = -58.2907153* 3.141592653589793 /180;
//        Double e = -34.702742* 3.141592653589793 /180;
//        Double f = -58.2937437* 3.141592653589793 /180;
//
//        double x = 6378137d * (2*Math.asin(Math.sqrt(Math.pow(Math.sin((c - e) / 2), 2) + Math.cos(c) * Math.cos(e) * Math.pow(Math.sin((d - f) / 2), 2))));
//        java.lang.System.out.println(x);




//        java.lang.System.out.println(received);


    }



}
