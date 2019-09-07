package com.example.ISAums.repository;

import com.example.ISAums.model.AgencyLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgencyLocationRepository extends JpaRepository<AgencyLocation, UUID> {

    AgencyLocation findByCityAndState(String city, String state);
}
