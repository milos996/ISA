package com.example.ISAums.repository;

import com.example.ISAums.dto.database.DBHotel;
import com.example.ISAums.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    boolean existsByName(String name);

    @Query("SELECT DISTINCT h " +
            "FROM Hotel h left join Room r on h.id = r.hotel.id " +
            "LEFT JOIN HotelReservation res on r.id = res.room.id " +
            "LEFT JOIN Address a on a.id = h.address.id " +
            "WHERE (:startDate is null or :endDate is null or ((res.startDate not between :startDate and :endDate) and ( res.endDate not between :startDate and :endDate))) " +
            "AND (:name = 'null' or h.name = :name) " +
            "AND (:city = 'null' or a.city = :city) " +
            "AND (:state = 'null' or a.state = :state)")
    List<Hotel> findAllByFilters( LocalDate startDate, LocalDate endDate, String name, String city, String state);

    Hotel findByName(String name);
}
//    or not ((res.startDate between (:startDate) and (:endDate)) or (res.endDate between (:startDate) and (:endDate)) )