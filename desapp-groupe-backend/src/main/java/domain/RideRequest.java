package domain;

import org.joda.time.DateTime;

public class RideRequest {
    private User passenger;
    private Route route;
    private DateTime date;
    private DateTime dateRequested;
    private Location boardingAt;
    private Location getOffAt;
    private RequestStatus status;

    public RideRequest(User passenger, DateTime date,Route route,Location boardingAt,Location getOffAt){
        this.dateRequested = DateTime.now();
        this.passenger = passenger;
        this.route = route;
        this.date = date;
        this.boardingAt = boardingAt;
        this.getOffAt = getOffAt;
        this.status = RequestStatus.Pending;
    }

    public void accept(){
        this.status = RequestStatus.Accepted;
    }

    public void reject(){
        this.status = RequestStatus.Denied;
    }

    public User getPassenger() {
        return passenger;
    }

    public Route getRoute() {
        return route;
    }

    public DateTime getDate() {
        return date;
    }

    public Location getBoardingAt() {
        return boardingAt;
    }

    public Location getGetOffAt() {
        return getOffAt;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public void setBoardingAt(Location boardingAt) {
        this.boardingAt = boardingAt;
    }

    public void setGetOffAt(Location getOffAt) {
        this.getOffAt = getOffAt;
    }


}
