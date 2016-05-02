package domain.builders;

import domain.RoutePoint;
import domain.Route;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

public class RouteBuilder {
    private List<RoutePoint> routePoints = new ArrayList<RoutePoint>();
    private Float fixedCosts ;
    private Float distanceInKms ;

    public static RouteBuilder aRoute() {
        return new RouteBuilder();
    }

    public Route build()
    {
        Route route = new Route();
        routePoints.stream().forEach(routePoint -> {
                routePoint.setRoute(route);
                route.addRoutePoint(routePoint);
            }
        );

        if (fixedCosts != null)
            route.setFixedCosts(fixedCosts);

        if (distanceInKms != null)
            route.setFixedCosts(distanceInKms);
        return route;

    }

    public RouteBuilder withRoutePointAt(Double longitude, Double latitude)
    {
        this.routePoints.add( new RoutePoint(latitude,longitude) );
        return this;
    }

    public RouteBuilder withRoutePoint(RoutePoint routePoint)
    {
        this.routePoints.add(routePoint);
        return this;
    }


    public RouteBuilder withRoutePoints(List<RoutePoint> routePoints) {
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
