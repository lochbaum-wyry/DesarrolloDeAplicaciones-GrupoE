package domain;

import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 02/05/16.
 */
public interface TestHelpersTrait
{
    // Funciones de ayuda para buildear objetos comunes

    default public RoutePoint aRoutePoint(Double currLongitude, Double currLatitude) {
        return new RoutePoint(currLatitude, currLongitude);
    }

    default public User aDriver()
    {
        return UserBuilder.aUser()
                .withName("Sarasa")
                .withLastName("Gil")
                .withEmail("sarasa@gmail.com")
                .withUserName("sarasa1982")
                .withVehicleCapacity(4)
                .build();
    }

    default public User aPassenger()
    {
        return UserBuilder.aUser()
                .withName("Sarasa")
                .withLastName("Gil")
                .withEmail("sarasa@gmail.com")
                .withUserName("sarasa1982")
                .build();
    }

    default public Route aCommonRouteWithLocations(int numberOfLocations, int longitudeShift, int latitudeShift)
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
