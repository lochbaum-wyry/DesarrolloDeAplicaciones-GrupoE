package domain;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;


public class GoodRateTest
{
    @Test
    public void test_new()
    {
        User user = mock(User.class);
        Ride ride = mock(Ride.class);
        String comment = "un tipo groso";
        Rate rate = new GoodRate(user, ride, comment);
        Assert.assertTrue(rate instanceof  GoodRate);
    }
}

