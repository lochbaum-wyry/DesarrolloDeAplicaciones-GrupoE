package domain;

import domain.rating_service.Rate;

import java.util.ArrayList;
import java.util.List;


public class Vehicle  extends Entity
{
    private User owner;
    private Integer capacity;
    private Float oilWastePerKm;
    private List<Rate> rates;

    public Vehicle(Integer capacity,Float oilWastePerKm)
    {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
