package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Route extends Entity{

    private List<Schedule> schedules = new ArrayList<Schedule>();
    private List<RoutePoint> routePoints = new ArrayList<RoutePoint>();
    private Float distanceInKms = 0f;
    private Float fixedCosts = 0f;

    public Route(Float distanceInKms, Float fixedCosts, List<LatLng> points, List<Schedule> schedules) {
        this.schedules = schedules;
        this.fixedCosts = fixedCosts;
        this.distanceInKms = distanceInKms;

        setRoutePointsFromLatLng(points);
    }

    public void setRoutePointsFromLatLng(List<LatLng> points)
    {
        points.stream().forEach(latLng -> this.addRoutePoint(latLng));
    }

    public Route() {

    }

    public void addRoutePoint(LatLng latLng)
    {
        RoutePoint routePoint = new RoutePoint(this,latLng.getLatitude(), latLng.getLongitude());
        this.routePoints.add(routePoint);
        updateIndexes();
    }

    public void addSchedule(Schedule schedule){ this.schedules.add(schedule); }

    public List<RoutePoint> getRoutePoints() {
        return routePoints;
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


    public void setDistanceInKms(Float distanceInKms) {
        this.distanceInKms = distanceInKms;
    }


    public void setRoutePoints(List<RoutePoint> routePoints)
    {
        this.routePoints = routePoints;
    }

    private void updateIndexes()
    {
        for (int i = 0; i < this.getRoutePoints().size() ; i++)
        {
            RoutePoint routePoint = this.getRoutePoints().get(i);
            routePoint.setIndexInRoute(i);
        }
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

}
