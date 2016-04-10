package domain;

import domain.builders.RideBuilder;
import domain.builders.ScoringSystemBuilder;
import domain.builders.UserBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ScoringSystemTest {

    @Test
    public void test_applyRateEventScorers_whenAuserHaveaGodRate(){
        ScoringSystem scoringSystem = ScoringSystemBuilder.aScoringSystem().build();

        User user = UserBuilder.aUser().withPoints(500).build();

        Ride ride = Mockito.mock(Ride.class);
        user.rateUser(user,ride,RateValue.GOOD,"test");

        scoringSystem.applyRateEventScorers(user);

        Assert.assertEquals(user.getPoints().intValue(),1000);
    }

    @Test
    public void test_applyRateEventScorers_whenAuserHavetwoBadRate(){
        ScoringSystem scoringSystem = ScoringSystemBuilder.aScoringSystem().build();

        User user = UserBuilder.aUser().withPoints(0).build();

        Ride ride = Mockito.mock(Ride.class);
        user.rateUser(user,ride,RateValue.BAD,"test");
        user.rateUser(user,ride,RateValue.BAD,"test");

        scoringSystem.applyRateEventScorers(user);

        Assert.assertEquals(user.getPoints().intValue(),-1000);
    }
}
