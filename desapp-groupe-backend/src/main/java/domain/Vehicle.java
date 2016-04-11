package domain;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Rateable{

    private Integer capacity;
    private Double oilWastePerKm;
    private List<Rate> rates;

    public Vehicle(Integer capacity,Double oilWastePerKm){
        this.capacity = capacity;
        this.oilWastePerKm = oilWastePerKm;
        this.rates = new ArrayList<Rate>();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Double getOilWastePerKm() {
        return oilWastePerKm;
    }

    @Override
    public List<Rate> getRates() {
        return this.rates;
    }
}
