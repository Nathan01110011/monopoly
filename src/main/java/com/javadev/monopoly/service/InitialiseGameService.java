package com.javadev.monopoly.service;

import com.javadev.monopoly.model.BoardTile;
import com.javadev.monopoly.model.Player;
import com.javadev.monopoly.repository.BoardTileRepository;
import com.javadev.monopoly.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class InitialiseGameService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BoardTileRepository boardTileRepository;

    @Autowired
    private InsertDataService insertDataService;

    public void initialOptions() {
        try {
            insertDataService.insertInitialData();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        System.out.println("*********************************************");
        System.out.println("***  How many players are playing today?  ***");
        System.out.println("*********************************************\n");

        Scanner scanner = new Scanner(System.in);

        Integer numberOfPlayers = null;
        while (numberOfPlayers == null) {
            if (scanner.hasNext()) {
                try {
                    numberOfPlayers = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("That ain't a number, try again:");
                    continue;
                }
            }
            if (numberOfPlayers < 2) {
                System.out.println("Get a load of Billy no mates here - You need to find at least two people to play:");
                numberOfPlayers = null;
            } else if (numberOfPlayers > 5) {
                System.out.println("Sorry Mr/Miss/GenderNeutralPronoun Popular, five people MAX!");
                numberOfPlayers = null;
            }
        }
        System.out.println("Number of players =  " + numberOfPlayers);

        for (int i = 0; i <= numberOfPlayers - 1; i++) {
            // Zero index in backend, human index in console
            System.out.println("What is player " + (i + 1) + " called?");

                String name = scanner.nextLine();

            playerRepository.save(new Player(i, name));
            System.out.println("Hello " + name + "!");
        }

        List<Player> playerList = playerRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder();

        for (Player p: playerList) {
            stringBuilder.append(p.getName() + ", ");
        }
        System.out.println(stringBuilder + "let's play Monopoly!!!\n");
        
        
        System.out.println("*******************************************************");
        System.out.println("***  Type the number of the game you wish to play.  ***");
        System.out.println("*******************************************************\n");

        System.out.println("1) Normal: You must land on a property to buy it.");
        System.out.println("2) Auction Mode: If someone passes on a property, it goes to auction.");
        System.out.println("3) Fast Mode: Properties are chucked out at random at the start of the game.\n");

        Integer gameMode = null;
        while (gameMode == null) {
            if (scanner.hasNext()) {
                try {
                    gameMode = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("That ain't a number, try again:");
                    continue;
                }
            }
            if (gameMode < 1 | gameMode > 3) {
                System.out.println("Please read possible game types (1, 2 or 3).");
                gameMode = null;
            }
        }
        // TODO: Persist Game Type In DB
    }


}
