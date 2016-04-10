package domain;

public class GoodRatingScorer extends RateEventScorer
{
    public Boolean canApplyTo(User user)
    {
        return user.getRates().get(user.getRates().size()-1).getValue().equals(RateValue.GOOD);
    }
    public Integer calculatePointsFor(User user)
    {
        return 500;
    }

}
