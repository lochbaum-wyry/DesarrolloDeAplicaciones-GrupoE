package domain;

import domain.builders.ScheduleBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;
import java.time.DayOfWeek;

public class ScheduleTest
{
    @Test
    public void test_getDepartureTime_returnsDepartureTimeSet()
    {
        LocalTime departureTime = new LocalTime();
        Schedule schedule = new Schedule(DayOfWeek.FRIDAY, departureTime);

        Assert.assertEquals(departureTime,schedule.getDepartureTime());
    }

    @Test
    public void test_setAndGetDepartureTime(){
        Schedule schedule = ScheduleBuilder.aSchedule().build();

        LocalTime date = new LocalTime();
        schedule.setDepartureTime(date);

        Assert.assertEquals(schedule.getDepartureTime(),date);
    }

    @Test
    public void test_setAndGetDay(){
        Schedule schedule = ScheduleBuilder.aSchedule().build();

        DayOfWeek date = DayOfWeek.FRIDAY;
        schedule.setDay(date);

        Assert.assertEquals(schedule.getDay(),date);
    }


}