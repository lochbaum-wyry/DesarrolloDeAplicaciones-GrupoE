package domain.builders;

import domain.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class RideBuilder {
    private Vehicle vehicle;
    private DateTime date;
    private Route route;
    private User driver;
    private List<TakenSeat> takenSeats = new ArrayList<TakenSeat>();
    private Boolean cancelled;

    public RideBuilder(){
        this.vehicle = null;
        this.date = null;
        this.route = null;
        this.driver = null;
        this.cancelled = Boolean.FALSE;
    }


    public static RideBuilder aRide() {
        return new RideBuilder();
    }

    public RideBuilder withTakenSeatAt(TakenSeat takenSeat1) {
        this.takenSeats.add(takenSeat1);
        return this;
    }

    public Ride build() {
        Ride ride = new Ride(this.route,this.date,this.driver);
        ride.getTakenSeats().addAll(this.takenSeats);
        return ride;
    }

    public RideBuilder withDriver(User driverMario) {
        this.driver = driverMario;
        return this;
    }

    public RideBuilder withDate(DateTime date) {
        this.date = date;
        return this;
    }

    public RideBuilder withRoute(Route route) {
        this.route = route;
        return this;
    }
}
