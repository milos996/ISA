package com.example.ISAums.repository;
import com.example.ISAums.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AirplaneRepository  extends JpaRepository<Airplane, UUID> {

    @Query(value = "select * from airplane a where a.airline_id = ?1", nativeQuery = true)
    Airplane findByAirlineId(UUID startAirlineId);
}
