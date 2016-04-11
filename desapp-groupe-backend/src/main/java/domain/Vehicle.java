package domain;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Rateable{

    private Integer capacity;
    private Float oilWastePerKm;
    private List<Rate> rates;

    public Vehicle(Integer capacity,Float oilWastePerKm){
        this.capacity = capacity;
        this.oilWastePerKm = oilWastePerKm;
        this.rates = new ArrayList<Rate>();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Float getOilUsePerKmInLts() {
        return oilWastePerKm;
    }

    @Override
    public List<Rate> getRates() {
        return this.rates;
    }
}
