package com.javadev.monopoly.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Card {

    @Id
    Integer id;

}
