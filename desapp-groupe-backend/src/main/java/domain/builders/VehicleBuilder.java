package domain.builders;

import domain.Vehicle;

public class VehicleBuilder {

    public static VehicleBuilder aVehicle() {
        return new VehicleBuilder();
    }

    public Vehicle build() {
        return new Vehicle();
    }
}
