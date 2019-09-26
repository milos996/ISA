package com.example.ISAums.repository;

import com.example.ISAums.model.AirlineAdmin;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface AirlineAdminRepository  extends JpaRepository<AirlineAdmin, UUID> {
    AirlineAdmin findByUser_Id(UUID user_id);
}
