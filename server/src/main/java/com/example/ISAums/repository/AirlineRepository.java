package com.example.ISAums.repository;
import com.example.ISAums.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AirlineRepository extends JpaRepository<Airline, UUID> {

    @Query(value = "select * from airline a where a.id != ?2 and a.name = ?1", nativeQuery = true)
    Airline getAnotherWithThisName(String name, String id);

    @Query(value = "SELECT * FROM airline air " +
                   "ORDER BY air.name ASC", nativeQuery =  true)
    List<Airline> sortByName();

    @Query(value = "SELECT * FROM airline air " +
                   "ORDER BY air.hand_luggage_price ASC", nativeQuery =  true)
    List<Airline> sortByHandLuggagePrice();

    @Query(value = "SELECT * FROM airline air " +
                   "ORDER BY air.checking_in_suitcase_price ASC", nativeQuery =  true)
    List<Airline> sortBySuitcasePrice();

    @Query(value = "SELECT * FROM airline air " +
                   "ORDER BY air.rating DESC", nativeQuery =  true)
    List<Airline> sortByRating();

    @Query(value = "SELECT * FROM airline air " +
                   "JOIN address a ON air.address_id = a.id " +
                   "ORDER BY a.city ASC", nativeQuery =  true)
    List<Airline> sortByAddress();
}
