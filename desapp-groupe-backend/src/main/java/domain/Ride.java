package domain;

import domain.exceptions.NoSeatsAvailableException;
import helpers.CalculationHelpers;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ride extends Entity
{
    private Float oilPrice ;
    private Vehicle vehicle;
    private DateTime date;
    private Route route;
    private User driver;
    private List<TakenSeat> takenSeats = new ArrayList<TakenSeat>();
    private Boolean cancelled;
    private Float efficiencyPercentage;

    private RideCostCalculator rideCostCalculator ;

    public Ride () {}

    public static Ride newFromRideRequest(RideRequest rideRequest)
    {
        Ride ride = new Ride(rideRequest.getRoute(), rideRequest.getDate(), rideRequest.getDriver());
        ride.setRideCostCalculator(new RideCostPassengerDivision(ride));
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

    public void setOilPrice(Float oilPrice)
    {
        this.oilPrice = oilPrice;
    }

    public Float getOilPrice()
    {
        return this.oilPrice;
    }

    public void cancelRide()
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

    public void addTakenSeat(TakenSeat takenSeat)
    {
        this.takenSeats.add(takenSeat);
    }

    public void takeSeat(User passenger, RoutePoint boardingAt, RoutePoint getOffAt) throws NoSeatsAvailableException
    {
        TakenSeat seat = new TakenSeat(passenger, boardingAt, getOffAt);
        this.addTakenSeat(seat);
        this.updateEfficiency();
    }

    public void updateEfficiency()
    {
        this.efficiencyPercentage = computeEfficiencyPercentage();
    }

    private float computeEfficiencyPercentage() {
        return (getSavedAmount()*100)/getTotalCost();
    }

    public void validateSeatAvailableInSection(RoutePoint boardingAt, RoutePoint getOffAt) throws NoSeatsAvailableException
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
    public Integer availableSeatsCountInSection(RoutePoint from, RoutePoint to)
    {
        Integer placesLeft = this.vehicle.getCapacity() - 1; // one space taken by driver by default

        placesLeft -= (int) this.takenSeatsInSection(from, to).size();

        return placesLeft ;
    }

    /**
     * Returns a list of the taken seats in a given section of the route
     * @param from
     * @param to
     * @return
     */
    public List<TakenSeat> takenSeatsInSection(RoutePoint from, RoutePoint to) {
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
    public Boolean seatIsTakenInSection(TakenSeat seat, RoutePoint sectionFrom, RoutePoint sectionTo)
    {
        Integer idxSeatBoardingAt = seat.getBoardingAt().getIndexInRoute();
        Integer idxSeatGetOffAt = seat.getGetOffAt().getIndexInRoute();

        // chequeo si se solapan los puntos de ruta
        return CalculationHelpers.rangesOverlap(  sectionFrom.getIndexInRoute(), sectionTo.getIndexInRoute(),
                                            idxSeatBoardingAt, idxSeatGetOffAt );
    }

    public Optional<TakenSeat> getSeatTakenBy(User passenger)
    {
        return takenSeats.stream().filter(seat -> seat.getPassenger() == passenger).findFirst();
    }

    public Float getTotalCost()
    {
        return this.getRoute().getFixedCosts() + this.getVehicleUseCosts();
    }

    public Float getVehicleUseCosts() {
        return this.getVehicle().getOilUsePerKmInLts()
                * this.getRoute().getDistanceInKms() * this.oilPrice;
    }

    public RideCostCalculator getRideCostCalculator() {
        return rideCostCalculator;
    }

    public void setRideCostCalculator(RideCostCalculator rideCostCalculator) {
        this.rideCostCalculator = rideCostCalculator;
    }

    public Integer getNumberOfPassengers()
    {
        return this.getTakenSeats().size()  ; /// + 1 is the driver
    }

    public Float getCostForPassenger(User passenger)
    {
        return rideCostCalculator.calculateCostForPassenger(passenger);
    }

    public Float getSavedAmount()
    {
        return getPassengers().stream().map(passenger -> getCostForPassenger(passenger)).reduce(0f, Float::sum);
    }

    public List<User> getPassengers()
    {
        return this.takenSeats.stream().map(takenSeat -> takenSeat.getPassenger()).collect(Collectors.toList());
    }

    public Boolean isDriver(User user)
    {
        return this.getDriver().equals(user);
    }

    public Float getEfficiencyPercentage()
    {
        return this.efficiencyPercentage;
    }

    public void setEfficiencyPercentage(Float efficiencyPercentage)
    {
        this.efficiencyPercentage = efficiencyPercentage;
    }

    public Boolean isCancelled() {
        return this.cancelled;
    }


    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setTakenSeats(List<TakenSeat> takenSeats) {
        this.takenSeats = takenSeats;
    }

    public boolean isPassenger(User requester) {
        return getSeatTakenBy(requester).isPresent();
    }
}
