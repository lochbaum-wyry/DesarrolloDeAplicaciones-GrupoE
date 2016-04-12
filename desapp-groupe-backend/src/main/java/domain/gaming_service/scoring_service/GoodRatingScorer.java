package domain.gaming_service.scoring_service;

import domain.GoodRate;
import domain.Rate;
import domain.RateValue;
import domain.User;

public class GoodRatingScorer extends RateEventScorer
{
    public Boolean canApplyTo(User user)
    {
        return false ;
    }

    public Boolean canApplyTo(User user, Rate rate)
    {
        return  rate.getRide().isDriver(user) &&
                rate instanceof GoodRate;
    }

    public Integer calculatePointsFor(User user)
    {
        return 500;
    }

}
