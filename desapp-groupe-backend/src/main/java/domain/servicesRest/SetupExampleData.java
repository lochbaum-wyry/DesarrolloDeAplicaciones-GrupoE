package domain.servicesRest;

import domain.*;
import domain.builders.RouteBuilder;
import domain.builders.ScheduleBuilder;
import domain.builders.VehicleBuilder;
import domain.exceptions.ProductException;
import domain.exceptions.SingUpException;
import domain.exceptions.SubiQueTeLlevoException;
import domain.services.*;
import helpers.UserAuthorization;
import org.joda.time.DateTime;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


public class SetupExampleData {
    UserService userService;
    PostService postService;
    RideService rideService;
    UserServiceRest userServiceRest;
    ProductsService productsService;
    RatingService ratingService;

    public SetupExampleData() {}

    public SetupExampleData(UserService userService, RideService rideService, PostService postService, UserServiceRest userServiceRest, ProductsService productsService, RatingService ratingService)
    {
        this.userService = userService;
        this.rideService = rideService;
        this.postService = postService;
        this.userServiceRest = userServiceRest;
        this.productsService = productsService;
        this.ratingService = ratingService;
    }

    public RideService getRideService() {
        return rideService;
    }

    public void setRideService(RideService rideService) {
        this.rideService = rideService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public void init() throws SubiQueTeLlevoException {
        try {
            User fede = userService.signUp("Federico Lochbaum", "Lochbaum", "trimegisto", "federico.lochbaum@gmail.com", "https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/8/005/074/165/2458a49.jpg");
            User otro = userService.signUp("algun", "otro", "algunotro", "algunotrousuario@gmail.com", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Albert_Einstein_Head_cleaned.jpg/250px-Albert_Einstein_Head_cleaned.jpg");

            Vehicle fedeMovil = VehicleBuilder.aVehicle().withCapacity(5).withOilWasterPerKm(0.05f).build();
            userService.addVehicleForUser(fede, fedeMovil);
            fede.setPoints(150000);
            userService.getUserRepository().update(fede);

            Rate rate = new Rate(otro, fede, null, null, null, "maestro!! aca esta la prueba");

            ratingService.getRateRepository().saveOrUpdate(rate);

            otro.setPoints(15000);
            userService.getUserRepository().update(otro);


            productsService.createProduct("Descuento de 10 % en Combustible", 2,10000);
            productsService.createProduct("El perdon de dios", 1,1000000000);
            productsService.createProduct("Paquete de papas fritas", 100,500);

            User dan  = userService.signUp("Dan", "Wyry", "danwyry", "dandanielw2@gmail.com", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Albert_Einstein_Head_cleaned.jpg/250px-Albert_Einstein_Head_cleaned.jpg");

            Vehicle ninjamovil = VehicleBuilder.aVehicle().withCapacity(5).withOilWasterPerKm(0.05f).build();
            userService.addVehicleForUser(dan, ninjamovil);

            List<Route> routes = new ArrayList<Route>();

            Route route = RouteBuilder.aRoute()
                    .withDistanceInKms(20f)
                    .withFixedCosts(100)
                    .withRoutePointAt(-58.2907153,-34.7035576)
                    .withRoutePointAt(-58.3435821,-34.6760004)
                    .build();

            Schedule schedule = ScheduleBuilder.aSchedule()
                    .withDay(DayOfWeek.FRIDAY)
                    .withDepartureTimeAt(9,30)
                    .build();

            route.addSchedule(schedule);
            dan.addRoute(route);
            dan.setPoints(15000);
            dan.getOrAddChatWith(fede);
            dan.getOrAddChatWith(otro);

            userService.addRoute(dan,route);


            RideRequest rr = rideService.requestRide(fede,dan,new DateTime(),route,route.getRoutePoints().get(0),route.getRoutePoints().get(1));
            rideService.acceptRideRequest(rr);


            DateTime date = new DateTime(2016,5,6,8,45);
            RoutePoint boardingAt = route.getRoutePoints().get(0);
            RoutePoint getOffAt = route.getRoutePoints().get(1);
            RideRequest rideRequest = new RideRequest(fede,dan,date,route, boardingAt, getOffAt);
            rideService.requestRide(rideRequest);


        } catch (SingUpException e)
        {
        } catch (ProductException e) {
            e.printStackTrace();
        }

//        return o;
    }
}
