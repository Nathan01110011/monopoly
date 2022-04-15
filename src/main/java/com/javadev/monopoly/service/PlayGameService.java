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

    // Game runs when there are more than one active player - returns winner when only one remains
    public Player playGame() {
        System.out.println("STARTING GAME!");
        List<Player> activePlayers = playerRepository.findByActiveTrue();
        // Player Id 1 goes first, then rotates incrementally
        Player currentPlayer = playerRepository.findById(0).orElse(new Player(0, "User"));

        while (activePlayers.size() > 1) {

            // Refresh active players
            activePlayers = playerRepository.findByActiveTrue();

            System.out.println("\n" + currentPlayer.getName() + " is up!");
            movePlayer(currentPlayer);
            playerPhaseOne();
            playerPhaseTwo();
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
            if (currentPlayer.getActive() == false) {
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
            System.out.println("You are starting on " + player.getBoardPosition());
            System.out.println("Hit ENTER to roll!");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            System.out.println("ROLLLLLLLLING!!!!");

            try {
                // Pause for effect!
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Double dieOne = Math.random() * 1000 % 6 + 1;
            Double dieTwo = Math.random() * 1000 % 6 + 1;

            if (dieOne == dieTwo) {
                System.out.println("Double Rolled, NICE!");
                doubleDiceRoll = true;
                doubleDiceRollCounter = doubleDiceRollCounter + 1;
                if (doubleDiceRollCounter == 3) {
                    System.out.println("Wait no that's the third one,NOT NICE! Go to JAIL!");
                    // TODO : Go to jail functionality
                }
            }

            int diceRoll = (int) (dieOne + dieTwo);
            System.out.println("YOU ROLLED " + diceRoll + "!!!!");
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
            landingOnTileActions();
        } while (doubleDiceRoll);

    }


    private void landingOnTileActions() {
    }
}
