package domain;
import domain.builders.RateBuilder;
import domain.rating_service.Rate;
import domain.rating_service.RateValue;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class RateTest {
    @Test
    public void test_getValue(){
        User user = Mockito.mock(User.class);
        Ride ride = Mockito.mock(Ride.class);
        Rate rate = RateBuilder.aRate()
                .withUser(user)
                .withRateValue(RateValue.GOOD)
                .withComment("hola")
                .withRide(ride)
                .build();

        Assert.assertEquals(rate.getValue(),RateValue.GOOD);
    }

    @Test
    public void test_getComment(){
        User user = Mockito.mock(User.class);
        Ride ride = Mockito.mock(Ride.class);
        Rate rate = RateBuilder.aRate()
                .withUser(user)
                .withRateValue(RateValue.GOOD)
                .withComment("hola")
                .withRide(ride)
                .build();

        Assert.assertEquals(rate.getComment(),"hola");
    }

    @Test
    public void test_getUser(){
        User user = Mockito.mock(User.class);
        Ride ride = Mockito.mock(Ride.class);
        Rate rate = RateBuilder.aRate()
                .withUser(user)
                .withRateValue(RateValue.GOOD)
                .withComment("hola")
                .withRide(ride)
                .build();

        Assert.assertEquals(rate.getRater(),user);
    }

    @Test
    public void test_getRide(){
        User user = Mockito.mock(User.class);
        Ride ride = Mockito.mock(Ride.class);
        Rate rate = RateBuilder.aRate()
                .withUser(user)
                .withRateValue(RateValue.GOOD)
                .withComment("hola")
                .withRide(ride)
                .build();

        Assert.assertEquals(rate.getRide(),ride);
    }

    @Test
    public void test_getMonth(){
        User user = Mockito.mock(User.class);
        Ride ride = Mockito.mock(Ride.class);
        Rate rate = RateBuilder.aRate()
                .withUser(user)
                .withRateValue(RateValue.GOOD)
                .withComment("hola")
                .withRide(ride)
                .build();

        Assert.assertEquals(rate.getMonth().intValue(),4);
    }


    @Test
    public void test_getYear(){
        User user = Mockito.mock(User.class);
        Ride ride = Mockito.mock(Ride.class);
        Rate rate = RateBuilder.aRate()
                .withUser(user)
                .withRateValue(RateValue.GOOD)
                .withComment("hola")
                .withRide(ride)
                .build();

        Assert.assertEquals(rate.getYear().intValue(),2016);
    }


}
