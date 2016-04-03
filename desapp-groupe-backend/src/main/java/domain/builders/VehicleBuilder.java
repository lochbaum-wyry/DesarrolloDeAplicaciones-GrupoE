package domain.builders;

import domain.Vehicle;

public class VehicleBuilder {

    private Integer capacity;
    private Double oilWasterPerHour;

    public static VehicleBuilder aVehicle() {
        return new VehicleBuilder();
    }

    public VehicleBuilder withCapacity(Integer capacity){
        this.capacity = capacity;
        return this;
    }

    public VehicleBuilder withOilWasterPerHour(Double oilWasterPerHour){
        this.oilWasterPerHour = oilWasterPerHour;
        return this;
    }

    public Vehicle build() {
        return new Vehicle(capacity,oilWasterPerHour);
    }
}
