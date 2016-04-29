package domain;

import org.joda.time.DateTime;

public class RideRequest extends Entity
{
    private User requester;
    private User driver;
    private Route route;
    private DateTime date;
    private DateTime dateRequested;
    private Location boardingAt;
    private Location getOffAt;
    private RequestStatus status;


    public RideRequest(User requester, User driver, DateTime date, Route route, Location boardingAt, Location getOffAt)
    {
        this.driver = driver;
        this.dateRequested = DateTime.now();
        this.requester = requester;
        this.route = route;
        this.date = date;
        this.boardingAt = boardingAt;
        this.getOffAt = getOffAt;
        this.status = RequestStatus.Pending;
    }

    public void accept(){
        this.status = RequestStatus.Accepted;
    }

    public void reject()
    {
        this.status = RequestStatus.Rejected;
    }

    public User getRequester() {
        return requester;
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

    public void setBoardingAt(Location boardingAt) {
        this.boardingAt = boardingAt;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public DateTime getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(DateTime dateRequested) {
        this.dateRequested = dateRequested;
    }

    public void setGetOffAt(Location getOffAt) {
        this.getOffAt = getOffAt;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

}
