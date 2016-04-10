package domain;

import org.joda.time.DateTime;

public class Rate {
    private User user;
    private Ride ride;
    private RateValue rateValue;
    private String comment;
    private DateTime date;

    public Rate(User user, Ride ride, RateValue rateValue, String comment){
        this.rateValue = rateValue;
        this.user = user;
        this.ride = ride;
        this.comment = comment;
        this.date = new DateTime();
    }


    public RateValue getValue() {
        return rateValue;
    }

    public Integer getMonth() {
        return date.getMonthOfYear();
    }

    public Integer getYear() {
        return date.getYearOfEra();
    }

    public Ride getRide() { return this.ride ; }

}
