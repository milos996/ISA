package com.example.ISAums.repository;

import com.example.ISAums.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    boolean existsByFloorAndNumberAndHotelId(Integer floor, Integer number, UUID id);
}
