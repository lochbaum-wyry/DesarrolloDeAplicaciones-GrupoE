package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ride {

    private Vehicle vehicle;
    private DateTime date;
    private Route route;
    private User driver;
    private List<TakenSeat> takenSeats = new ArrayList<TakenSeat>();
    private Boolean cancelled;

    public static Ride fromRideRequest(User driver, RideRequest rideRequest)
    {
        Ride ride = new Ride(rideRequest.getRoute(), rideRequest.getDate(), driver);
        return ride;
    }

    public Ride(Route route, DateTime date, User driver)
    {
        this.setRoute(route);
        this.setDate(date);
        this.setDriver(driver);
        this.setVehicle(driver.getVehicle());
        this.cancelled = Boolean.FALSE;
    }

    public void cancelledRide(){
        this.cancelled = Boolean.TRUE;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public List<TakenSeat> getTakenSeats()
    {
        return takenSeats;
    }

    public void setTakenSeats(List<TakenSeat> takenSeats)
    {
        this.takenSeats = takenSeats;
    }

    public void addTakenSeat(TakenSeat takenSeat)
    {
        this.takenSeats.add(takenSeat);
    }


    public void takeSeat(User passenger, Location boardingAt, Location getOffAt)
    {
        TakenSeat seat = new TakenSeat(passenger, boardingAt, getOffAt);
        addTakenSeat(seat);
    }

    public Optional<TakenSeat> seatTakenBy(User passenger)
    {
        return takenSeats.stream().filter(seat -> seat.getPassenger() == passenger).findFirst();
    }

    public Boolean suitsRideRequest(RideRequest rideRequest) {
        return  this.route == rideRequest.getRoute() &&
                this.date == rideRequest.getDate()
                ;
    }
}
