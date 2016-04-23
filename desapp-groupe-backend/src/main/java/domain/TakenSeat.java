package domain;

public class TakenSeat extends Entity{
    private User passenger;
    private Location boardingAt;
    private Location getOffAt;

    public TakenSeat(User passenger, Location boardingAt, Location getOffAt)
    {
        this.passenger = passenger;
        this.boardingAt = boardingAt;
        this.getOffAt = getOffAt;
    }

    public User getPassenger()
    {
        return passenger;
    }

    public Location getGetOffAt()
    {
        return getOffAt;
    }

    public Location getBoardingAt()
    {
        return boardingAt;
    }

    public void setBoardingAt(Location boardingAt) {
        this.boardingAt = boardingAt;
    }

    public void setGetOffAt(Location getOffAt) {
        this.getOffAt = getOffAt;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }



}
