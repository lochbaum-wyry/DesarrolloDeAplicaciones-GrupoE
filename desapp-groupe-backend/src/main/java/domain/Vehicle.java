package domain;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    private Integer capacity;
    private Double oilWasterPerHour;
    private List<Rate> rates  ;

    public Vehicle(Integer capacity,Double oilWasterPerHour){
        this.capacity = capacity;
        this.oilWasterPerHour = oilWasterPerHour;
        this.rates = new ArrayList<Rate>();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void addRate(Rate rate)
    {
        this.rates.add(rate);
    }
}
