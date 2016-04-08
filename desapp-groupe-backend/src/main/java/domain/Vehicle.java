package domain;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    private Integer capacity;
    private Double oilWastePerKm;
    private List<Rate> rates  ;

    public Vehicle(Integer capacity,Double oilWastePerKm){
        this.capacity = capacity;
        this.oilWastePerKm = oilWastePerKm;
        this.rates = new ArrayList<Rate>();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void addRate(Rate rate)
    {
        this.rates.add(rate);
    }

    public Double getOilWastePerKm() {
        return oilWastePerKm;
    }
}
