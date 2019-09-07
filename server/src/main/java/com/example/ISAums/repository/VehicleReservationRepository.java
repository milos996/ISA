package com.example.ISAums.repository;

import com.example.ISAums.model.VehicleReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface VehicleReservationRepository extends JpaRepository<VehicleReservation, UUID> {

}
