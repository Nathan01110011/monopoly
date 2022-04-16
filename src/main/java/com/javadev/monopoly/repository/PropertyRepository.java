package com.javadev.monopoly.repository;

import com.javadev.monopoly.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    List<Property>findAllByColourGroup(String colourGroup);

    List<Property>findAllByOwnerId(Integer ownerId);

}