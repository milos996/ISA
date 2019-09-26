package com.example.ISAums.repository;

import com.example.ISAums.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DestinationRepository extends JpaRepository<Destination, UUID> {
}
