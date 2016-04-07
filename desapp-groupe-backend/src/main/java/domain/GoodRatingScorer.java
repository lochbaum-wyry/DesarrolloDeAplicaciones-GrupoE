package domain;

public class GoodRatingScorer extends RateEventScorer{
    public Boolean canApplyTo(User user)
    {
        return true;
    }
    public Integer calculatePointsFor(User user)
    {
        return 500;
    }

}
