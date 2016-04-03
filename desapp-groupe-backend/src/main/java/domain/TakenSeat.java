package domain;

public class TakenSeat {
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


}
