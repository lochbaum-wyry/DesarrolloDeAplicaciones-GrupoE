package domain;



public class GoodRate extends Rate
{
    public GoodRate(User user, Ride ride, String comment) {
        super(user, ride, RateValue.GOOD, comment);
    }
}
