package com.example.ISAums.repository;

import com.example.ISAums.model.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelReservationRepository extends  JpaRepository<HotelReservation, UUID> {


    @Query(value = "SELECT * FROM hotel_reservation hr left join room r on hr.room_id = r.id WHERE r.hotel_id = ?3 and hr.end_date between ?1 and ?2", nativeQuery = true)
    List<HotelReservation> findAllWhereDateBetweenStartAndEndDate(Date startDate, Date endDate, String id);


    // TODO
//    @Query("SELECT " +
//            "FROM " +
//            "WHERE ")
//    List<Object> groupByDate(Date start,Date end);

    @Query(value = "SELECT CASE WHEN COUNT(hr) > 0 THEN true ELSE false END  from hotel_reservation hr where hr.room_id = ?1 and hr.end_date > CURDATE()")
    boolean existsByRoomWhereEndDateIsAfterToday(String roomId);
}
