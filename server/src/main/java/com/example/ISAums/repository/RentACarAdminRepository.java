package com.example.ISAums.repository;

import com.example.ISAums.model.RentACarAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentACarAdminRepository extends JpaRepository<RentACarAdmin, UUID> {

    RentACarAdmin findByUserId(UUID uuid);

}
