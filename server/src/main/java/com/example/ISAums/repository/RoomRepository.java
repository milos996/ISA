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

    @Query("SELECT DISTINCT r " +
            "FROM  Room r  " +
            "left join HotelReservation res on r.id = res.room.id " +
            "WHERE r.hotel.id = :hotelId " +
            "AND (:people = 0 or r.numberOfPeople = :people) " +
            "AND (:fromPrice = 0.0 or r.priceSummer >= :fromPrice) " +
            "AND (:toPrice = 0.0 or r.priceSummer <= :toPrice) ")
    List<Room> getRooms(UUID hotelId, Integer people, Double fromPrice, Double toPrice);
}
//    @Query("SELECT DISTINCT r " +
//            "FROM  Room r  " +
//            "left join HotelReservation res on r.id = res.room.id " +
//            "WHERE r.hotel.id = :hotelId " +
//            "AND (:startDate is null or :endDate is null or ((res.startDate not between :startDate and :endDate) and ( res.endDate not between :startDate and :endDate))) " +
//            "AND (:people is null or r.numberOfPeople = :people) " +
//            "AND (:fromPrice is null or r.priceSummer >= :fromPrice) " +
//            "AND (:toPrice is null or r.priceSummer <= :toPrice) ")
//    List<Room> getRooms(UUID hotelId, LocalDate startDate, LocalDate endDate, Integer people, Double fromPrice, Double toPrice);
