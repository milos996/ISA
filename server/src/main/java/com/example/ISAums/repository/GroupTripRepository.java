package com.example.ISAums.repository;

import com.example.ISAums.model.GroupTrip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupTripRepository extends JpaRepository<GroupTrip, UUID> {
}
