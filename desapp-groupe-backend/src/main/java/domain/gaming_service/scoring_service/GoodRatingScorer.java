package domain.gaming_service.scoring_service;

import domain.User;
import domain.rating_service.Rate;
import domain.rating_service.RateValue;

public class GoodRatingScorer extends RateEventScorer
{
    public Boolean canApplyTo(User user)
    {
        return false ;
    }

    public Boolean canApplyTo(User user, Rate rate)
    {
        return  rate.getRide().isDriver(user) &&
                rate.getValue().equals(RateValue.GOOD);
    }

    public Integer calculatePointsFor(User user)
    {
        return 500;
    }

}
