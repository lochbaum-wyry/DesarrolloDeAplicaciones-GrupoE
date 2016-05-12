package domain.service_tests;

import domain.*;
import domain.exceptions.NoSeatsAvailableException;
import domain.services.MonthlyRanking;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public class GamingServiceTest extends AbstractServiceTest
{
    Logger log = Logger.getLogger(GamingServiceTest.class);
    List<User> drivers;
    List<User> passengers;


//    @Before
//    public void setUp()
//    {
//        drivers = new ArrayList<>();
//        passengers = new ArrayList<>();
//
//        generateASetOfDrivers();
//        generateASetOfPassengers();
//        makePassengersRequestForRides();
//        try {
//            makeDriversAcceptAndRejectRequests();
//        } catch (NoSeatsAvailableException e) {
//            e.printStackTrace();
//        }
//    }
//
    @Transactional
    @Test
    public void test_createRanking_CreateaRanking()
    {
//        MonthlyRanking ranking = gamingService.createRanking(4,2016);
//        Assert.assertTrue(ranking instanceof MonthlyRanking);
//
//
//        List<User> drivers = ranking.getMostEfficientDrivers();
//        java.lang.System.out.println(drivers);
    }



    private void makeDriversAcceptAndRejectRequests() throws NoSeatsAvailableException
    {
        User driver ;
        List<RideRequest> rideReqs ;
Ride ride;
        for (int i = 0 ; i < drivers.size() ; i++)
        {
            driver = drivers.get(i);
            rideReqs = rideService.getPendingRequestsFor(driver);

            for (RideRequest request : rideReqs)
            {
                if (i%2==0) {
                    ride = rideService.acceptRideRequest(request);
                    java.lang.System.out.println("Aceptando: req: " + request.getId() + " - driver: " + request.getDriver().getId() + " - pass: " + request.getRequester().getId() );
                    java.lang.System.out.println("y devuelve el ride: " + ride.getId() );

                }
                else {
                    rideService.rejectRideRequest(request);
                    java.lang.System.out.println("Rechazando: req: " + request.getId() + " - driver: " + request.getDriver().getId() + " - pass: " + request.getRequester().getId() );
                }
            }
        }
    }

    private void makePassengersRequestForRides()
    {
        RideRequest rr ;
        DateTime date = new DateTime();
        User driver = null;
        User passenger ;
        Route route = null ;
        RoutePoint board = null;
        RoutePoint getOff = null;

        for (int d = 0, p = 0 ; d < 10 ; d++)
        {
            driver = drivers.get(d);
            route = driver.getRoutes().get(0);
            board = route.getRoutePoints().get(0);
            getOff = route.getRoutePoints().get(route.getRoutePoints().size() - 1);
            for ( int i = 0 ;  i < 3 ; i++, p++ )
            {
                passenger = passengers.get(p);
                rr  = rideService.requestRide(passenger, driver, date, route, board, getOff);
                java.lang.System.out.println("Agregando request: " + rr.getId() + " - Driver: " + driver.getId() + " - pass : " + passenger.getId());
            }
        }

    }

    private void generateASetOfPassengers()
    {
        for (int i = 0 ; i < 30 ; ++i)
        {
            passengers.add(aPersistedPassenger());
        }
    }

    private void generateASetOfDrivers()
    {
        User driver ;
        for (int i = 0  ; i < 10 ; ++i )
        {
            driver = aDriver();
            driver.addRoute(aCommonRouteWithLocations(5,50,50));
            userRepo.save(driver);
            drivers.add((User)userRepo.findById(driver.getId()));
        }
    }

}
