package domain.services;

import domain.RoutePoint;
import domain.Route;
import domain.User;
import domain.services.gaming_service.GamingService;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class System
{
    private SystemSettings settings ;
    private GamingService gamingService;
    private List<User> users = new ArrayList<User>();


    public List<User> getUsers() {
        return users;
    }

    public System(SystemSettings systemSettings, GamingService gamingService)
    {
        this.settings = systemSettings;
        this.gamingService = gamingService;
    }

    public void setSettings(SystemSettings settings)
    {
        this.settings = settings;
    }
//
//    public List<Route> findRoutesSatisfying(DateTime date, Integer secondsDateCloseness, RoutePoint departureRoutePoint, RoutePoint getOffRoutePoint, Float radioCloseness)
//    {
//        List<Route> result = users.stream().map(user -> user.getRoutes()).flatMap(routes -> routes.stream()).collect(Collectors.toList());
//
//        result = result.stream()
//                .filter(route -> route.matchesRequestedRoute(departureRoutePoint, getOffRoutePoint,radioCloseness) && route.isInDayAndHour(date,secondsDateCloseness) )
//                .collect(Collectors.toList());
//
//        return result;
//    }


    public SystemSettings getSettings() {
        return settings;
    }
}
