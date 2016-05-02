package domain;

import domain.builders.LocationBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.lang.*;

public class RoutePointTest
{

    @Test
    public void testisNearWhenIsTrue()
    {
        RoutePoint locationtest = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();
        RoutePoint routePoint = LocationBuilder.aLocation().withLatitude(38.6).withLongitude(40.4).build();

        Boolean received = locationtest.isNear(routePoint,200f);

        Assert.assertTrue(received);
    }

    @Test
    public void testisNearWhenIsFalse()
    {
        RoutePoint locationtest = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();
        RoutePoint routePoint = LocationBuilder.aLocation().withLatitude(200.4).withLongitude(583.3).build();

        Boolean received = locationtest.isNear(routePoint,200f);

        Assert.assertFalse(received);
    }

}
