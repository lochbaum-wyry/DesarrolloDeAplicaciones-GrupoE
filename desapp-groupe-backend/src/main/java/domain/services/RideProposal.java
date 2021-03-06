package domain.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import domain.Route;
import domain.RoutePoint;
import domain.Schedule;
import domain.User;
import org.joda.time.DateTime;


public class RideProposal
{
    @JsonSerialize(using=ReducedUserSerializer.class)
    private User driver;
    private Route route;
    private RoutePoint boardingPoint;
    private RoutePoint getOffPoint;
    @JsonSerialize(using=DateTimeSerializer.class)
    private DateTime departureDateTime;

    @JsonSerialize(using=DateTimeSerializer.class)
    public DateTime getDepartureDateTime()
    {
        return departureDateTime;
    }

    public void setDepartureDateTime(DateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public RoutePoint getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(RoutePoint boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public RoutePoint getGetOffPoint() {
        return getOffPoint;
    }

    public void setGetOffPoint(RoutePoint getOffPoint) {
        this.getOffPoint = getOffPoint;
    }

    @JsonSerialize(using=ReducedUserSerializer.class)
    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public RideProposal() {}

    public RideProposal(User driver, Route route, Schedule schedule, DateTime departureDateTime, RoutePoint boardingPoint, RoutePoint getOffPoint)
    {
        int minute = schedule.getDepartureTime().getMinuteOfHour();
        int hour = schedule.getDepartureTime().getHourOfDay();
        int year = departureDateTime.getYear();
        int month = departureDateTime.getMonthOfYear();
        int day = departureDateTime.getDayOfMonth();
        DateTime scheduledDepartureDateTime = new DateTime(year, month, day, hour, minute);

        this.driver = driver;
        this.boardingPoint = boardingPoint;
        this.getOffPoint = getOffPoint;
        this.route = route ;
        this.departureDateTime = scheduledDepartureDateTime ;
    }
}
