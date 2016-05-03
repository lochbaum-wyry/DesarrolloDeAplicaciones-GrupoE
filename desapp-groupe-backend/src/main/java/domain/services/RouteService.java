package domain.services;

import domain.LatLng;
import domain.Route;
import domain.RoutePoint;
import domain.repositories.RoutePointRepository;
import domain.repositories.RouteRepository;
import org.joda.time.DateTime;

import java.util.List;

public class RouteService
{
    private final RoutePointRepository routePointRepository;
    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository , RoutePointRepository routePointRepository)
    {
        this.routeRepository = routeRepository;
        this.routePointRepository = routePointRepository ;
    }

    public List<Route> findRoutesSatisfying(DateTime date, Integer secondsDateCloseness, LatLng departureRoutePoint, LatLng arrivalRoutePoint, Float radioCloseness)
    {
        return routeRepository.findRoutesSatisfiying(date, secondsDateCloseness, departureRoutePoint, arrivalRoutePoint, radioCloseness);
    }

}
