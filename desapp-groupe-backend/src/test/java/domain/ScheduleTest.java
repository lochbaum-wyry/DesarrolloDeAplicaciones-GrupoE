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

}
