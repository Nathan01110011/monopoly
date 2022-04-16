package com.javadev.monopoly.service;

import com.javadev.monopoly.model.Player;
import com.javadev.monopoly.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class PlayGameService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ResolveLandOnTileService resolveLandOnTileService;

    @Autowired
    SettleDebtsService settleDebtsService;

    // Game runs when there are more than one active player - returns winner when only one remains
    public Player playGame() {
        System.out.println("STARTING GAME!");
        List<Player> activePlayers = playerRepository.findByActiveTrue();
        // Player ID 1 goes first, then rotates incrementally
        Player currentPlayer = playerRepository.findById(0).get();

        while (activePlayers.size() > 1) {

            // Refresh active players
            activePlayers = playerRepository.findByActiveTrue();

            System.out.println("\n" + currentPlayer.getName() + " is up!");
            movePlayer(currentPlayer);
            playerPhaseOne();
            playerPhaseTwo();

            // Turn Ended, next player
            currentPlayer = playerEndTurn(currentPlayer, activePlayers);

        }
        return activePlayers.get(0);
    }



    private void playerPhaseOne() {
        // Trading, Dealing, Managing Props
    }

    private void playerPhaseTwo() {
        // Location actions, Roll Dice
    }

    private Player playerEndTurn(Player currentPlayer, List<Player> activePlayers) {
        // End players turn, makes next player active
        int currentPlayerIndex = currentPlayer.getId();
        int nextPlayerIndex = currentPlayerIndex + 1;

        while (currentPlayerIndex == currentPlayer.getId()) {
            try {
                currentPlayer = activePlayers.get(nextPlayerIndex);
            } catch (IndexOutOfBoundsException e) {
                // Reset index to 0 if next player doesn't exist, e.g. looking for P3 in a 2 player game
                nextPlayerIndex = 0;
            }
            if (!currentPlayer.getActive()) {
                nextPlayerIndex = nextPlayerIndex + 1;
            }
        }
        return currentPlayer;
    }

    private void movePlayer(Player player) {

        boolean doubleDiceRoll = false;
        int doubleDiceRollCounter = 0;

        // Wrap dice rolls in do ... while - if double rolled, re-roll, if three in a row - go to jail
        do {
            doubleDiceRoll = false;

//            int dieOne = (int) (Math.random() * 1000 % 6 + 1);
//            int dieTwo = (int) (Math.random() * 1000 % 6 + 1);

            int dieOne = 3;
            int dieTwo = 3;

            if (player.getJailed()) {
                if (inJail(player, dieOne, dieTwo)){
                    System.out.println("Looks like you're staying in the slammer for now!");
                    return;
                }
            } else {
                System.out.println("You are currently on " + player.getBoardPosition());
                System.out.println("Hit ENTER to roll!");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                System.out.println("ROLLLLLLLLING!!!!");
            }
            try {
                // Pause for effect!
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




            int diceRoll = (dieOne + dieTwo);
            System.out.println("A " + dieOne + " and a " + dieTwo + "...");
            System.out.println("YOU ROLLED " + diceRoll + "!!!!");

            if (dieOne == dieTwo) {
                System.out.println("Double Rolled, NICE! Have another go!");
                doubleDiceRoll = true;
                doubleDiceRollCounter = doubleDiceRollCounter + 1;
                if (doubleDiceRollCounter == 2) {
                    System.out.println("Wait no that's the third one, NOT NICE! Go to JAIL!");
                    // TODO : Go to jail functionality
                    player.sendPlayerToJail();
                    playerRepository.save(player);
                    return;
                }
            }

            int newPosition = player.getBoardPosition() + diceRoll;

            if (newPosition > 39) {
                // Passing go functionality, if passed 40, modulo 40 gives us the id of landed tile
                newPosition = newPosition % 40;
                player.setBankBalance(player.getBankBalance() + 200);
                System.out.println("YOU HAVE PASSED GO, COLLECT 200 Smackaroonies!");
            }
            player.setBoardPosition(newPosition);
            System.out.println("New Position is " + newPosition);
            playerRepository.save(player);
            landingOnTileActions(player, diceRoll);
        } while (doubleDiceRoll);

    }

    private boolean inJail(Player player, int dieOne, int dieTwo) {
        System.out.println("You are starting in jail!");
        System.out.println("Type 1 to roll to leave");
        System.out.println("Type 2 to pay £50 to leave early");
        if (player.getGetOutOfJailFreeCards() > 0) {
            System.out.println("Type 3 to use a get out of jail free card");
        }
        Scanner scanner = new Scanner(System.in);
        Integer jailChoice = null;
        while (jailChoice == null) {
                try {
                    jailChoice = Integer.parseInt(scanner.nextLine());
                    if (jailChoice.equals(1)) {
                        if (dieOne == dieTwo) {
                            System.out.println("Doubles! Pack your bags, lets go!");
                            player.setTurnsLeftInJail(0);
                            player.setJailed(false);
                            return false;
                        } else {
                            // Not doubles, return and end turn
                            System.out.println(dieOne + " + " + dieTwo +": Not Doubles, Stay in Jail");
                            return true;
                        }
                    } else if (jailChoice.equals(2)){
                        settleDebtsService.payJailExitFee(player);
                        player.setTurnsLeftInJail(0);
                        player.setJailed(false);
                        return false;
                    } else if (jailChoice.equals(3) & player.getGetOutOfJailFreeCards() > 0) {
                        player.setTurnsLeftInJail(0);
                        player.setJailed(false);
                        player.setGetOutOfJailFreeCards(player.getGetOutOfJailFreeCards() - 1);
                        return false;
                    } else  {
                        jailChoice = null;
                        System.out.println("Pick a Valid Number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("That ain't a number, try again:");
                }
            }
        return true;
    }

    private void landingOnTileActions(Player player, Integer diceRoll) {
        resolveLandOnTileService.resolveLandingOnTile(player, diceRoll);
    }
}
