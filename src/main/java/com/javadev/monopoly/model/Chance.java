package com.javadev.monopoly.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Chance extends Card {

    @Id
    Integer id;

}
