package domain;

import domain.builders.RoutePointBuilder;
import domain.builders.RouteBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.lang.*;

public class RoutePointTest
{

    @Test
    public void testisNearWhenIsTrue()
    {
        RoutePoint locationtest = RoutePointBuilder.aRoutePoint().withLatitude(50.5).withLongitude(60.4).build();
        RoutePoint routePoint = RoutePointBuilder.aRoutePoint().withLatitude(38.6).withLongitude(40.4).build();

        Boolean received = locationtest.isNear(routePoint,200f);

        Assert.assertTrue(received);
    }

    @Test
    public void test_isNearWhenIsFalse()
    {
        RoutePoint locationtest = RoutePointBuilder.aRoutePoint().withLatitude(50.5).withLongitude(60.4).build();
        RoutePoint routePoint = RoutePointBuilder.aRoutePoint().withLatitude(200.4).withLongitude(583.3).build();

        Boolean received = locationtest.isNear(routePoint,200f);

        Assert.assertFalse(received);
    }

    @Test
    public void test_isSameLatLng(){
        Route route = RouteBuilder.aRoute().build();
        RoutePoint routePoint = new RoutePoint(route,3d,4d);

        Assert.assertTrue(routePoint.isSameLatLng(3.0,4.0));
    }

}
