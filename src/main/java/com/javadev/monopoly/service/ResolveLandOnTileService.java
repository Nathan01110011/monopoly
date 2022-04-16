package com.javadev.monopoly.service;

import com.javadev.monopoly.model.BoardTile;
import com.javadev.monopoly.model.Player;
import com.javadev.monopoly.model.Property;
import com.javadev.monopoly.repository.BoardTileRepository;
import com.javadev.monopoly.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ResolveLandOnTileService {

    @Autowired
    BoardTileRepository boardTileRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    BuyLocationService buyLocationService;

    @Autowired
    SettleDebtsService settleDebtsService;


    public void resolveLandingOnTile(Player currentPlayer, Integer diceRoll){
        BoardTile boardTile = boardTileRepository.findById(currentPlayer.getBoardPosition()).get();
        System.out.println("Landed on " + boardTile.getId() + " which is type " + boardTile.getTypeId());

        switch(boardTile.getTypeId()) {
            case 0:
                System.out.println(currentPlayer.getName() + " has landed on GO!");
                break;
            case 1:
                // 1 = Property
                resolveLandingOnPropertyTile(currentPlayer);
                break;
            case 2:
                // 2 = Rail
                resolveLandingOnRailTile(currentPlayer);
                break;
            case 3:
                // 3 = Utility
                resolveLandingOnUtilityTile(currentPlayer, diceRoll);
                break;
            case 4:
                // 4 = Chance
                resolveLandingOnCardTile(currentPlayer, "Chance");
                break;
            case 5:
                // 5 = Community Chest
                resolveLandingOnCardTile(currentPlayer, "Community Chest");
                break;
            case 6:
                // 6 = Jail
                System.out.println(currentPlayer.getName() + " is visiting the jailhouse!");
                break;
            case 7:
                // 7 = Go To Jail
                resolveLandingOnGoToJailTile(currentPlayer);
                break;
            case 8:
                // 8 = Fines
                resolveLandingOnFineTile(currentPlayer);
                break;
            case 9:
                // 9 = Free Parking
                resolveLandingOnFreeParkingTile(currentPlayer);
                break;
            default:
                // This is an exhaustive list of tile type ids, something is broken if this is hit
        }


    }

    private void resolveLandingOnPropertyTile(Player player) {
        List<Property> propertyList = propertyRepository.findAll();
        Property property = propertyRepository.findById(player.getBoardPosition()).get();
        System.out.println(player.getName() + " has landed on " + property.getName());

        if (property.getOwnerId() == null) {
            buyProperty(player, property);
        } else if (!property.getOwnerId().equals(player.getId())) {
            // TODO: Check if mortgaged, if not calculate fees
            if (property.getMortgaged()) {
                System.out.println("Property is mortgaged, no fees to pay!");
            } else {
                settleDebtsService.settlePropertyDebtsToPlayer(player, property);
            }
        } else {
            System.out.println("You own this property, have a nice time, enjoy!");
        }
    }


    private void resolveLandingOnRailTile(Player player) {



    }

    private void resolveLandingOnUtilityTile(Player player, Integer diceRoll) {



    }

    private void resolveLandingOnCardTile(Player player, String cardType) {

        System.out.println(player.getName() + " has landed on a " + cardType + " tile. Drawing Card!");
        // TODO: Draw card logic

    }

    private void resolveLandingOnGoToJailTile(Player player) {

        // TODO: Move character to jail tile, set jail status to 3 turns, end turn

    }

    private void resolveLandingOnFineTile(Player player) {
        // TODO: Remove cash from player wallet, add to free parking space if active

    }

    private void resolveLandingOnFreeParkingTile(Player player) {
        // TODO: If option on, give player all fees and fines collected from other payers so far

    }


    private void buyProperty(Player player, Property property) {
        System.out.println("Do you want to purchase " + property.getName() +"? Yes (Y) No (N)");
        Scanner scanner = new Scanner(System.in);
        String purchaseDecision = null;
        while (purchaseDecision == null) {
            if (scanner.hasNext()) {
                purchaseDecision = scanner.nextLine();
                if (purchaseDecision.equalsIgnoreCase("Y") | purchaseDecision.equalsIgnoreCase("YES")){
                    buyLocationService.buyProperty(player, property);
                } else if (purchaseDecision.equalsIgnoreCase("N") | purchaseDecision.equalsIgnoreCase("NO")) {
                    // TODO: If auction mode on, auction property
                } else {
                    // Reset to null if wrong input made
                    System.out.println("Sorry, Yes (Y) or No (N) answers only please!");
                    purchaseDecision = null;
                }
            }
        }
    }

}
