package domain;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.sql.Time;
import java.time.DayOfWeek;

public class Schedule {
    private DayOfWeek day;
    private DateTime departureTime;
    private DateTime arrivalTime;
    private int id;

    public Schedule(DayOfWeek day, DateTime departureTime, DateTime arrivalTime){
        this.arrivalTime = arrivalTime;
        this.day = day;
        this.departureTime = departureTime;
    }

    public DayOfWeek getDay() {
        return day;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public DateTime getArrivalTime() {
        return arrivalTime;
    }

    public DateTime getDepartureTime() {
        return departureTime;
    }

    public boolean dayAndHourIsNear(DateTime date, Integer secondsDateCloseness)
    {
        return getDay().getValue() == date.getDayOfWeek() && timeIsNear(date, secondsDateCloseness);
    }

    private boolean timeIsNear(DateTime date, Integer secondsDateCloseness) {
        int toleranceBegin = departureTime.secondOfDay().get() - secondsDateCloseness;
        int toleranceEnd = departureTime.secondOfDay().get() + secondsDateCloseness;

        return   (toleranceBegin <= date.secondOfDay().get()) && (date.secondOfDay().get() >= toleranceEnd);
    }


    public void setArrivalTime(DateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public void setDepartureTime(DateTime departureTime) {
        this.departureTime = departureTime;
    }
}
