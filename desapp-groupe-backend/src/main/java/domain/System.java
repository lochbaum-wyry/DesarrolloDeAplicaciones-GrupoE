package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class System
{
    private SystemSettings settings ;
    private GamingSystem gamingSystem;
    private List<User> users = new ArrayList<User>();

    public void signUp(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }


    public System()
    {
        this.gamingSystem = new GamingSystem(this);
        this.settings = SystemSettings.getInstance();
    }

    public List<Route> findRoutesSatisfying(DateTime date, Location departureLocation, Location getOffLocation)
    {
        return findRoutesSatisfying(date, settings.getDefaultTimeClosenessInSecs(), departureLocation,  getOffLocation, settings.getDefaultRadioCloseness());
    }

    public List<Route> findRoutesSatisfying(DateTime date, Integer secondsDateCloseness, Location departureLocation, Location getOffLocation, Float radioCloseness)
    {
        List<Route> result = users.stream().map(user -> user.getRoutes()).flatMap(routes -> routes.stream()).collect(Collectors.toList());

        result = result.stream()
                .filter(route -> route.matchesRequestedRoute(departureLocation,getOffLocation,radioCloseness) && route.isInDayAndHour(date,secondsDateCloseness) )
                .collect(Collectors.toList());

        return result;
    }


}
