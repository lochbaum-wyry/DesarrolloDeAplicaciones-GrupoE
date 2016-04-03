package domain;

public class Vehicle {

    private Integer capacity;
    private Double oilWasterPerHour;

    public Vehicle(Integer capacity,Double oilWasterPerHour){
        this.capacity = capacity;
        this.oilWasterPerHour = oilWasterPerHour;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Double getOilWasterPerHour() {
        return oilWasterPerHour;
    }

}
