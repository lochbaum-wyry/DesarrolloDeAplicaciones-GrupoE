package domain.services;


import domain.LatLng;
import domain.Ride;
import domain.Route;
import domain.Schedule;
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
            RideProposal proposal = new RideProposal(route, schedule, date);
            result.add(proposal);
        }
        return result ;
    }

}
