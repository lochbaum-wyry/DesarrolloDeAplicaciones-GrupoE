package domain;
import org.joda.time.LocalTime;

import java.time.DayOfWeek;

public class Schedule extends Entity{
    private DayOfWeek day;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public Schedule(DayOfWeek day, LocalTime departureTime, LocalTime arrivalTime)
    {
        this.day = day;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }


    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
