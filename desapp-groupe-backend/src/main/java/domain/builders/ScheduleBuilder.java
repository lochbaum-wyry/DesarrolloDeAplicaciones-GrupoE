package domain.builders;

import domain.Schedule;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.time.DayOfWeek;

public class ScheduleBuilder {
    private DayOfWeek day;
    private LocalTime departureTime ;
    private LocalTime arrivalTime;

    public static ScheduleBuilder aSchedule()
    {
        return new ScheduleBuilder();
    }

    public ScheduleBuilder()
    {
        // Inicializacion de valores por defecto para el momento actual
        departureTime = new LocalTime();
        arrivalTime = new LocalTime();
        day = DayOfWeek.of(new DateTime().getDayOfWeek());
    }


    public ScheduleBuilder withDay(DayOfWeek day) {
        this.day = day;
        return this;
    }

    public ScheduleBuilder withDepartureTime(LocalTime dateTime) {
        this.departureTime = dateTime;
        return this;
    }

    public ScheduleBuilder withArrivalTime(LocalTime dateTime) {
        this.arrivalTime = dateTime;
        return this;
    }


    public Schedule build() {
        return new Schedule(day,departureTime,arrivalTime);
    }

    public ScheduleBuilder withDepartureTimeAt(int hour, int minute)
    {
        departureTime = new LocalTime(hour,minute);
        return this;
    }

    public ScheduleBuilder withArrivalTimeAt(int hour, int minute)
    {
        arrivalTime = new LocalTime(hour,minute);
        return this;
    }
}
