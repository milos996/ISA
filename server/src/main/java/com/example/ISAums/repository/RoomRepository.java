package com.example.ISAums.repository;

import com.example.ISAums.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    boolean existsByFloorAndNumberAndHotelId(Integer floor, Integer number, UUID id);

    @Query("SELECT new com.example.ISAums.dto.database.DBRoom(r.id, r.number, r.floor, r.priceSummer, r.priceWinter, r.priceSpring, r.priceAutumn, r.numberOfPeople) " +
            "FROM  Room r  " +
            "left join HotelReservation res on r.id = res.room.id " +
            "WHERE r.hotel.id = ?1 " +
            "AND ((?2 is null or ?3 is null) or not ((res.startDate between (?2) and (?3)) or (res.endDate between (?2) and (?3)) )) " +
            "AND (?4 is null or r.numberOfPeople = ?4) " +
            "AND (?5 is null or r.priceSummer >= ?5) " +
            "AND (?6 is null or r.priceSummer <= ?6) ")
    List<Room> getRooms(UUID hotelId, LocalDate startDate, LocalDate endDate, Integer people, Double fromPrice, Double toPrice);
}
