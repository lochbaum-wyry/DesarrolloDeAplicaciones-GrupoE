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
    public void test_getArrivalTime_returnsArrivalTimeSet()
    {
        LocalTime departureTime = new LocalTime();
        LocalTime arrivalTime = new LocalTime();
        Schedule schedule = new Schedule(DayOfWeek.FRIDAY, departureTime, arrivalTime);

        Assert.assertEquals(arrivalTime,schedule.getArrivalTime());
    }

    @Test
    public void test_getDepartureTime_returnsDepartureTimeSet()
    {
        LocalTime departureTime = new LocalTime();
        LocalTime arrivalTime = new LocalTime();
        Schedule schedule = new Schedule(DayOfWeek.FRIDAY, departureTime, arrivalTime);

        Assert.assertEquals(departureTime,schedule.getDepartureTime());
    }

    @Test
    public void test_setAndGetArrivalTime(){
        Schedule schedule = ScheduleBuilder.aSchedule().build();

        LocalTime date = new LocalTime();
        schedule.setArrivalTime(date);

        Assert.assertEquals(schedule.getArrivalTime(),date);
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