package domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import domain.services.ReducedUserSerializer;
import domain.servicesRest.serialization.JodaDateTimeDeserializer;
import org.joda.time.DateTime;

public class RideRequest extends Entity
{
    @JsonSerialize(using= ReducedUserSerializer.class)
    private User requester;
    @JsonSerialize(using= ReducedUserSerializer.class)
    private User driver;
    private Route route;

    @JsonSerialize(using=DateTimeSerializer.class)
    @JsonDeserialize(using=JodaDateTimeDeserializer.class)
    private DateTime date;

    private DateTime dateRequested;
    private RoutePoint boardingAt;
    private RoutePoint getOffAt;
    private RequestStatus status;

    public RideRequest()
    {
        this.dateRequested = DateTime.now();

    }

    public RideRequest(User requester, User driver, DateTime date, Route route, RoutePoint boardingAt, RoutePoint getOffAt)
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

    @JsonSerialize(using= ReducedUserSerializer.class)
    public User getRequester() {
        return requester;
    }

    public Route getRoute() {
        return route;
    }

    public DateTime getDate() {
        return date;
    }

    public RoutePoint getBoardingAt() {
        return boardingAt;
    }

    public RoutePoint getGetOffAt() {
        return getOffAt;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setBoardingAt(RoutePoint boardingAt) {
        this.boardingAt = boardingAt;
    }

    @JsonDeserialize(using=JodaDateTimeDeserializer.class)
    public void setDate(DateTime date) {
        this.date = date;
    }

    public DateTime getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(DateTime dateRequested) {
        this.dateRequested = dateRequested;
    }

    public void setGetOffAt(RoutePoint getOffAt) {
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

    @JsonSerialize(using= ReducedUserSerializer.class)
    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }
}
