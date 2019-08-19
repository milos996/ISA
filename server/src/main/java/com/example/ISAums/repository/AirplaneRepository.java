package com.example.ISAums.repository;

import com.example.ISAums.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AirplaneRepository extends JpaRepository<Airplane, UUID> {

    List<Airplane> findAllByAirlineId(UUID airlineId);
}
