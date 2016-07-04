package domain.servicesRest.daos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.RateType;
import domain.RateValue;
import domain.servicesRest.serialization.JodaDateTimeDeserializer;
import org.joda.time.DateTime;


public class RateDTO {

    private int raterId;
    private int ratedUserId;
    private int rideId;
    private int vehicleId;
    private RateType rateType;
    private RateValue rateValue;
    private String comment;

    @JsonDeserialize(using=JodaDateTimeDeserializer.class)
    private DateTime date;

    public int getRaterId() {
        return raterId;
    }

    public void setRaterId(int raterId) {
        this.raterId = raterId;
    }

    public int getRatedUserId() {
        return ratedUserId;
    }

    public void setRatedUserId(int ratedUserId) {
        this.ratedUserId = ratedUserId;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    public RateValue getRateValue() {
        return rateValue;
    }

    public void setRateValue(RateValue rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DateTime getDate() {
        return date;
    }

    @JsonDeserialize(using=JodaDateTimeDeserializer.class)
    public void setDate(DateTime date) {
        this.date = date;
    }



}
