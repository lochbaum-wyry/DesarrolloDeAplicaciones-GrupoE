package domain;

import domain.builders.LocationBuilder;
import domain.builders.RouteBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class RouteTest
{

    @Test
    public void testlocationIsNearRouteWhenIsTrue(){
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        Location location = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();

        Boolean received = route.locationIsNearRoute(location,200f);
        Boolean expected = Boolean.TRUE;

        Assert.assertEquals(expected,received);
    }

    @Test
    public void testlocationIsNearRouteWhenIsFalse(){
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        Location location = LocationBuilder.aLocation().withLatitude(200.4).withLongitude(583.3).build();

        Boolean received = route.locationIsNearRoute(location, 200f);
        Boolean expected = Boolean.FALSE;

        Assert.assertEquals(expected,received);
    }

    @Test
    public void testmatchesRequestedRouteWhenisTrue(){
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        Location location1 = LocationBuilder.aLocation().withLatitude(30.1).withLongitude(56.3).build();
        Location location2 = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();

        Boolean received = route.matchesRequestedRoute(new DateTime(),1000,location1,location2, 200f);
        Boolean expected = Boolean.TRUE;

        Assert.assertEquals(expected,received);

    }

    @Test
    public void testmatchesRequestedRouteWhenisFalse(){
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        Location location1 = LocationBuilder.aLocation().withLatitude(200.3).withLongitude(56.2).build();
        Location location2 = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(500.4).build();

        Boolean received = route.matchesRequestedRoute(new DateTime(), 1000,location1,location2, 200f);
        Boolean expected = Boolean.FALSE;

        Assert.assertEquals(expected,received);

    }

}
