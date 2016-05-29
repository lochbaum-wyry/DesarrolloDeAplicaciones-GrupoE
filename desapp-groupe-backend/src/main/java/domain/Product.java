package domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Product extends Entity{
    private String name;
    private List<Image> pictures;
    private Integer cost;
    private Integer stock;

    public Product(){}

    public Product(String name,Integer cost,Integer stock){
        this.pictures = new ArrayList<Image>();
        this.cost = cost;
        this.name = name;
        this.stock = stock;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void addPicture(Image picture){
        this.pictures.add(picture);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPictures(List<Image> pictures) {
        this.pictures = pictures;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public List<Image> getPictures() {
        return pictures;
    }

    public Integer getStock() {
        return stock;
    }

}
