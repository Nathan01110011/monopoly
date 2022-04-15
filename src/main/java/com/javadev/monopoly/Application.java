package com.javadev.monopoly;

import com.javadev.monopoly.repository.PlayerRepository;
import com.javadev.monopoly.service.GameSetupService;
import com.javadev.monopoly.service.InitialiseGameService;
import com.javadev.monopoly.service.PlayGameService;
import com.javadev.monopoly.service.PreTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

    @Autowired
    InitialiseGameService initialiseGameService;

    GameSetupService gameSetupService = new GameSetupService();
    PreTurnService preTurnService = new PreTurnService();

    @Autowired
    PlayGameService playGameService;

    @Autowired
    PlayerRepository playerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public void startGame() {

        // TODO: Initial Game Options - Normal or Fast, how many players, and random/selected characters
        initialiseGameService.initialOptions();


        // TODO: Game Setup - Initialising players with money and character tokens
//        gameSetup();


        // TODO: Game Phase 1 - Trading, Dealing and Managing Properties
//        turnPhaseOne();
        playGameService.playGame();



        // TODO: Game Phase 2 - Rolling and Moving
        turnPhaseTwo();

    }




    private void gameSetup() {
        System.out.println("*********Starting Game Setup*********");
    }

    private void turnPhaseOne() {
        System.out.println("Do you wish to conduct any business this turn?");
        System.out.println("1) Yes");
        System.out.println("2) No");
    }

    private void turnPhaseTwo() {
        System.out.println("Hit Enter To Rollllllllll!");
    }

}