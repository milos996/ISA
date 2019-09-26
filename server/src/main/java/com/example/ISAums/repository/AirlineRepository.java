package com.example.ISAums.repository;
import com.example.ISAums.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AirlineRepository extends JpaRepository<Airline, UUID> {

    @Query(value = "select * from airline a where a.id != ?2 and a.name = ?1", nativeQuery = true)
    Airline getAnotherWithThisName(String name, String id);
}
