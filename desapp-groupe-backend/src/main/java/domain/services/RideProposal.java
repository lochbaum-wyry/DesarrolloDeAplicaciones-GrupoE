package domain.services;

import domain.Route;
import domain.RoutePoint;
import domain.Schedule;
import domain.User;
import org.joda.time.DateTime;


public class RideProposal
{
    private User driver;
    private Route route;
    private RoutePoint boardingPoint;
    private RoutePoint getOffPoint;
    private Schedule schedule;
    private DateTime departureDateTime;

    public DateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(DateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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

    public RideProposal() {}

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public RideProposal(User driver, Route route, Schedule schedule, DateTime departureDateTime, RoutePoint boardingPoint, RoutePoint getOffPoint)
    {
        int minute = schedule.getArrivalTime().getMinuteOfHour();
        int hour = schedule.getDepartureTime().getHourOfDay();
        int year = departureDateTime.getYear();
        int month = departureDateTime.getMonthOfYear();
        int day = departureDateTime.getDayOfMonth();
        DateTime scheduleDate = new DateTime(year, month, day, hour, minute);

        this.driver = driver;
        this.boardingPoint = boardingPoint;
        this.getOffPoint = getOffPoint;
        this.route = route ;
        this.schedule = schedule ;
        this.departureDateTime = scheduleDate ;
    }

}
