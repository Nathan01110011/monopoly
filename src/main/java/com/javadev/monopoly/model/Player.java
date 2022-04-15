package com.javadev.monopoly.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    Integer id;
    String name;
    Integer bankBalance;
    Boolean active;
    Integer boardPosition;
    Boolean isJailed;
    Integer turnsInJail;

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

    public Integer getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(Integer bankBalance) {
        this.bankBalance = bankBalance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(Integer boardPosition) {
        this.boardPosition = boardPosition;
    }

    public Boolean getJailed() {
        return isJailed;
    }

    public void setJailed(Boolean jailed) {
        isJailed = jailed;
    }

    public Integer getTurnsInJail() {
        return turnsInJail;
    }

    public void setTurnsInJail(Integer turnsInJail) {
        this.turnsInJail = turnsInJail;
    }

    public Player() {
    }

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
        // Initialised with 1000
        this.bankBalance = 1000;
        // Start game as active
        this.active = true;
        // Start on GO
        this.boardPosition = 0;
        // Start out of jail
        this.isJailed = false;
        this.turnsInJail = 0;
    }

}
