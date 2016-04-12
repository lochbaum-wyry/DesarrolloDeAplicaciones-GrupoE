package domain;

import domain.builders.LocationBuilder;
import domain.builders.RouteBuilder;
import org.junit.Assert;
import org.junit.Test;

public class RouteTest
{

    @Test
    public void test_locationIsNearRouteWhenIsTrue(){
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        Location location = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();

        Boolean received = route.locationIsNearRoute(location,200f);
        Boolean expected = Boolean.TRUE;

        Assert.assertEquals(expected,received);
    }

    @Test
    public void test_locationIsNearRouteWhenIsFalse(){
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        Location location = LocationBuilder.aLocation().withLatitude(200.4).withLongitude(583.3).build();

        Boolean received = route.locationIsNearRoute(location, 200f);
        Boolean expected = Boolean.FALSE;

        Assert.assertEquals(expected,received);
    }

    @Test
    public void test_matchesRequestedRouteWhenisTrue(){
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        Location location1 = LocationBuilder.aLocation().withLatitude(30.1).withLongitude(56.3).build();
        Location location2 = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();

        Boolean received = route.matchesRequestedRoute(location1,location2, 200f);
        Boolean expected = Boolean.TRUE;

        Assert.assertEquals(expected,received);

    }

    @Test
    public void test_matchesRequestedRouteWhenisFalse(){
        Route route = RouteBuilder.aRoute().withLocationAt(34.5,21.4).withLocationAt(59.3,100.2).withLocationAt(56.4,87.6).build();

        Location location1 = LocationBuilder.aLocation().withLatitude(200.3).withLongitude(56.2).build();
        Location location2 = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(500.4).build();

        Boolean received = route.matchesRequestedRoute(location1,location2, 200f);
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



}
