package com.javadev.monopoly.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CommunityChest extends Card {

    String description;
    // Action ids (0 = reward, 1 = fine, 2 = relocation, 3 = go to jail, 4 = Get out of jail card, 5 = assessed for repairs)
    Integer action;
}
