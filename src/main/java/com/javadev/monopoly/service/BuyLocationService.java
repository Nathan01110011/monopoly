package com.javadev.monopoly.service;

import com.javadev.monopoly.model.Player;
import com.javadev.monopoly.model.Property;
import com.javadev.monopoly.repository.PlayerRepository;
import com.javadev.monopoly.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyLocationService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public void buyProperty(Player player, Property property) {

        Integer playerBankBalance = player.getBankBalance();
        Integer propertyCost = property.getPrice();

        // If player does not have fund to buy, not sure if they should get the chance to sell assets
        if (playerBankBalance >= propertyCost) {
            player.setBankBalance(playerBankBalance - propertyCost);
            property.setOwnerId(player.getId());
            playerRepository.save(player);
            propertyRepository.save(property);
            System.out.println("Property bought! " + player.getName() + " now has £" + player.getBankBalance() + " in the bank!");
        } else {
            System.out.println(player.getName() + ", you can't afford this property.");
        }
    }

}
