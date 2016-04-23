package domain;

import org.joda.time.DateTime;

public class Rate {
    private User user;
    private Ride ride;
    private RateValue rateValue;
    private String comment;
    private DateTime date;
    private int id;

    public Rate(User user, Ride ride, RateValue rateValue, String comment)
    {
        this.rateValue = rateValue;
        this.user = user;
        this.ride = ride;
        this.comment = comment;
        this.date = new DateTime();
    }

    public static Rate create(User user, Ride ride, RateValue rateValue, String comment)
    {
        Rate rate = null;
        switch (rateValue)
        {
            case GOOD:
                rate =  new GoodRate(user, ride, comment);
            break;

            case BAD:
                rate =  new BadRate(user, ride, comment);
            break;

        }
        return rate ;

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


    public User getUser() {
        return user;
    }

    public Integer getMonth() {
        return date.getMonthOfYear();
    }

    public Integer getYear() {
        return date.getYearOfEra();
    }

    public Ride getRide() { return this.ride ; }

}
