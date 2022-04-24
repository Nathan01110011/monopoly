package com.javadev.monopoly.model;

import javax.persistence.Entity;

// TODO: This should be completely redone as inheriting from a BoardTile class
@Entity
public class Rail extends BoardTile {


    Integer ownerId;

    Boolean isMortgaged;


    public Rail() {
    }

    public Rail(Integer boardTileId, Integer boardTileTypeId, String name) {
        super(boardTileId, boardTileTypeId, name);
        this.isMortgaged = false;
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

}
