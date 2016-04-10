package domain;

public class BadRatingScorer extends RateEventScorer
{
    public Boolean canApplyTo(User user)
    {
        return isEven((int)user.getBadRateCount());
    }

    public Boolean canApplyTo(User user, Rate rate)
    {
        return rate.getRide().getDriver().equals(user) && this.canApplyTo(user);
    }

    private Boolean isEven(int number) {
        return ((number % 2) == 0) && (number != 0);
    }

    public Integer calculatePointsFor(User user)
    {
        return -1000;
    }

}
