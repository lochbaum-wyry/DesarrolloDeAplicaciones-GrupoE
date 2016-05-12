package domain;

import domain.builders.UserBuilder;



public class AbstractDomainTest implements TestHelpersTrait {

    protected User driverWithVehicle(Integer capacity) {
        return UserBuilder.aUser().withVehicleCapacity(capacity).build();
    }

}
