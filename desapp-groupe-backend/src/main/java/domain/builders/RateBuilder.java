package domain.builders;

import domain.Rate;
import domain.RateType;
import domain.RateValue;
import domain.Ride;
import domain.User;
import org.joda.time.DateTime;

public class RateBuilder {
    private User user;
    private User ratedUser;
    private RateType rateType;
    private Ride ride;
    private RateValue rateValue;
    private String comment;
    private DateTime date;

    public static RateBuilder aRate() {
        return new RateBuilder();
    }


    public RateBuilder withDate(DateTime date)
    {
        this.date = date;
        return this;
    }
    public RateBuilder withUser(User user) {
        this.user = user;
        return this;
    }
    public RateBuilder withRateType(RateType rateType) {
        this.rateType = rateType;
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
        if (rateType == null) rateType = RateType.Driving;
        Rate rate = new Rate(user, ratedUser, ride, rateType, rateValue, comment);
        rate.setDate(date);
        return rate;
    }
}
