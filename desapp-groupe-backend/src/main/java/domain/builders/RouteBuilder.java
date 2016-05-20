package domain.builders;

import domain.LatLng;
import domain.Route;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

public class RouteBuilder {
    private List<LatLng> routePoints = new ArrayList<LatLng>();
    private Float fixedCosts ;
    private Float distanceInKms ;

    public static RouteBuilder aRoute() {
        return new RouteBuilder();
    }

    public Route build()
    {
        Route route = new Route();
        route.setLatLngs(routePoints);

        if (fixedCosts != null)
            route.setFixedCosts(fixedCosts);

        if (distanceInKms != null)
            route.setFixedCosts(distanceInKms);
        return route;

    }

    public RouteBuilder withRoutePointAt(Double longitude, Double latitude)
    {
        this.routePoints.add( new LatLng(latitude,longitude) );
        return this;
    }



    public RouteBuilder withRoutePoints(List<LatLng> routePoints) {
        this.routePoints = routePoints;
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
