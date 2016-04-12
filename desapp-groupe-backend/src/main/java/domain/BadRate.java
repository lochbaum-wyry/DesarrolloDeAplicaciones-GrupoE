package domain;

/**
 * Created by prospero on 4/12/16.
 */
public class BadRate extends Rate
{
    public BadRate(User user, Ride ride, String comment) {
        super(user, ride, RateValue.BAD, comment);
    }
}
