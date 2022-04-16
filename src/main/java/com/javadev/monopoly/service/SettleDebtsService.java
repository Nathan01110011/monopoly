package com.javadev.monopoly.service;

import com.javadev.monopoly.model.Player;
import com.javadev.monopoly.model.Property;
import com.javadev.monopoly.repository.PlayerRepository;
import com.javadev.monopoly.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettleDebtsService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PropertyRepository propertyRepository;

    // TODO: If a player has debts, this class must be called to settle them

    public void settlePropertyDebtsToPlayer(Player debtor, Property property) {

        Player creditor = playerRepository.findById(property.getOwnerId()).get();
        Integer fee = 0;
        // Find out if player owns all properties
        List<Property> propertyList = propertyRepository.findAllByColourGroup(property.getColourGroup());
        boolean creditorOwnAllPropertiesInSet = true;
        for (Property p : propertyList) {

            if (!(p.getOwnerId() != null && p.getOwnerId().equals(creditor.getId()))) {
                creditorOwnAllPropertiesInSet = false;
                break;
            }
        }

        if (creditorOwnAllPropertiesInSet) {
            // Owns set but no buildings on them doubles rent
            if (property.getConstructionLevel().equals(0)) {
                fee = property.getBaseRent() * 2;
            } else {
                switch (property.getConstructionLevel()) {
                    case 1:
                        fee = property.getOneHouseRent();
                        System.out.println("One house on property!");
                        break;
                    case 2:
                        fee = property.getTwoHousesRent();
                        System.out.println("Two houses on property!");
                        break;
                    case 3:
                        fee = property.getThreeHousesRent();
                        System.out.println("Three houses on property!");
                        break;
                    case 4:
                        fee = property.getFourHousesRent();
                        System.out.println("Four houses on property!");
                        break;
                    case 5:
                        fee = property.getHotelRent();
                        System.out.println("ONE HOTEL ON PROPERTY! OUCH!");
                        break;
                }
            }
        } else {
            // No properties, no set, base rent
            fee = property.getBaseRent();
        }

        System.out.println(debtor.getName() + " owes " + creditor.getName() + " £" + fee + " for landing on " + property.getName() + ".");

        if (debtor.getBankBalance() < fee) {
            while (debtor.getBankBalance() > fee) {
                forceDebtorToSellAssets(debtor, fee);
            }
        }

        creditor.setBankBalance(creditor.getBankBalance() + fee);
        playerRepository.save(creditor);

        debtor.setBankBalance(debtor.getBankBalance() - fee);
        playerRepository.save(debtor);

        System.out.println("Debt paid! " + debtor.getName() + " now has £" + debtor.getBankBalance() + " in the bank!");
        System.out.println(creditor.getName() + " now has a balance of £" + creditor.getBankBalance() + ". Thanks!");
    }

    public void payJailExitFee(Player debtor) {
        if (debtor.getBankBalance() < 50) {
            forceDebtorToSellAssets(debtor, 50);
        } else {
            debtor.setBankBalance(debtor.getBankBalance() - 50);
        }
        System.out.println("Jail fee paid, " + debtor.getName() + " now has " + debtor.getBankBalance() + ".");
    }

    private void forceDebtorToSellAssets(Player debtor, Integer debt) {

        while (debtor.getBankBalance() < debt) {
            List<Property> propertyList = propertyRepository.findAllByOwnerId(debtor.getId());

        }
        System.out.println("Enough money to pay debts now!");
    }


}
