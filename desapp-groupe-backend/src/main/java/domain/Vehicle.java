package domain;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Rateable{

    private Integer capacity;
    private Double oilWastePerKm;

    public Vehicle(Integer capacity,Double oilWastePerKm){
        this.capacity = capacity;
        this.oilWastePerKm = oilWastePerKm;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Double getOilWastePerKm() {
        return oilWastePerKm;
    }
}
