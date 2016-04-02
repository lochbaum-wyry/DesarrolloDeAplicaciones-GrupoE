package domain.builders;

import domain.Location;
import domain.TakenSeat;
import domain.User;

public class TakenSeatBuilder {
    private User passenger;
    private Location boardingAt;
    private Location getOffAt;

    public TakenSeatBuilder(){
        this.passenger = null;
        this.boardingAt = null;
        this.getOffAt = null;
    }

    public static TakenSeatBuilder aTakenSeat() {
        return new TakenSeatBuilder();
    }


    public TakenSeatBuilder withPassenger(User passenger) {
        this.passenger = passenger;
        return this;
    }

    public TakenSeatBuilder withboardingAtLocation(Location boardingAt) {
        this.boardingAt = boardingAt;
        return this;
    }

    public TakenSeatBuilder withgetOffAtLocation(Location getOffAt) {
        this.getOffAt = getOffAt;
        return this;
    }

    public TakenSeat build() {
        return new TakenSeat(this.passenger,this.boardingAt,this.getOffAt);
    }
}
