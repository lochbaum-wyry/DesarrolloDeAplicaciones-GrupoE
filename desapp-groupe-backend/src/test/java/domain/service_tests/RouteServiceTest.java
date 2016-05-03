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

        Route route = RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePointAt(100d,100d)
                .withRoutePointAt(300d,500d)
                .build();

//        DateTime departureTime = new DateTime(2016,5,1,9,30,0);
//        DateTime arrivalTime = new DateTime(2016,5,1,9,30,0);
//        Schedule schedule = new Schedule(DayOfWeek.FRIDAY, departureTime, arrivalTime);

        routeRepo.save(route);

        DateTime date = new DateTime();
        int secondsDateCloseness = 1000;
        float radioCloseness = 300f;
        LatLng departurePoint = new LatLng(200d,200d);
        LatLng arrivalPoint = new LatLng(230d,230d);

        java.lang.System.out.println(routeRepo.findAll());

        List<Route> received = routeService.findRoutesSatisfying(date, secondsDateCloseness, departurePoint, arrivalPoint, radioCloseness);

        java.lang.System.out.println(received);


    }



}
