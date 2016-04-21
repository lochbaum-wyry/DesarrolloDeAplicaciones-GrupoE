package domain;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Vehicle")
public class Vehicle implements Rateable{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="capacity")
    private Integer capacity;
    @Column(name="oilWastePerKm")
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

    public int getId() {
        return id;
    }
}
