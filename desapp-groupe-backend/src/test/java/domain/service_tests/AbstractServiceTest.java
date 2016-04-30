package domain.service_tests;

import domain.Location;
import domain.Route;
import domain.User;
import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;
import domain.repositories.HibernateGenericDao;
import domain.services.GenericService;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    protected Location aLocation(Double currLongitude, Double currLatitude) {
        return new Location(currLongitude, currLatitude);
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
        List<Location> locations = new ArrayList<>();
        Double currLongitude = 0d ;
        Double currLatitude = 0d;

        for (int i = 0 ; i < numberOfLocations ; ++i)
        {
            currLatitude = currLongitude + longitudeShift ;
            currLatitude = currLatitude + latitudeShift;
            locations.add(aLocation(currLongitude, currLatitude));
        }

        return RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withLocations(locations)
                .build();
    }

}
