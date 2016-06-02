package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalTimeSerializer;
import org.joda.time.LocalTime;

import java.time.DayOfWeek;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Schedule extends Entity
{
    private DayOfWeek day;

    @JsonSerialize(using= LocalTimeSerializer.class)
    @JsonDeserialize(using= LocalTimeDeserializer.class)
    private LocalTime departureTime;

    @JsonSerialize(using= LocalTimeSerializer.class)
    @JsonDeserialize(using= LocalTimeDeserializer.class)
    private LocalTime arrivalTime;

    public Schedule() { }
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
