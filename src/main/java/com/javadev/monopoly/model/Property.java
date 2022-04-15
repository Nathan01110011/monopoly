package com.javadev.monopoly.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Property {

    // Doubles as board id number
    @Id
    Integer id;
    String name;
    Integer price;
    String colourGroup;

    // 1-4 houses, 5 = hotel
    Integer constructionLevel;

    public Property(Integer id, String name, Integer price, String colourGroup) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.colourGroup = colourGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getConstructionLevel() {
        return constructionLevel;
    }

    public void setConstructionLevel(Integer constructionLevel) {
        this.constructionLevel = constructionLevel;
    }
}
