package com.javadev.monopoly.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
    String name;

    public BoardTile() {
    }

    public BoardTile(Integer boardTileId, Integer boardTileTypeId, String name) {
        this.id = boardTileId;
        this.typeId = boardTileTypeId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }
}
