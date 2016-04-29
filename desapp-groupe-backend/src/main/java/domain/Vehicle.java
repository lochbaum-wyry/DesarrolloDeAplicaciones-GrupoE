package domain;

import java.util.ArrayList;
import java.util.List;


public class Vehicle  extends Entity
{
    private User owner;
    private Integer capacity;
    private Float oilWastePerKm;
    private List<Rate> rates;
    private Integer totalRateCount = 0 ;
    private Integer goodRateCount = 0 ;
    private Integer badRateCount = 0 ;

    public Vehicle(Integer capacity,Float oilWastePerKm)
    {
        this.capacity = capacity;
        this.oilWastePerKm = oilWastePerKm;
        this.rates = new ArrayList<Rate>();
    }

    public void updateRateCounters(Rate rate)
    {
        this.setTotalRateCount(this.getTotalRateCount() + 1);
        switch (rate.getValue())
        {
            case GOOD:
                this.setGoodRateCount(this.getGoodRateCount() + 1);
                break;
            case BAD:
                this.setBadRateCount(this.getBadRateCount() + 1);
                break;
        }
    }


    public Integer getBadRateCount() {
        return badRateCount;
    }

    public void setBadRateCount(Integer badRateCount) {
        this.badRateCount = badRateCount;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getGoodRateCount() {
        return goodRateCount;
    }

    public void setGoodRateCount(Integer goodRateCount) {
        this.goodRateCount = goodRateCount;
    }

    public Float getOilWastePerKm() {
        return oilWastePerKm;
    }

    public void setOilWastePerKm(Float oilWastePerKm) {
        this.oilWastePerKm = oilWastePerKm;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public Integer getTotalRateCount() {
        return totalRateCount;
    }

    public void setTotalRateCount(Integer totalRateCount) {
        this.totalRateCount = totalRateCount;
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
