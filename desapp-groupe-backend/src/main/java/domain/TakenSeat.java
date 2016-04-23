package domain;

public class TakenSeat {
    private User passenger;
    private Location boardingAt;
    private Location getOffAt;
    private int id;

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
