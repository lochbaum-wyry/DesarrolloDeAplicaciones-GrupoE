package domain.service_tests;

import domain.LatLng;
import domain.Route;
import domain.Schedule;
import domain.builders.RouteBuilder;
import domain.builders.ScheduleBuilder;
import domain.repositories.RouteRepository;
import domain.services.RideProposal;
import domain.services.RouteService;
import domain.services.UserService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteServiceTest extends AbstractServiceTest
{
    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteRepository routeRepo;

    @Autowired
    private UserService userService ;

    @Test
    public void test_getRideProposalsForRouteSchedulesCloseTo_whenARoutesExistsWithinClosenessInMtsAndGivenDepartureAndArrivalPointsTheResultIsNotEmpty()
    {
        List<Route> routes = new ArrayList<Route>();

        Route route = RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePointAt(-58.2907153,-34.7035576)
                .withRoutePointAt(-58.3435821,-34.6760004)
                .build();

        Schedule schedule = ScheduleBuilder.aSchedule()
                .withDay(DayOfWeek.FRIDAY)
                .withDepartureTimeAt(9,30)
                .withArrivalTimeAt(10,30)
                .build();

        route.addSchedule(schedule);

        routeRepo.save(route);

        DateTime date = new DateTime(2016,5,6,8,45);
        int secondsDateCloseness = 3600;
        Double closenessInMts = 295d;
        LatLng departurePoint = new LatLng(-34.702742,-58.2937437);
        LatLng arrivalPoint = new LatLng(-34.6753329,-58.342759);

        List received = routeService.getRideProposalsForRouteSchedulesCloseTo(date, secondsDateCloseness, departurePoint, arrivalPoint, closenessInMts);
        assertFalse(received.isEmpty());
    }


    @Test
    public void test_getRideProposalsForRouteSchedulesCloseTo_whenARoutesDoesNotExistWithinClosenessInMtsAndGivenDepartureAndArrivalPointsTheResultIsEmpty()
    {
        List<Route> routes = new ArrayList<Route>();

        Route route = RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePointAt(-58.2907153,-34.7035576)
                .withRoutePointAt(-58.3435821,-34.6760004)
                .build();

        Schedule schedule = ScheduleBuilder.aSchedule()
                .withDay(DayOfWeek.FRIDAY)
                .withDepartureTimeAt(9,30)
                .withArrivalTimeAt(10,30)
                .build();

        route.addSchedule(schedule);

        routeRepo.save(route);

        DateTime date = new DateTime(2016,5,6,8,45);
        int secondsDateCloseness = 3600;
        Double closenessInMts = 290d;
        LatLng departurePoint = new LatLng(-34.702742,-58.2937437);
        LatLng arrivalPoint = new LatLng(-34.6753329,-58.342759);

        List received = routeService.getRideProposalsForRouteSchedulesCloseTo(date, secondsDateCloseness, departurePoint, arrivalPoint, closenessInMts);
        assertTrue(received.isEmpty());
    }


    @Test
    public void test_getRideProposalsForRouteSchedulesCloseTo_whenARoutesDoesNotExistWithinSecondsClosenessTheResultIsEmpty()
    {
        List<Route> routes = new ArrayList<Route>();

        Route route = RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePointAt(-58.2907153,-34.7035576)
                .withRoutePointAt(-58.3435821,-34.6760004)
                .build();

        Schedule schedule = ScheduleBuilder.aSchedule()
                .withDay(DayOfWeek.FRIDAY)
                .withDepartureTimeAt(9,30)
                .withArrivalTimeAt(10,30)
                .build();

        route.addSchedule(schedule);

        routeRepo.save(route);

        DateTime date = new DateTime(2016,5,6,8,25);
        int secondsCloseness = 3600;
        Double closenessInMts = 1000d;
        LatLng departurePoint = new LatLng(-34.702742,-58.2937437);
        LatLng arrivalPoint = new LatLng(-34.6753329,-58.342759);

        List received = routeService.getRideProposalsForRouteSchedulesCloseTo(date, secondsCloseness, departurePoint, arrivalPoint, closenessInMts);
        assertTrue(received.isEmpty());
    }

    @Test
    public void test_getRideProposalsForRouteSchedulesCloseTo_whenARoutesExistsWithinSecondsClosenessTheResultIsNotEmpty()
    {
        List<Route> routes = new ArrayList<Route>();

        Route route = RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePointAt(-58.2907153,-34.7035576)
                .withRoutePointAt(-58.3435821,-34.6760004)
                .build();

        Schedule schedule = ScheduleBuilder.aSchedule()
                .withDay(DayOfWeek.FRIDAY)
                .withDepartureTimeAt(9,30)
                .withArrivalTimeAt(10,30)
                .build();

        route.addSchedule(schedule);

        routeRepo.save(route);

        DateTime date = new DateTime(2016,5,6,8,35);
        int secondsCloseness = 3600;
        Double closenessInMts = 1000d;
        LatLng departurePoint = new LatLng(-34.702742,-58.2937437);
        LatLng arrivalPoint = new LatLng(-34.6753329,-58.342759);

        List<RideProposal> received = routeService.getRideProposalsForRouteSchedulesCloseTo(date, secondsCloseness, departurePoint, arrivalPoint, closenessInMts);
        assertFalse(received.isEmpty());
    }

    @Test
    public void test_getRideProposalsForRouteSchedulesCloseTo_whenARoutesInTheSameDayOfWeekExistsTHenResultIsNotEmpty()
    {
        List<Route> routes = new ArrayList<Route>();

        Route route = RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePointAt(-58.2907153,-34.7035576)
                .withRoutePointAt(-58.3435821,-34.6760004)
                .build();

        Schedule schedule = ScheduleBuilder.aSchedule()
                .withDay(DayOfWeek.FRIDAY)
                .withDepartureTimeAt(9,30)
                .withArrivalTimeAt(10,30)
                .build();

        route.addSchedule(schedule);

        routeRepo.save(route);

        DateTime date = new DateTime(2016,5,6,8,35);
        int secondsCloseness = 3600;
        Double closenessInMts = 1000d;
        LatLng departurePoint = new LatLng(-34.702742,-58.2937437);
        LatLng arrivalPoint = new LatLng(-34.6753329,-58.342759);

        List received = routeService.getRideProposalsForRouteSchedulesCloseTo(date, secondsCloseness, departurePoint, arrivalPoint, closenessInMts);
        assertFalse(received.isEmpty());
    }


    @Test
    public void test_getRideProposalsForRouteSchedulesCloseTo_whenARoutesInTheSameDayOfWeekDoesNotExistTHenResultIsEmpty()
    {
        List<Route> routes = new ArrayList<Route>();

        Route route = RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePointAt(-58.2907153,-34.7035576)
                .withRoutePointAt(-58.3435821,-34.6760004)
                .build();

        Schedule schedule = ScheduleBuilder.aSchedule()
                .withDay(DayOfWeek.THURSDAY)
                .withDepartureTimeAt(9,30)
                .withArrivalTimeAt(10,30)
                .build();

        route.addSchedule(schedule);

        routeRepo.save(route);

        DateTime date = new DateTime(2016,5,6,8,35);
        int secondsCloseness = 3600;
        Double closenessInMts = 1000d;
        LatLng departurePoint = new LatLng(-34.702742,-58.2937437);
        LatLng arrivalPoint = new LatLng(-34.6753329,-58.342759);

        List received = routeService.getRideProposalsForRouteSchedulesCloseTo(date, secondsCloseness, departurePoint, arrivalPoint, closenessInMts);
        assertTrue(received.isEmpty());
    }

}
