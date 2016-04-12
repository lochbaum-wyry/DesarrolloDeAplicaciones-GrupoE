package domain.builders;

import domain.Location;
import domain.Route;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

public class RouteBuilder {
    private List<Location> locations = new ArrayList<Location>();
    private Float fixedCosts ;
    private Float distanceInKms ;

    public static RouteBuilder aRoute() {
        return new RouteBuilder();
    }

    public Route build() {
        Route route = new Route();
        locations.stream().forEach(location -> {route.addLocation(location);});

        if (fixedCosts != null)
            route.setFixedCosts(fixedCosts);

        if (distanceInKms != null)
            route.setFixedCosts(distanceInKms);


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

    public RouteBuilder withFixedCosts(float fixedCosts)
    {
        this.fixedCosts = fixedCosts;
        return this;
    }

    public RouteBuilder withDistanceInKms(Float distanceInKms) {
        this.distanceInKms = distanceInKms;
        return this;
    }
}
