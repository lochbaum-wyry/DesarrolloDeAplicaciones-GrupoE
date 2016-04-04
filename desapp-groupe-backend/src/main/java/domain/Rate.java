package domain;

import java.awt.datatransfer.DataFlavor;

public class Rate {
    private User user;
    private Ride ride;
    private RateValue rateValue;
    private String comment;

    public Rate(User user, Ride ride, RateValue rateValue, String comment){
        this.rateValue = rateValue;
        this.user = user;
        this.ride = ride;
        this.comment = comment;
    }


    public RateValue getValue() {
        return rateValue;
    }
}
