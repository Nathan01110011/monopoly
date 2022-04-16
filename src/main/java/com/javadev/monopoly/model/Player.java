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
    Integer turnsLeftInJail;
    Integer getOutOfJailFreeCards;

    public void sendPlayerToJail() {
        this.isJailed = true;
        this.turnsLeftInJail = 3;
        this.boardPosition = 10;
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

    public Integer getTurnsLeftInJail() {
        return turnsLeftInJail;
    }

    public void setTurnsLeftInJail(Integer turnsLeftInJail) {
        this.turnsLeftInJail = turnsLeftInJail;
    }


    public Integer getGetOutOfJailFreeCards() {
        return getOutOfJailFreeCards;
    }

    public void setGetOutOfJailFreeCards(Integer getOutOfJailFreeCards) {
        this.getOutOfJailFreeCards = getOutOfJailFreeCards;
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
        this.turnsLeftInJail = 0;
        this.getOutOfJailFreeCards = 0;
    }

}
