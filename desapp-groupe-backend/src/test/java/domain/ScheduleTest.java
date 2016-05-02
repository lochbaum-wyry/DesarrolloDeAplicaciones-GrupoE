package domain;

import domain.builders.ScheduleBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import java.time.DayOfWeek;

public class ScheduleTest
{
    @Test
    public void test_dayAndHourIsNear_returnsTrueIfGivenDateTimeIsBetweenDepartureWithSecondsMarginAndDateOfWeekIsTheSame()
    {
        Schedule schedule = ScheduleBuilder.aSchedule()
                .withDay(DayOfWeek.FRIDAY)
                .withDepartureTime(new DateTime(2016,2,5,9,00))
                .withArrivalTime(new DateTime(2016,2,5,10,30))
                .build();

        DateTime day = new DateTime(2016,2,12,9,30);

        Assert.assertTrue(schedule.dayAndHourIsNear(day,1500));
    }

    @Test
    public void test_dayAndHourIsNear_returnsFalseIfGivenDateTimeIsNotBetweenDepartureWithSecondsMarginOrDateOfWeekIsNotTheSame()
    {
        Schedule schedule = ScheduleBuilder.aSchedule()
                .withDay(DayOfWeek.FRIDAY)
                .withDepartureTime(new DateTime(2016,2,5,9,00))
                .withArrivalTime(new DateTime(2016,2,5,10,30))
                .build();

        DateTime day = new DateTime(2016,2,13,10,30);

        Assert.assertFalse(schedule.dayAndHourIsNear(day,1500));
    }

    @Test
    public void test_getArrivalTime_returnsArrivalTimeSet()
    {
        DateTime departureTime = new DateTime();
        DateTime arrivalTime = new DateTime();
        Schedule schedule = new Schedule(DayOfWeek.FRIDAY, departureTime, arrivalTime);

        Assert.assertEquals(arrivalTime,schedule.getArrivalTime());
    }

    @Test
    public void test_getDepartureTime_returnsDepartureTimeSet()
    {
        DateTime departureTime = new DateTime();
        DateTime arrivalTime = new DateTime();
        Schedule schedule = new Schedule(DayOfWeek.FRIDAY, departureTime, arrivalTime);

        Assert.assertEquals(departureTime,schedule.getDepartureTime());
    }

    @Test
    public void test_setAndGetArrivalTime(){
        Schedule schedule = ScheduleBuilder.aSchedule().build();

        DateTime date = new DateTime(2,3,4,5,6);
        schedule.setArrivalTime(date);

        Assert.assertEquals(schedule.getArrivalTime(),date);
    }


    @Test
    public void test_setAndGetDepartureTime(){
        Schedule schedule = ScheduleBuilder.aSchedule().build();

        DateTime date = new DateTime(2,3,4,5,6);
        schedule.setDepartureTime(date);

        Assert.assertEquals(schedule.getDepartureTime(),date);
    }

}