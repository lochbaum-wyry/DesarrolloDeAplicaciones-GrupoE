package domain;

import org.codehaus.jackson.annotate.JsonIgnore;

public class RoutePoint extends Entity
{
    @JsonIgnore
    private Route route;
    private Integer indexInRoute;
    private Double latitude;

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonIgnore
    public Route getRoute() {
        return route;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    private Double longitude;
    public RoutePoint()
    {}
    public RoutePoint(Route route, Double latitude, Double longitude)
    {
        this(latitude, longitude);
        this.route = route;
    }

    public RoutePoint(Double latitude, Double longitude, Integer indexInRoute)
    {
        this.indexInRoute = indexInRoute;
        this.latitude = latitude;
        this.longitude = longitude ;
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

    @JsonIgnore
    public void setRoute(Route route) {
        this.route = route;
    }

}
