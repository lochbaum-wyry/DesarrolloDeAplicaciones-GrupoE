package domain;

public class Rate {
    private RateType rateType;
    private User user;
    private Ride ride;
    private RateValue rateValue;
    private String comment;

    public Rate(RateType rateType, User user, Ride ride, RateValue rateValue, String comment){
        this.rateType = rateType;
        this.user = user;
        this.ride = ride;
        this.rateType = rateType;
        this.comment = comment;
    }

    public RateType getRateType() {
        return rateType;
    }

    public User getUser() {
        return user;
    }

    public Ride getRide() {
        return ride;
    }

    public Boolean isGood() { return rateValue == RateValue.GOOD; }

    public String getComment() {
        return comment;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public void setRateValue(RateValue rateValue) { this.rateValue = rateValue; }

    public void setComment(String comment) {
        this.comment = comment;
    }



}
