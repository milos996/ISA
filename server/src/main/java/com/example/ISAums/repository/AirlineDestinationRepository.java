package com.example.ISAums.repository;
import com.example.ISAums.model.AirlineDestination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AirlineDestinationRepository  extends JpaRepository<AirlineDestination, UUID> {
}
