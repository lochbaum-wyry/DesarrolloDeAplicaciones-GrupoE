package domain.services;

public class SystemSettings {

    private Float oilPrice = 10f;
    private Float defaultRadioCloseness = 200f;
    private Integer defaultTimeClosenessInSecs = 1800 ;
    

    public SystemSettings(){}

    public Float getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(Float oilPrice) {
        this.oilPrice = oilPrice;
    }

    public Float getDefaultRadioCloseness() {
        return defaultRadioCloseness;
    }

    public void setDefaultRadioCloseness(Float defaultRadioCloseness)
    {
        this.defaultRadioCloseness = defaultRadioCloseness;
    }

    public Integer getDefaultTimeClosenessInSecs() {
        return defaultTimeClosenessInSecs;
    }

    public void setDefaultTimeClosenessInSecs(Integer defaultTimeClosenessInSecs) {
        this.defaultTimeClosenessInSecs = defaultTimeClosenessInSecs;
    }
}
