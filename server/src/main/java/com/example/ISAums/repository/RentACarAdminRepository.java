package com.example.ISAums.repository;

import com.example.ISAums.model.RentACarAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentACarAdminRepository extends JpaRepository<RentACarAdmin, UUID> {
    RentACarAdmin findByUser_Id(UUID user_id);
}
