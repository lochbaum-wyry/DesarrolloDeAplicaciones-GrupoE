package domain.servicesRest;

import domain.Route;
import domain.services.RouteService;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/route")
@Service
public class RouteServiceRest {

    RouteService routeService;

    public RouteServiceRest(RouteService routeService) {
        this.routeService = routeService;
    }

    @GET
    @Path("/findRoutesCloseTo/{date}/{departurePoint}/{arrivalPoint}")
    @Produces("application/json")
    public List<Route> findRoutesCloseTo(@PathParam("date") final DateTimeTransformer date, @PathParam("departurePoint") final LatLngTransformer departurePoint, @PathParam("arrivalPoint") final LatLngTransformer arrivalPoint)
    {
        return routeService.findRoutesCloseTo(date.getValue(), 1800, departurePoint.getValue(), arrivalPoint.getValue(), 1000d);
    }


}
