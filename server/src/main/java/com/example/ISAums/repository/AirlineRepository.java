package com.example.ISAums.repository;
import com.example.ISAums.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AirlineRepository extends JpaRepository<Airline, UUID> {
}
