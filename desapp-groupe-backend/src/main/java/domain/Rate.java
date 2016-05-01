package domain;

import org.joda.time.DateTime;

public class Rate {
    private User rater;
    private User ratedUser;
    private Ride ride;

    private RateType rateType;
    private RateValue rateValue;
    private String comment;
    private DateTime date;
    private Vehicle vehicle;
    private int id;

    public Rate(User rater, User ratedUser, Ride ride, RateType rateType, RateValue rateValue, String comment)
    {
        this.rateType = rateType;
        this.rateValue = rateValue;
        this.ratedUser = ratedUser;
        this.rater = rater;
        this.ride = ride;
        this.comment = comment;
        this.date = new DateTime();
    }

    public RateValue getValue() {
        return rateValue;
    }

    public String getComment() {
        return comment;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getRater() {
        return rater;
    }

    public Integer getMonth() {
        return date.getMonthOfYear();
    }

    public Integer getYear() {
        return date.getYearOfEra();
    }

    public Ride getRide() { return this.ride ; }

    public User getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(User ratedUser) {
        this.ratedUser = ratedUser;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public RateValue getRateValue() {
        return rateValue;
    }

    public void setRateValue(RateValue rateValue) {
        this.rateValue = rateValue;
    }


    public void setDate(DateTime date) {
        this.date = date;
    }
}
