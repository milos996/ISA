package com.example.ISAums.repository;

import com.example.ISAums.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    boolean existsByName(String name);

    @Query("SELECT new com.example.ISAums.dto.database.DBHotel(h.id, h.name, h.description, h.rating, a) " +
            "FROM Hotel h left join Room r on h.id = r.hotel.id " +
            "LEFT JOIN HotelReservation res on r.id = res.room.id " +
            "LEFT JOIN Address a on a.id = h.address.id " +
            "WHERE ((?1 is null or ?2 is null) or not ((res.startDate between (?1) and (?2)) or (res.endDate between (?1) and (?2)) )) " +
            "AND (?3 is null or h.name = ?3) " +
            "AND (?4 is null or a.city = ?4) " +
            "AND (?5 is null or a.state = ?5) ")
    List<Hotel> findAllByFilters(LocalDate startDate, LocalDate endDate, String name, String city, String state);
}
