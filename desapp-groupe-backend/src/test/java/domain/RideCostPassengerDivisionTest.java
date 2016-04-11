package domain;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RideCostPassengerDivisionTest
{

    @Test
    public void test_calculate()
    {
        Ride ride = mock(Ride.class);
        when(ride.getTotalCost()).thenReturn(100f);
        when(ride.getNumberOfPassengers()).thenReturn(3);

        User passenger = mock(User.class);

        RideCostCalculator rideCostCalculator = new RideCostPassengerDivision(ride);

        Float expected = 25f;

        Assert.assertEquals(expected, rideCostCalculator.calculateCostForPassenger(passenger));
    }
}
