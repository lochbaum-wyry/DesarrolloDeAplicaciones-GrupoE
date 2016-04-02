package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Route {

    public static Integer radioCloseness = 200;
    private List<Schedule> schedules = new ArrayList<Schedule>();
    private List<Location> locations = new ArrayList<Location>();

    public Boolean matchesRequestedRoute(DateTime date, Location departureLocation, Location getOffLocation) {
        // TODO: Fecha
        return this.locationIsNearRoute(departureLocation) && this.locationIsNearRoute(getOffLocation);
    }

    public Boolean locationIsNearRoute(Location location){
        return this.locations.stream().anyMatch(point -> point.isNear(location));
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public List<Location> getLocations() {
        return locations;
    }

}
