package domain.builders;

import domain.Schedule;
import org.joda.time.DateTime;

import java.time.DayOfWeek;

public class ScheduleBuilder {
    private DayOfWeek day;
    private DateTime departureTime;
    private DateTime arrivalTime;

    public static ScheduleBuilder aSchedule() {
        return new ScheduleBuilder();
    }


    public ScheduleBuilder withDay(DayOfWeek day) {
        this.day = day;
        return this;
    }

    public ScheduleBuilder withDepartureTime(DateTime dateTime) {
        this.departureTime = dateTime;
        return this;
    }

    public ScheduleBuilder withArrivalTime(DateTime dateTime) {
        this.arrivalTime = dateTime;
        return this;
    }


    public Schedule build() {
        return new Schedule(day,departureTime,arrivalTime);
    }
}
