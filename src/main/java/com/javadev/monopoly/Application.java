package com.javadev.monopoly;

import com.javadev.monopoly.model.Player;
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

    @Autowired
    PlayGameService playGameService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public void startGame() {

        // TODO: Initial Game Options - Normal or Fast, how many players, and random/selected characters
        initialiseGameService.initialOptions();

        Player winner = playGameService.playGame();

        System.out.println(winner.getName() + " has won the game!");

    }

}