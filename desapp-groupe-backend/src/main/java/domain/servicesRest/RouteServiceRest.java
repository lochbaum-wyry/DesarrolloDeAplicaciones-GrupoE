package domain.servicesRest;

import domain.services.RideProposal;
import domain.services.RouteService;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("route")
@Service
public class RouteServiceRest {

    RouteService routeService;

    public RouteServiceRest(RouteService routeService) {
        this.routeService = routeService;
    }

    @GET
    @Path("getRideProposals/{date}/{departurePoint}/{arrivalPoint}")
    @Produces("application/json")
    public List<RideProposal> getRideProposals(@PathParam("date") final DateTimeTransformer date, @PathParam("departurePoint") final LatLngTransformer departurePoint, @PathParam("arrivalPoint") final LatLngTransformer arrivalPoint)
    {
        java.lang.System.out.println(departurePoint.getValueStr());
        java.lang.System.out.println(arrivalPoint.getValueStr());
        return routeService.getRideProposalsCloseTo(date.getValue(), 1800, departurePoint.getValue(), arrivalPoint.getValue(), 1000d);
    }


}
