package domain.services;


import domain.*;
import domain.repositories.RoutePointRepository;
import domain.repositories.RouteRepository;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<RideProposal> getRideProposalsCloseTo(DateTime date, Integer secondsDateCloseness, LatLng departureRoutePoint, LatLng arrivalRoutePoint, Double radioCloseness)
    {
        List rows = routeRepository.findRouteSchedulesCloseTo(date, secondsDateCloseness, departureRoutePoint, arrivalRoutePoint, radioCloseness);
        return routeScheduleToRideProposal(date, rows);
    }

    private List<RideProposal> routeScheduleToRideProposal(DateTime date, List rows) {
        List<RideProposal> result = new ArrayList<>();

        for (int i = 0 ; i < rows.size() ; i++)
        {
            Object [] row = (Object [] )rows.get(i);
            Route route = (Route)row[0];
            Schedule schedule = (Schedule)row[1];
            RoutePoint departure = (RoutePoint) row[2];
            RoutePoint arrival = (RoutePoint) row[3];
            User driver = (User) row[4];
            RideProposal proposal = new RideProposal(driver, route, schedule, date, departure, arrival);
            result.add(proposal);
        }
        return result ;
    }

    public RouteRepository getRouteRepository() {
        return routeRepository;
    }

    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public RoutePointRepository getRoutePointRepository() {
        return routePointRepository;
    }
}
