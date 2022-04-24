package com.javadev.monopoly.repository;

import com.javadev.monopoly.model.Rail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RailRepository extends JpaRepository<Rail, Integer> {

}