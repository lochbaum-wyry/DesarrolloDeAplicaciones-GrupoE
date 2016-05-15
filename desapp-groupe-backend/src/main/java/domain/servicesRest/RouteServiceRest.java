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

//
//    @POST
//    @Path("/singUp")
//    @Consumes("application/json")
//    public Response signUp(User user){
//        Response response;
//        try {
//            userService.singUp(user.getName(),user.getLastName(),user.getUserName(),user.getEmail());
//            response = Response.ok().tag("El usuario fue creado Correctamente").build();
//        } catch (SingUpException e) {
//            response = Response.serverError().tag("No se pudo crear el Usuario Correctamente").build();
//        }
//
//        return response;
//    }
//
//    @POST
//    @Path("/addRouteForUser")
//    @Consumes("application/json")
//    public Response addRouteForUser(User user, List<RoutePoint> points, Float distanceInKms, Float fixedCosts, List<Schedule> schedules){
//        Response response;
//        //try {
//            userService.addRouteForUser(user,points,distanceInKms,fixedCosts,schedules);
//            response = Response.ok().tag("La ruta fue agregar Correctamente").build();
//        //} catch (SingUpException e) {
//        //    response = Response.serverError().tag("No se pudo agregar la ruta Correctamente").build();
//        //}
//
//        return response;
//    }
//
//
//

}
