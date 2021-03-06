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
    private Boolean cancelled = Boolean.FALSE;
    private Float oilPrice;
    private RideCostCalculator rideCostCalculator ;

    public static RideBuilder aRide() {
        return new RideBuilder();
    }

    public RideBuilder withTakenSeatAt(TakenSeat takenSeat1)
    {
        this.takenSeats.add(takenSeat1);
        return this;
    }

    public Ride build()
    {
        Ride ride = new Ride(this.route,this.date,this.driver);

        ride.getTakenSeats().addAll(this.takenSeats);

        ride.setVehicle(this.vehicle);
        ride.setOilPrice(oilPrice);

        if (this.rideCostCalculator == null)
            rideCostCalculator = new RideCostPassengerDivision();

        this.rideCostCalculator.setRide(ride);

        ride.setRideCostCalculator(rideCostCalculator);

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

    public RideBuilder withVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public RideBuilder withOilPrice(Float oilPrice) {
        this.oilPrice = oilPrice;
        return this;
    }

    public RideBuilder withRideCostCalculator(RideCostCalculator rideCostCalculator)
    {
        this.rideCostCalculator = rideCostCalculator;
        return this;
    }
}
