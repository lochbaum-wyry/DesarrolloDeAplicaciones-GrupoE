package domain;
import domain.builders.RateBuilder;
import domain.builders.VehicleBuilder;
import org.joda.time.DateTime;
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

        Assert.assertEquals(rate.getRateValue(),RateValue.GOOD);

        rate.setRateValue(RateValue.BAD);

        Assert.assertEquals(rate.getRateValue(),RateValue.BAD);
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

        rate.setComment("fede");

        Assert.assertEquals(rate.getComment(),"fede");
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

        User user2 = Mockito.mock(User.class);
        rate.setRater(user2);

        Assert.assertEquals(rate.getRater(),user2);
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

        Ride ride2 = Mockito.mock(Ride.class);

        rate.setRide(ride2);

        Assert.assertEquals(rate.getRide(),ride2);
    }

    @Test
    public void test_getMonth()
    {
        User user = Mockito.mock(User.class);
        Ride ride = Mockito.mock(Ride.class);
        Rate rate = RateBuilder.aRate()
                .withUser(user)
                .withRateValue(RateValue.GOOD)
                .withComment("hola")
                .withRide(ride)
                .withDate(new DateTime(2016,4,5,0,0,1))
                .build();

        Integer expectedMonth = 4;
        Assert.assertEquals(expectedMonth,rate.getMonth());

        rate.setDate(new DateTime(2,3,4,5,6));

        Integer expectedMonth2 = 3;
        Assert.assertEquals(expectedMonth2,rate.getMonth());
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
                .withDate(new DateTime(2016,4,5,0,0,1))
                .build();

        Integer expectedYear = 2016;
        Assert.assertEquals(expectedYear, rate.getYear());

        rate.setDate(new DateTime(2,3,4,5,6));

        Integer expectedYear2 = 3;
        Assert.assertEquals(expectedYear2,rate.getMonth());
    }

    @Test
    public void test_setAndGetRatedUser(){

        User user = Mockito.mock(User.class);
        Rate rate = RateBuilder.aRate().build();

        rate.setRatedUser(user);
        Assert.assertEquals(rate.getRatedUser(),user);

    }

    @Test
    public void test_setAndGetVehicle(){

        Vehicle vehicle = VehicleBuilder.aVehicle().build();
        Rate rate = RateBuilder.aRate().build();

        rate.setVehicle(vehicle);
        Assert.assertEquals(rate.getVehicle(),vehicle);

    }

    @Test
    public void test_setAndGetRateType(){
        Rate rate = RateBuilder.aRate().build();

        rate.setRateType(RateType.Accompany);
        Assert.assertEquals(rate.getRateType(),RateType.Accompany);

    }

    @Test
    public void test_setAndGetDate(){
        Rate rate = RateBuilder.aRate().build();

        DateTime date = new DateTime();
        rate.setDate(date);
        Assert.assertEquals(rate.getDate(),date);

    }


}