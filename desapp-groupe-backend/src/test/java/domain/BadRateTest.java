package domain;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class BadRateTest {
    @Test
    public void test_new()
    {
        User user = mock(User.class);
        Ride ride = mock(Ride.class);
        String comment = "Pesimo conductor";
        Rate rate = new BadRate(user, ride, comment);
        Assert.assertTrue(rate instanceof  BadRate);
    }
}
