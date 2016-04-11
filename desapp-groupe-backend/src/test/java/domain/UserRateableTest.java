package domain;

import domain.builders.UserBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.lang.*;

public class UserRateableTest {

    @Test
    public void test_getBadRates(){
        User luis = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();

        Rate goodRate = Mockito.mock(Rate.class);
        Mockito.when(goodRate.getValue()).thenReturn(RateValue.GOOD);

        Rate badRate = Mockito.mock(Rate.class);
        Mockito.when(badRate.getValue()).thenReturn(RateValue.BAD);

        luis.addRate(goodRate);
        luis.addRate(badRate);
        luis.addRate(badRate);
        luis.addRate(goodRate);
        luis.addRate(goodRate);

        long countBadRates = luis.getBadRateCount();
        long countGoodRates = luis.getGoodRateCount();

        Assert.assertEquals(countGoodRates,3);
        Assert.assertEquals(countBadRates,2);
    }

    @Test
    public void test_rateIsOfMonthYear_ReturnTrueIfaRateIsSameYearAndMonth(){
        User user1 = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();

        Rate goodRate = Mockito.mock(Rate.class);
        Mockito.when(goodRate.getMonth()).thenReturn(2);
        Mockito.when(goodRate.getYear()).thenReturn(2016);

        Boolean expected = user1.rateIsOfMonthYear(goodRate, 2, 2016);
        Assert.assertTrue(expected);
    }

    @Test
    public void test_rateIsOfMonthYear_ReturnFalseIfaRateIsDifferentYearAndMonth(){
        User user2 = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();

        Rate goodRate = Mockito.mock(Rate.class);
        Mockito.when(goodRate.getMonth()).thenReturn(2);
        Mockito.when(goodRate.getYear()).thenReturn(2016);

        Boolean expected = user2.rateIsOfMonthYear(goodRate, 4, 2015);
        Assert.assertFalse(expected);
    }

    @Test
    public void test_ratesInMonthYear_ReturnAllRatesInaMonthAndAyear(){
        User user = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();

        Rate rate = Mockito.mock(Rate.class);
        Mockito.when(rate.getMonth()).thenReturn(2);
        Mockito.when(rate.getYear()).thenReturn(2016);

        Rate rate2 = Mockito.mock(Rate.class);
        Mockito.when(rate2.getMonth()).thenReturn(3);
        Mockito.when(rate2.getYear()).thenReturn(2016);

        user.addRate(rate);
        user.addRate(rate2);
        user.addRate(rate);
        user.addRate(rate2);
        user.addRate(rate);

        Assert.assertEquals(user.ratesInMonthYear(user,2,2016).size(),3);
    }

    @Test
    public void test_getGoodRatesPercentage_whenAuserHave75GoodRatesPercentage(){
        User luis = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();

        Rate goodRate = Mockito.mock(Rate.class);
        Mockito.when(goodRate.getValue()).thenReturn(RateValue.GOOD);

        Rate badRate = Mockito.mock(Rate.class);
        Mockito.when(badRate.getValue()).thenReturn(RateValue.BAD);

        luis.addRate(goodRate);
        luis.addRate(badRate);
        luis.addRate(goodRate);
        luis.addRate(goodRate);

        Assert.assertEquals(luis.getGoodRatesPercentage().intValue(),75);
    }

    @Test
    public void test_getBadRatesPercentage_whenAuserHave66BadRatesPercentage(){
        User luis = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();

        Rate goodRate = Mockito.mock(Rate.class);
        Mockito.when(goodRate.getValue()).thenReturn(RateValue.GOOD);

        Rate badRate = Mockito.mock(Rate.class);
        Mockito.when(badRate.getValue()).thenReturn(RateValue.BAD);

        luis.addRate(goodRate);
        luis.addRate(badRate);
        luis.addRate(badRate);
        luis.addRate(badRate);
        luis.addRate(badRate);
        luis.addRate(goodRate);

        Assert.assertEquals(luis.getBadRatesPercentage().intValue(),66);
    }

    @Test
    public void test_CountsRates(){
        User luis = UserBuilder.aUser()
                .withName("fede")
                .withUserName("trimegisto")
                .withLastName("lochbaum")
                .withEmail("EmailBuilder.aEmail.build()")
                .build();

        Rate goodRate = Mockito.mock(Rate.class);
        Mockito.when(goodRate.getValue()).thenReturn(RateValue.GOOD);

        Rate badRate = Mockito.mock(Rate.class);
        Mockito.when(badRate.getValue()).thenReturn(RateValue.BAD);

        luis.addRate(goodRate);
        luis.addRate(badRate);
        luis.addRate(badRate);
        luis.addRate(badRate);
        luis.addRate(badRate);
        luis.addRate(goodRate);

        Assert.assertEquals(luis.getGoodRateCount(),(Integer)2);
        Assert.assertEquals(luis.getBadRateCount(),(Integer)4);
        Assert.assertEquals(luis.getTotalRateCount(),(Integer)6);
    }
}
