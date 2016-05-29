package domain;

public class TakenSeat extends Entity
{
    private Ride ride;
    private User passenger;
    private RoutePoint boardingAt;
    private RoutePoint getOffAt;

    public TakenSeat() {}
    public TakenSeat(User passenger, RoutePoint boardingAt, RoutePoint getOffAt)
    {
        this.passenger = passenger;
        this.boardingAt = boardingAt;
        this.getOffAt = getOffAt;
    }

    public User getPassenger()
    {
        return passenger;
    }

    public RoutePoint getGetOffAt()
    {
        return getOffAt;
    }

    public RoutePoint getBoardingAt()
    {
        return boardingAt;
    }

    public void setBoardingAt(RoutePoint boardingAt) {
        this.boardingAt = boardingAt;
    }

    public void setGetOffAt(RoutePoint getOffAt) {
        this.getOffAt = getOffAt;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

}
