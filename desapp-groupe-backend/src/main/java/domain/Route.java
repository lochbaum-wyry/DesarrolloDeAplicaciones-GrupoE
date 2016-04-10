package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<Schedule> schedules = new ArrayList<Schedule>();
    private List<Location> locations = new ArrayList<Location>();
    private Float distanceInKms;

    public Boolean matchesRequestedRoute(Location departureLocation, Location getOffLocation, Float radioCloseness)
    {
        // TODO: Fecha
        return this.locationIsNearRoute(departureLocation, radioCloseness) && this.locationIsNearRoute(getOffLocation, radioCloseness);
    }


    public Boolean locationIsNearRoute(Location location, Float radioCloseness)
    {
        return this.locations.stream().anyMatch(point -> point.isNear(location, radioCloseness));
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public void addSchedule(Schedule schedule){ this.schedules.add(schedule); }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Float getFixedCosts()
    {
        return 0f;
    }

    public Float getDistanceInKms()
    {
        return this.distanceInKms;
    }

    public boolean isInDayAndHour(DateTime date,Integer secondsDateCloseness) {
        return schedules.stream().anyMatch(schedule -> schedule.dayAndHourIsEquals(date,secondsDateCloseness));
    }
}
