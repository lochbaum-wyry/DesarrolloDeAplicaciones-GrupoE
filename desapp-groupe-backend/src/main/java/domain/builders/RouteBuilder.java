package domain.builders;

import domain.Location;
import domain.Route;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

public class RouteBuilder {
    private List<Location> locations = new ArrayList<Location>();

    public static RouteBuilder aRoute() {
        return new RouteBuilder();
    }

    public Route build() {
        Route route = new Route();
        locations.stream().forEach(location -> {route.addLocation(location);});

        return route;

    }

    public RouteBuilder withLocationAt(Double longitude, Double latitude)
    {
        this.locations.add( new Location(longitude, latitude) );
        return this;
    }

    public RouteBuilder withLocation(Location location)
    {
        this.locations.add( location );
        return this;
    }


    public RouteBuilder withLocations(List<Location> locations) {
        this.locations = locations;
        return this;
    }
}
