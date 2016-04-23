package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<Schedule> schedules = new ArrayList<Schedule>();
    private List<Location> locations = new ArrayList<Location>();
    private Float distanceInKms = 0f;
    private Float fixedCosts = 0f;
    private int id;

    public Boolean matchesRequestedRoute(Location departureLocation, Location getOffLocation, Float radioCloseness)
    {
        return this.locationIsNearRoute(departureLocation, radioCloseness) && this.locationIsNearRoute(getOffLocation, radioCloseness);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setFixedCosts(Float fixedCosts)
    {
        this.fixedCosts = fixedCosts;
    }

    public Float getFixedCosts()
    {
        return this.fixedCosts;
    }

    public Float getDistanceInKms()
    {
        return this.distanceInKms;
    }

    public boolean isInDayAndHour(DateTime date,Integer secondsDateCloseness) {
        return schedules.stream().anyMatch(schedule -> schedule.dayAndHourIsNear(date,secondsDateCloseness));
    }

    public void setDistanceInKms(Float distanceInKms) {
        this.distanceInKms = distanceInKms;
    }


    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

}
