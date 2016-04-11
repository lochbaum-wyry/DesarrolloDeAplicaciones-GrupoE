package domain;

import domain.builders.LocationBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.lang.*;

public class LocationTest
{

    @Test
    public void testisNearWhenIsTrue()
    {
        Location locationtest = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();
        Location location = LocationBuilder.aLocation().withLatitude(38.6).withLongitude(40.4).build();

        Boolean received = locationtest.isNear(location,200f);

        Assert.assertTrue(received);
    }

    @Test
    public void testisNearWhenIsFalse()
    {
        Location locationtest = LocationBuilder.aLocation().withLatitude(50.5).withLongitude(60.4).build();
        Location location = LocationBuilder.aLocation().withLatitude(200.4).withLongitude(583.3).build();

        Boolean received = locationtest.isNear(location,200f);

        Assert.assertFalse(received);
    }

    @Test
    public void test_equals_same_latitude_and_longitude_equals_true()
    {
        Location loc1 = LocationBuilder.aLocation().withLatitude(100.0).withLongitude(100.0).build();
        Location loc2 = LocationBuilder.aLocation().withLatitude(100.0).withLongitude(100.0).build();

        Assert.assertEquals(loc1, loc2);

    }

}
