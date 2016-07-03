package domain;

import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;
import domain.builders.VehicleBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 02/05/16.
 */
public interface TestHelpersTrait
{
    // Funciones de ayuda para buildear objetos comunes

    default public LatLng aLatLng(Double currLongitude, Double currLatitude) {
        return new LatLng(currLatitude, currLongitude);
    }

    default public User aDriver()
    {
        Vehicle vehicle = aVehicle();
        User user = UserBuilder.aUser()
                .withName("Sarasa")
                .withLastName("Gil")
                .withEmail("sarasa@gmail.com")
                .withUserName("sarasa1982")
                .withVehicle(vehicle)
                .build();
        vehicle.setOwner(user);
        return user;
    }

    default Vehicle aVehicle() {
       return VehicleBuilder.aVehicle().withOilWasterPerKm(0.05f).withCapacity(4).build();
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
        List<LatLng> routePoints = new ArrayList<>();
        Double currLongitude = 0d ;
        Double currLatitude = 0d;

        for (int i = 0 ; i < numberOfLocations ; ++i)
        {
            currLatitude = currLongitude + longitudeShift ;
            currLatitude = currLatitude + latitudeShift;
            routePoints.add(aLatLng(currLongitude, currLatitude));
        }

        return RouteBuilder.aRoute()
                .withDistanceInKms(20f)
                .withFixedCosts(100)
                .withRoutePoints(routePoints)
                .build();
    }
}
