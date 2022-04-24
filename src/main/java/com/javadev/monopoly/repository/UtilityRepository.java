package com.javadev.monopoly.repository;

import com.javadev.monopoly.model.Rail;
import com.javadev.monopoly.model.Utility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityRepository extends JpaRepository<Utility, Integer> {

}