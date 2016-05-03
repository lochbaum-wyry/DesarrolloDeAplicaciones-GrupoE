package domain;

import java.lang.*;

import static java.lang.StrictMath.abs;

public class RoutePoint extends Entity
{
    private Route route;
    private Integer indexInRoute;
    private Double latitude;
    private Double longitude;

    public RoutePoint(Route route, Double latitude, Double longitude)
    {
        this(latitude, longitude);
        this.route = route;
    }

    public RoutePoint(Double latitude, Double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude ;
    }

    public Double getLongitude()
    {
        return this.longitude;
    }

    public Double getLatitude()
    {
        return this.latitude;
    }

    public Boolean isNear(RoutePoint routePoint, Float radioCloseness)
    {
        return this.pointIsInCircle(routePoint.getLongitude(), routePoint.getLatitude(), radioCloseness);
    }

    private Boolean pointIsInCircle(Double longitude, Double latitude, Float radioCloseness)
    {
        return (Math.pow(longitude-this.getLongitude(),2) + Math.pow(latitude-this.getLatitude() ,2)) < Math.pow(radioCloseness,2);
    }

    public Integer getIndexInRoute()
    {
        return indexInRoute;
    }

    public void setIndexInRoute(Integer indexInRoute)
    {
        this.indexInRoute = indexInRoute;
    }

    public boolean isSameLatLng(Double latitude, Double longitude)
    {
        return getLatitude().equals(latitude) && getLongitude().equals(longitude);
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}