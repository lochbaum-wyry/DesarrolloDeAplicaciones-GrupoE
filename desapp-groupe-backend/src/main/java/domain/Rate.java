package domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import domain.servicesRest.serialization.JodaDateTimeDeserializer;
import org.joda.time.DateTime;

public class Rate extends Entity{
    private User rater;
    private User ratedUser;
    private Ride ride;
    private RateType rateType;
    private RateValue rateValue;
    private String comment;

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=JodaDateTimeDeserializer.class)
    private DateTime date;
    private Vehicle vehicle;

    public Rate() {}
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

    public void setComment(String comment) {
        this.comment = comment;
    }


    public DateTime getDate() {
        return date;
    }

    public void setRater(User rater) {
        this.rater = rater;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}