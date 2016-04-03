package domain;

import domain.builders.UserBuilder;

/**
 * Created by prospero on 4/3/16.
 */
public class AbstractDomainTest {

    protected User driverWithVehicle(Integer capacity) {
        return UserBuilder.aUser().withVehicleCapacity(capacity).build();
    }




}
