package com.javadev.monopoly.service;

import com.javadev.monopoly.model.BoardTile;
import com.javadev.monopoly.model.Property;
import com.javadev.monopoly.repository.BoardTileRepository;
import com.javadev.monopoly.repository.PropertyRepository;
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
import java.util.List;

@Service
public class InsertDataService {

    @Autowired
    private BoardTileRepository boardTileRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public void insertInitialData() {

        // Adding Tile Info
        List<BoardTile> tileList = readBoardTilesFromCSV("tile_info.csv");
        boardTileRepository.saveAll(tileList);

        // Adding Properties
        List<Property> propertyList = readPropertiesFromCSV("property_info.csv");
        propertyRepository.saveAll(propertyList);
        // TODO: Add Property and Other Items to DB

    }

    // Adapted from https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
    private static List<BoardTile> readBoardTilesFromCSV(String fileName) {
        List<BoardTile> boardTiles = new ArrayList<>();
//        Path pathToFile = Paths.get(fileName);
        Path pathToFile = null;
        try {
            pathToFile = Paths.get(InitialiseGameService.class.getResource("/"+fileName).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            String line = br.readLine();
            while (line != null) {

                String[] attributes = line.split(",");
                BoardTile book = createBoardTile(attributes);
                boardTiles.add(book);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return boardTiles;
    }

    private static BoardTile createBoardTile(String[] metadata) {
        int boardTileId = Integer.parseInt(metadata[0]);
        int boardTileTypeId = Integer.parseInt(metadata[1]);

        return new BoardTile(boardTileId, boardTileTypeId);
    }

    // Adapted from https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
    private static List<Property> readPropertiesFromCSV(String fileName) {
        List<Property> boardTiles = new ArrayList<>();
//        Path pathToFile = Paths.get(fileName);
        Path pathToFile = null;
        try {
            pathToFile = Paths.get(InitialiseGameService.class.getResource("/"+fileName).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            String line = br.readLine();
            while (line != null) {

                String[] attributes = line.split(",");
                Property book = createProperty(attributes);
                boardTiles.add(book);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return boardTiles;
    }

    private static Property createProperty(String[] metadata) {
        int id = Integer.parseInt(metadata[0]);
        String name = metadata[1];
        int price = Integer.parseInt(metadata[2]);
        String colourGroup = metadata[3];
        int rent = Integer.parseInt(metadata[4]);
        int oneHouse = Integer.parseInt(metadata[5]);
        int twoHouse = Integer.parseInt(metadata[6]);
        int threeHouse = Integer.parseInt(metadata[7]);
        int fourHouse = Integer.parseInt(metadata[8]);
        int oneHotel = Integer.parseInt(metadata[9]);

        return new Property(id, name, price, colourGroup, rent, oneHouse, twoHouse, threeHouse, fourHouse, oneHotel);
    }


}
