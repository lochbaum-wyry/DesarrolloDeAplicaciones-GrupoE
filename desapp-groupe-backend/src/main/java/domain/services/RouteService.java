package domain.services;


import domain.LatLng;
import domain.Route;
import domain.repositories.RoutePointRepository;
import domain.repositories.RouteRepository;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RouteService
{
    private  RoutePointRepository routePointRepository;
    private  RouteRepository routeRepository;

    public RouteService(){}

    public RouteService(RouteRepository routeRepository , RoutePointRepository routePointRepository)
    {
        this.routeRepository = routeRepository;
        this.routePointRepository = routePointRepository ;
    }
    @Transactional
    public List<Route> findRoutesCloseTo(DateTime date, Integer secondsDateCloseness, LatLng departureRoutePoint, LatLng arrivalRoutePoint, Double radioCloseness)
    {
        return routeRepository.findRoutesCloseTo(date, secondsDateCloseness, departureRoutePoint, arrivalRoutePoint, radioCloseness);
    }

}
