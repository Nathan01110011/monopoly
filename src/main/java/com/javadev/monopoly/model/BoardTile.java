package com.javadev.monopoly.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BoardTile {

    /*
    boardTileId represents each tile around the board from 0 - 39
    boardTypeId is the kind of tile the id represents -
    0 - Go
    1 - Property
    2 - Rail
    3 - Utility
    4 - Chance
    5 - Community Chest
    6 - Jail
    7 - Go to Jail
    8 - Fine/Tax
    9 - Free Parking
    ownerId only relevant for purchasable tiles, to track ownership
     */

    @Id
    Integer id;
    Integer typeId;

    public BoardTile() {
    }

    public BoardTile(Integer boardTileId, Integer boardTileTypeId) {
        this.id = boardTileId;
        this.typeId = boardTileTypeId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTypeId() {
        return typeId;
    }
}
