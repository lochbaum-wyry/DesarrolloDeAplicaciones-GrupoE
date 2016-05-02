package domain.builders;

import domain.RoutePoint;
import domain.RideRequest;
import domain.Route;
import domain.User;
import org.joda.time.DateTime;

public class RideRequestBuilder {
    private Route route;
    private RoutePoint boardingAt;
    private RoutePoint getOffAt;
    private User passenger;
    private DateTime date;
    private User driver;

    public RideRequestBuilder() {
        this.route = null;
        this.boardingAt = null;
        this.getOffAt = null;
        this.passenger = null;
        this.date = null;
    }

    public static RideRequestBuilder aRideRequest() {
        return new RideRequestBuilder();
    }

    public RideRequestBuilder withRoute(Route route) {
        this.route = route;
        return this;
    }

    public RideRequestBuilder withBoardingAt(RoutePoint boardingAt) {
        this.boardingAt = boardingAt;
        return this;
    }

    public RideRequestBuilder withGetoffAt(RoutePoint getOffAt) {
        this.getOffAt = getOffAt;
        return this;
    }

    public RideRequestBuilder withPassenger(User passenger) {
        this.passenger = passenger;
        return this;
    }

    public RideRequestBuilder withDate(DateTime date) {
        this.date = date;
        return this;
    }

    public RideRequestBuilder withDriver(User driver) {
        this.driver = driver;
        return this;
    }


    public RideRequest build() {
        RideRequest rideRequest = new RideRequest(passenger,driver,date, route,boardingAt,getOffAt);
        return rideRequest;
    }
}
