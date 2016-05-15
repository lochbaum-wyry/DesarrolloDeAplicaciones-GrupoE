package domain.services;

import domain.Route;
import domain.Schedule;
import org.joda.time.DateTime;


public class RideProposal
{
    private Route route;
    private Schedule schedule;

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
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

    private DateTime date;

    public RideProposal() {}

    public RideProposal(Route route, Schedule schedule, DateTime date)
    {
        int minute = schedule.getArrivalTime().getMinuteOfHour();
        int hour = schedule.getDepartureTime().getHourOfDay();
        DateTime scheduleDate = new DateTime(date.getYear(), date.getMonthOfYear(),  date.getDayOfMonth(), hour, minute);

        this.route = route ;
        this.schedule = schedule ;
        this.date = date ;
    }

}
