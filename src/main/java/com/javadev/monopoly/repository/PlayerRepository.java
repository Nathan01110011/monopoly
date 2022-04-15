package com.javadev.monopoly.repository;

import com.javadev.monopoly.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    public List<Player> findByActiveTrue();
}