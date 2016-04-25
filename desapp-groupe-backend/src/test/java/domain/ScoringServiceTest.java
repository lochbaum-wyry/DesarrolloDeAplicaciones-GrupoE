package domain;

import domain.builders.ScoringSystemBuilder;
import domain.gaming_service.scoring_service.BadRatingScorer;
import domain.gaming_service.scoring_service.GoodRatingScorer;
import domain.gaming_service.scoring_service.Scorer;
import domain.gaming_service.scoring_service.ScoringService;
import domain.rating_service.Rate;
import domain.rating_service.RateValue;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ScoringServiceTest extends AbstractDomainTest
{
//
//    @Test
//    public void test_applyRateEventScorers_whenAUserIsRatedGoodThenItAddsPointsToUser()
//    {
//        GoodRatingScorer scorer = new GoodRatingScorer();
//        ScoringService scoringService = ScoringSystemBuilder
//                .aScoringSystem()
//                .withScorer(scorer)
//                .build();
//
//        User user = mock(User.class);
//        when(user.getGoodRateCount()).thenReturn(1);
//
//        Ride ride = mock(Ride.class);
//        when(ride.getDriver()).thenReturn(user);
//        when(ride.isDriver(user)).thenReturn(true);
//
//        Rate rate = mock(GoodRate.class);
//        when(rate.getValue()).thenReturn(RateValue.GOOD);
//        when(rate.getRide()).thenReturn(ride);
//
//        scoringService.applyRateEventScorers(user, rate);
//
//        Integer pointsToBeAdded = scorer.calculatePointsFor(user);
//        verify(user,times(1)).addPoints(pointsToBeAdded);
//    }
//
//    @Test
//    public void test_applyRateEventScorers_whenAUserIsRatedBadOnlyOnceThenItDoesNotAddPointsToUser()
//    {
//
//        BadRatingScorer scorer = new BadRatingScorer();
//
//        ScoringService scoringService = ScoringSystemBuilder
//                .aScoringSystem()
//                .withScorer(scorer)
//                .build();
//
//        User user = mock(User.class);
//        when(user.getBadRateCount()).thenReturn(1);
//
//        Ride ride = mock(Ride.class);
//        when(ride.getDriver()).thenReturn(user);
//        when(ride.isDriver(user)).thenReturn(true);
//
//        Rate rate = mock(BadRate.class);
//        when(rate.getValue()).thenReturn(RateValue.BAD);
//        when(rate.getRide()).thenReturn(ride);
//
//        scoringService.applyRateEventScorers(user, rate);
//
//        Integer pointsToBeAdded = scorer.calculatePointsFor(user);
//        verify(user,times(0)).addPoints(pointsToBeAdded);
//    }
//
//    @Test
//    public void test_applyRateEventScorers_whenAUserIsRatedBadTwiceThenItSubstractsPointsToUser()
//    {
//        BadRatingScorer scorer = new BadRatingScorer();
//
//        ScoringService scoringService = ScoringSystemBuilder
//                .aScoringSystem()
//                .withScorer(scorer)
//                .build();
//
//        User user = mock(User.class);
//        when(user.getBadRateCount()).thenReturn(2);
//
//        Ride ride = mock(Ride.class);
//        when(ride.getDriver()).thenReturn(user);
//        when(ride.isDriver(user)).thenReturn(true);
//
//        Rate rate = mock(Rate.class);
//        when(rate.getValue()).thenReturn(RateValue.BAD);
//        when(rate.getRide()).thenReturn(ride);
//
//        scoringService.applyRateEventScorers(user, rate);
//
//        Integer pointsToBeAdded = scorer.calculatePointsFor(user);
//        verify(user,times(1)).addPoints(pointsToBeAdded);
//    }
//
//    @Test
//    public void test_applyRateEventScorers_integration_whenOneGoodAndTwoBadRatesAddedUserIsAddedPoints500AndSubstracted1000Points()
//    {
//        Scorer badRatingScorer = new BadRatingScorer();
//        Scorer goodRatingScorer = new GoodRatingScorer();
//
//        ScoringService scoringService = ScoringSystemBuilder
//                .aScoringSystem()
//                .withScorer(goodRatingScorer)
//                .withScorer(badRatingScorer)
//                .build();
//
//        User user = driverWithVehicle(2);
//
//        Ride ride = mock(Ride.class);
//        when(ride.getDriver()).thenReturn(user);
//        when(ride.isDriver(user)).thenReturn(true);
//
//        Rate goodRate = mock(GoodRate.class);
//        when(goodRate.getValue()).thenReturn(RateValue.GOOD);
//        when(goodRate.getRide()).thenReturn(ride);
//
//        Rate badRate1 = mock(BadRate.class);
//        when(badRate1.getValue()).thenReturn(RateValue.BAD);
//        when(badRate1.getRide()).thenReturn(ride);
//
//        Rate badRate2 = mock(BadRate.class);
//        when(badRate2.getValue()).thenReturn(RateValue.BAD);
//        when(badRate2.getRide()).thenReturn(ride);
//
//        Integer expected = 0 ;
//
//        Integer pointsToBeAdded = goodRatingScorer.calculatePointsFor(user);
//        Integer pointsToBeSubstracted = badRatingScorer.calculatePointsFor(user);
//
//        user.addRate(goodRate);
//        scoringService.applyRateEventScorers(user, goodRate);
//
//        expected = pointsToBeAdded + expected ;
//        Assert.assertEquals(expected, user.getPoints());
//
//        user.addRate(badRate1);
//        scoringService.applyRateEventScorers(user, badRate1);
//
//        Assert.assertEquals(expected, user.getPoints());
//
//        user.addRate(badRate2);
//        scoringService.applyRateEventScorers(user, badRate2);
//
//        expected = pointsToBeSubstracted + expected ;
//        Assert.assertEquals(expected, user.getPoints());
//
//
//    }

}
