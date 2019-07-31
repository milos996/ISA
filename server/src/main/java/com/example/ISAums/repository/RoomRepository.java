package com.example.ISAums.repository;

import com.example.ISAums.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    boolean existsByFloorAndNumberAndHotelId(Integer floor, Integer number, UUID id);

    @Nullable
    @Query(value = "select * " +
            "from room r left join reservation res on r.id = res.room_id " +
            "where r.number_of_people = :numberOfPeople and r.price_winter between :startPrice and :endPrice " +
            "and res.end_date not between :startDate and :endDate " +
            "and r.hotel_id = :hotelId", nativeQuery = true)
    List<Room> findAllInWinter(@Nullable  Double startPrice,@Nullable Double endPrice, Date endDate, Date startDate, UUID hotelId, Integer numberOfPeople);

    @Nullable
    @Query(value = "select  * " +
            "from room r left join reservation res on r.id = res.room_id " +
            "where r.number_of_people = :numberOfPeople and r.price_spring between :startPrice and :endPrice " +
            "and res.end_date not between :startDate and :endDate " +
            "and r.hotel_id = :hotelId", nativeQuery = true)
    List<Room> findAllInSpring(@Nullable Double startPrice, @Nullable Double endPrice, Date endDate, Date startDate, UUID hotelId, Integer numberOfPeople);

    @Nullable
    @Query(value = "select * " +
            "from room r left join reservation res on r.id = res.room_id " +
            "where r.number_of_people = :numberOfPeople and r.price_summer between :startPrice and :endPrice " +
            "and res.end_date not between :startDate and :endDate " +
            "and r.hotel_id = :hotelId", nativeQuery = true)
    List<Room> findAllInSummer(@Nullable Double startPrice, @Nullable Double endPrice, Date endDate, Date startDate, UUID hotelId, Integer numberOfPeople);

    @Nullable
    @Query(value = "select * " +
            "from room r left join reservation res on r.id = res.room_id " +
            "where r.number_of_people = :numberOfPeople and r.price_autumn between :startPrice and :endPrice " +
            "and res.end_date not between :startDate and :endDate " +
            "and r.hotel_id = :hotelId", nativeQuery = true)
    List<Room> findAllInAutumn(@Nullable Double startPrice, @Nullable Double endPrice, Date endDate, Date startDate, UUID hotelId, Integer numberOfPeople);
}
