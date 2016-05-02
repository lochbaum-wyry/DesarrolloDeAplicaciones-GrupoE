package domain;

import domain.builders.RouteBuilder;
import domain.builders.UserBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prospero on 4/3/16.
 */
public class AbstractDomainTest implements TestHelpersTrait {

    protected User driverWithVehicle(Integer capacity) {
        return UserBuilder.aUser().withVehicleCapacity(capacity).build();
    }

}
