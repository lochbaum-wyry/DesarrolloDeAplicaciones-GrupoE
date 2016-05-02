package domain.builders;

import domain.RoutePoint;
import domain.TakenSeat;
import domain.User;

public class TakenSeatBuilder {
    private User passenger;
    private RoutePoint boardingAt;
    private RoutePoint getOffAt;

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

    public TakenSeatBuilder withboardingAtLocation(RoutePoint boardingAt) {
        this.boardingAt = boardingAt;
        return this;
    }

    public TakenSeatBuilder withgetOffAtLocation(RoutePoint getOffAt) {
        this.getOffAt = getOffAt;
        return this;
    }

    public TakenSeat build() {
        return new TakenSeat(this.passenger,this.boardingAt,this.getOffAt);
    }
}
