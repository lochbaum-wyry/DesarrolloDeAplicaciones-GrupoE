package domain;
import java.time.DayOfWeek;

public class Schedule {
    private DayOfWeek day;
    private Integer departureTime;
    private Integer arrivalTime;

    public Schedule(DayOfWeek day,Integer departureTime,Integer arrivalTime){
        this.arrivalTime = arrivalTime;
        this.day = day;
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public void setDepartureTime(Integer departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public Integer getDepartureTime() {
        return departureTime;
    }
}
