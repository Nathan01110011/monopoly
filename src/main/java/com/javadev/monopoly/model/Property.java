package com.javadev.monopoly.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Property {

    // Foreign key, doubles as board id number
    @Id
    Integer id;
    String name;
    Integer price;
    String colourGroup;
    Integer ownerId;

    // 1-4 houses, 5 = hotel
    // No clean way of doing prices, so all need to read in from file, instead
    // just doing something like rent = basicRent * number of houses
    // http://www.jdawiseman.com/papers/trivia/monopoly-rents.html
    Integer constructionLevel;
    Boolean isMortgaged;
    Integer baseRent;
    Integer oneHouseRent;
    Integer twoHousesRent;
    Integer threeHousesRent;
    Integer fourHousesRent;
    Integer hotelRent;

    public Property() {
    }

    public Property(Integer id, String name, Integer price, String colourGroup, Integer baseRent, Integer oneHouseRent, Integer twoHousesRent, Integer threeHousesRent, Integer fourHousesRent, Integer hotelRent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.colourGroup = colourGroup;
        this.constructionLevel = 0;
        this.isMortgaged = false;
        this.baseRent = baseRent;
        this.oneHouseRent = oneHouseRent;
        this.twoHousesRent = twoHousesRent;
        this.threeHousesRent = threeHousesRent;
        this.fourHousesRent = fourHousesRent;
        this.hotelRent = hotelRent;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getConstructionLevel() {
        return constructionLevel;
    }

    public void setConstructionLevel(Integer constructionLevel) {
        this.constructionLevel = constructionLevel;
    }

    public String getColourGroup() {
        return colourGroup;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getMortgaged() {
        return isMortgaged;
    }

    public void setMortgaged(Boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    public Integer getBaseRent() {
        return baseRent;
    }

    public Integer getOneHouseRent() {
        return oneHouseRent;
    }

    public Integer getTwoHousesRent() {
        return twoHousesRent;
    }

    public Integer getThreeHousesRent() {
        return threeHousesRent;
    }

    public Integer getFourHousesRent() {
        return fourHousesRent;
    }

    public Integer getHotelRent() {
        return hotelRent;
    }
}
