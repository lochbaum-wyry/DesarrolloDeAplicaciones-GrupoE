package domain.service_tests;

import domain.RoutePoint;
import domain.Route;
import domain.User;
import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
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
abstract public class AbstractServiceTest
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




    // Funciones de ayuda para buildear objetos comunes

    protected RoutePoint aRoutePoint(Double currLongitude, Double currLatitude) {
        return new RoutePoint(currLatitude, currLongitude);
    }

    protected User aDriver()
    {
        return UserBuilder.aUser()
                .withName("Sarasa")
                .withLastName("Gil")
                .withEmail("sarasa@gmail.com")
                .withUserName("sarasa1982")
                .withVehicleCapacity(4)
                .build();
    }

    protected User aPassenger()
    {
        return UserBuilder.aUser()
                .withName("Sarasa")
                .withLastName("Gil")
                .withEmail("sarasa@gmail.com")
                .withUserName("sarasa1982")
                .build();
    }


    protected Route aCommonRouteWithLocations(int numberOfLocations, int longitudeShift, int latitudeShift)
    {
        List<RoutePoint> routePoints = new ArrayList<>();
        Double currLongitude = 0d ;
        Double currLatitude = 0d;

        for (int i = 0 ; i < numberOfLocations ; ++i)
        {
            currLatitude = currLongitude + longitudeShift ;
            currLatitude = currLatitude + latitudeShift;
            routePoints.add(aRoutePoint(currLongitude, currLatitude));
        }

        return RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePoints(routePoints)
                .build();
    }

}
