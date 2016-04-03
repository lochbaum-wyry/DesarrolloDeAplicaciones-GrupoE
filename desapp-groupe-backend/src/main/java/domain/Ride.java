package domain;

import domain.exceptions.NoSeatsAvailableException;
import org.joda.time.DateTime;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ride
{

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

    public void cancelledRide()
    {
        this.cancelled = Boolean.TRUE;
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    public DateTime getDate()
    {
        return date;
    }

    public void setDate(DateTime date)
    {
        this.date = date;
    }

    public Route getRoute()
    {
        return route;
    }

    public void setRoute(Route route)
    {
        this.route = route;
    }

    public User getDriver()
    {
        return driver;
    }

    public void setDriver(User driver)
    {
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


    public void takeSeat(User passenger, Location boardingAt, Location getOffAt) throws NoSeatsAvailableException
    {
        this.validateSeatAvailable(boardingAt, getOffAt);

        TakenSeat seat = new TakenSeat(passenger, boardingAt, getOffAt);
        this.addTakenSeat(seat);
    }

    public void validateSeatAvailable(Location boardingAt, Location getOffAt) throws NoSeatsAvailableException
    {
        if ( this.availableSeatsCountInSection(boardingAt, getOffAt) == 0 )
            throw new NoSeatsAvailableException(boardingAt, getOffAt);
    }

    /**
     * Returns number of seats available in a given section the route
     * @param from
     * @param to
     * @return
     */
    public Integer availableSeatsCountInSection(Location from, Location to)
    {
        Integer placesLeft = this.vehicle.getCapacity() - 1; // one space taken by driver by default

        placesLeft -= (int) this.takenSeatsCountInSection(from, to).size();

        return placesLeft ;
    }

    /**
     * Returns a list of the taken seats in a given section of the route
     * @param from
     * @param to
     * @return
     */
    public List<TakenSeat> takenSeatsCountInSection(Location from, Location to) {
        return this.takenSeats.stream()
                .filter( seat -> this.seatIsTakenInSection(seat, from, to) )
                .collect(Collectors.toList());
    }

    /**
     * Determines whether a seat is taken in a given section of the route
     * @param seat
     * @param sectionFrom
     * @param sectionTo
     * @return
     */
    public Boolean seatIsTakenInSection(TakenSeat seat, Location sectionFrom, Location sectionTo)
    {
        Integer idxSectionFrom = this.route.getLocations().indexOf(sectionFrom);
        Integer idxSectionTo = this.route.getLocations().indexOf(sectionTo);

        Integer idxSeatBoardingAt = this.route.getLocations().indexOf(seat.getBoardingAt());
        Integer idxSeatGetOffAt = this.route.getLocations().indexOf(seat.getGetOffAt());

        // chequeo si se solapan los puntos de ruta
        return sectionIndexesOverlap(idxSectionFrom, idxSectionTo, idxSeatBoardingAt, idxSeatGetOffAt);

    }

    private boolean sectionIndexesOverlap(Integer idxFrom, Integer idxTo, Integer idxSeatBoardingAt, Integer idxSeatGetOffAt) {
        return Math.max(idxTo,idxSeatGetOffAt) - Math.min(idxFrom,idxSeatBoardingAt)
                < (idxTo - idxFrom) + (idxSeatGetOffAt - idxSeatBoardingAt);
    }

    public Optional<TakenSeat> seatTakenBy(User passenger)
    {
        return takenSeats.stream().filter(seat -> seat.getPassenger() == passenger).findFirst();
    }

    public Boolean suitsRideRequest(RideRequest rideRequest)
    {
        return  this.route == rideRequest.getRoute() &&
                this.date == rideRequest.getDate()
                ;
    }
}