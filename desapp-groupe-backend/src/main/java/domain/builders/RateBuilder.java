package domain.builders;

import domain.Rate;
import domain.RateValue;
import domain.Ride;
import domain.User;
import org.joda.time.DateTime;

public class RateBuilder {
    private User user;
    private Ride ride;
    private RateValue rateValue;
    private String comment;
    private DateTime date;

    public static RateBuilder aRate() {
        return new RateBuilder();
    }


    public RateBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public RateBuilder withRateValue(RateValue good) {
        this.rateValue = good;
        return this;
    }


    public RateBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public RateBuilder withRide(Ride ride) {
        this.ride = ride;
        return this;
    }


    public Rate build() {
        return new Rate(user,ride,rateValue,comment);
    }
}
