package domain.service_tests;

import domain.*;
import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;
import domain.repositories.*;
import domain.services.RideService;
import domain.services.gaming_service.GamingService;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring-all-contexts.xml")
@Transactional
abstract public class AbstractServiceTest implements ServiceTestHelpersTrait
{

    ApplicationContext servicecontext ;
    ApplicationContext persistencecontext ;

    @Autowired
    private SessionFactory sessionFactory;
    private Session currentSession;

    @Before
    public void openSession() {
        currentSession = sessionFactory.getCurrentSession();
    }


    @Autowired
    GamingService gamingService ;

    @Autowired
    public RideService rideService ;

    @Autowired
    public RideRepository rideRepo ;

    @Autowired
    public RouteRepository routeRepo ;

    @Autowired
    public UserRepository userRepo ;

    @Autowired
    public VehicleRepository vehicleRepo ;

    @Autowired
    public RideRequestRepository rideRequestRepo ;


    protected RideRequest aPersistedRideRequestMatchingOtherRequest(RideRequest exampleRideRequest) {
        User requester = aPersistedPassenger();

        RideRequest rideRequest = new RideRequest(requester, exampleRideRequest.getDriver(), exampleRideRequest.getDate(), exampleRideRequest.getRoute(), exampleRideRequest.getBoardingAt(), exampleRideRequest.getGetOffAt());
        rideRequestRepo.save(rideRequest);

        return rideRequest;
    }

    protected RideRequest aPersistedRideRequest()
    {
        Route route = aCommonRouteWithLocations(5,50,0);
        User requester = aPersistedPassenger();
        User driver = aPersistedDriver();
        DateTime date = new DateTime(2016,6,1,0,0,0);
        RoutePoint board = route.getRoutePoints().get(0);
        RoutePoint getOff = route.getRoutePoints().get(1);

        routeRepo.save(route);

        RideRequest rideRequest = rideService.requestRide(requester, driver, date, route, board, getOff);
        return rideRequest;
    }


    protected  User aPersistedPassenger() {
        User passenger = aPassenger();
        userRepo.save(passenger);
        userRepo.findById(passenger.getId());
        return passenger;
    }

    /**
     * Returns a persisted driver with a vehicle with capacity for 4 passengers at most, and oilWasterPerHour = 0.05f
     * @return
     */
    protected  User aPersistedDriver() {
        User driver = aDriver();
        userRepo.save(driver);
        userRepo.findById(driver.getId());
        return driver;
    }

}
