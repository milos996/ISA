package com.example.ISAums.repository;

import com.example.ISAums.model.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelServiceRepository extends JpaRepository<HotelService, UUID> {
    List<HotelService> findAllByHotelId(UUID id);
}
