package domain;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class System {
    private List<User> users = new ArrayList<User>();

    public void signUp(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Route> findRoutesSatisfying(DateTime date, Location departureLocation, Location getOffLocation) {
        List<Route> result = users.stream().map(user -> user.getRoutes()).flatMap(routes -> routes.stream()).collect(Collectors.toList());

        result = result.stream().filter(route ->  route.matchesRequestedRoute(date,departureLocation,getOffLocation)  ).collect(Collectors.toList());

        return result;
    }


}
