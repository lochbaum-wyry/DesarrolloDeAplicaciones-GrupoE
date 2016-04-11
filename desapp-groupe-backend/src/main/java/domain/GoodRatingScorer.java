package domain;

public class GoodRatingScorer extends RateEventScorer
{
    public Boolean canApplyTo(User user)
    {
        return false ;
    }

    public Boolean canApplyTo(User user, Rate rate)
    {
        return rate.getRide().isDriver(user) && rate.getValue().equals(RateValue.GOOD);
    }

    public Integer calculatePointsFor(User user)
    {
        return 500;
    }

}
