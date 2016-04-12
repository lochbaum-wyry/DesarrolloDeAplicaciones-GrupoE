package domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by prospero on 4/12/16.
 */
public class VehicleTest
{
    @Test
    public void test_getOilUsePerKmInLts_returnsOilUsePerKmsInLtsSet()
    {
        Float oilWastePerKm = 0.05f;
        Vehicle vehicle = new Vehicle(1, oilWastePerKm);

        Assert.assertEquals(oilWastePerKm,vehicle.getOilUsePerKmInLts());

    }
}