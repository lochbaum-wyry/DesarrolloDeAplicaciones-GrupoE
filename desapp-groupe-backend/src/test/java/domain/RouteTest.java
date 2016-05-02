package domain;

import domain.builders.RoutePointBuilder;
import domain.builders.RouteBuilder;
import domain.builders.ScheduleBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class RouteTest
{

    @Test
    public void test_locationIsNearRouteWhenIsTrue(){
        Route route = RouteBuilder.aRoute()
                .withRoutePointAt(34.5,21.4)
                .withRoutePointAt(59.3,100.2)
                .withRoutePointAt(56.4,87.6).build();

        RoutePoint routePoint = RoutePointBuilder.aRoutePoint().withLatitude(50.5).withLongitude(60.4).build();

        Boolean received = route.locationIsNearRoute(routePoint,200f);
        Boolean expected = Boolean.TRUE;

        Assert.assertEquals(expected,received);
    }

    @Test
    public void test_locationIsNearRouteWhenIsFalse(){
        Route route = RouteBuilder.aRoute()
                .withRoutePointAt(34.5,21.4)
                .withRoutePointAt(59.3,100.2)
                .withRoutePointAt(56.4,87.6)
                .build();

        RoutePoint routePoint = RoutePointBuilder.aRoutePoint().withLatitude(200.4).withLongitude(583.3).build();

        Boolean received = route.locationIsNearRoute(routePoint, 200f);
        Boolean expected = Boolean.FALSE;

        Assert.assertEquals(expected,received);
    }

    @Test
    public void test_matchesRequestedRouteWhenisTrue(){
        Route route = RouteBuilder.aRoute().withRoutePointAt(34.5,21.4).withRoutePointAt(59.3,100.2).withRoutePointAt(56.4,87.6).build();

        RoutePoint routePoint1 = RoutePointBuilder.aRoutePoint().withLatitude(30.1).withLongitude(56.3).build();
        RoutePoint routePoint2 = RoutePointBuilder.aRoutePoint().withLatitude(50.5).withLongitude(60.4).build();

        Boolean received = route.matchesRequestedRoute(routePoint1, routePoint2, 200f);
        Boolean expected = Boolean.TRUE;

        Assert.assertEquals(expected,received);

    }

    @Test
    public void test_matchesRequestedRouteWhenisFalse()
    {
        Route route = RouteBuilder.aRoute()
                .withRoutePointAt(34.5,21.4)
                .withRoutePointAt(59.3,100.2)
                .withRoutePointAt(56.4,87.6)
                .build();

        RoutePoint routePoint1 = RoutePointBuilder.aRoutePoint().withLatitude(200.3).withLongitude(56.2).build();
        RoutePoint routePoint2 = RoutePointBuilder.aRoutePoint().withLatitude(50.5).withLongitude(500.4).build();

        Boolean received = route.matchesRequestedRoute(routePoint1, routePoint2, 200f);
        Boolean expected = Boolean.FALSE;

        Assert.assertEquals(expected,received);

    }

    @Test
    public void test_Route_fixedCostsAreInitializedInZero()
    {
        Route route = RouteBuilder.aRoute()
                .build();

        Float expected = 0f;
        Assert.assertEquals(expected,route.getFixedCosts());

    }

    @Test
    public void test_getFixedCosts_returnsFixedCostSet()
    {
        Route route = RouteBuilder.aRoute()
                .withFixedCosts(120f)
                .build();

        Float expected = 120f;
        Assert.assertEquals(expected,route.getFixedCosts());
    }

    @Test
    public void test_setFixedCosts_setsFixedCostsToAValue()
    {
        Route route = RouteBuilder.aRoute().build();

        route.setFixedCosts(120f);
        Float expected = 120f;
        Assert.assertEquals(expected,route.getFixedCosts());
    }


    // idstance
    @Test
    public void test_Route_distanceInKmsIsInitializedInZero()
    {
        Route route = RouteBuilder.aRoute()
                .build();

        Float expected = 0f;
        Assert.assertEquals(expected,route.getDistanceInKms());

    }

    @Test
    public void test_getDistanceInKms_returnsDistanceInKmsSet()
    {
        Route route = RouteBuilder.aRoute()
                .withDistanceInKms(120f)
                .build();

        Float expected = 120f;
        Assert.assertEquals(expected,route.getFixedCosts());
    }

    @Test
    public void test_getDistanceInKms_setsDistanceInKmsToAValue()
    {
        Route route = RouteBuilder.aRoute().build();

        route.setDistanceInKms(120f);
        Float expected = 120f;
        Assert.assertEquals(expected,route.getDistanceInKms());
    }

    @Test
    public void test_setAndGetSchedules(){
        Route route = RouteBuilder.aRoute().build();

        List<Schedule> schedules = new ArrayList<Schedule>();
        route.setSchedules(schedules);
        route.addSchedule(ScheduleBuilder.aSchedule().build());

        Assert.assertEquals(route.getSchedules(),schedules);
    }

    @Test
    public void test_setAndGetRoutePoints(){
        Route route = RouteBuilder.aRoute().build();

        List<RoutePoint> routePoints = new ArrayList<RoutePoint>();
        route.setRoutePoints(routePoints);
        route.addRoutePoint(RoutePointBuilder.aRoutePoint().build());

        Assert.assertEquals(route.getRoutePoints(),routePoints);
    }

    @Test
    public void test_isInDayAndHour(){
        Route route = RouteBuilder.aRoute().build();
        route.addSchedule(ScheduleBuilder.aSchedule().withDay(DayOfWeek.WEDNESDAY).withDepartureTime(new DateTime(23,4,5,6,7)).withArrivalTime(new DateTime(2,5,7,9,2,3)).build());

        Assert.assertFalse(route.isInDayAndHour(new DateTime(23,4,5,6,7),1));
    }

}
