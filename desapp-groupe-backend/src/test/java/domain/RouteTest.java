package domain;

import domain.builders.RouteBuilder;
import domain.builders.RoutePointBuilder;
import domain.builders.ScheduleBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteTest
{

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

}
