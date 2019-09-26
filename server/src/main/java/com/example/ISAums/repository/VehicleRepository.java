package com.example.ISAums.repository;

import com.example.ISAums.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.Nullable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    @Query(value = "SELECT * FROM vehicle v " +
                   "LEFT JOIN rent_a_car_location racl on racl.rent_a_car_id = v.rent_a_car_id " +
                   "LEFT JOIN agency_location al on racl.agency_location_id = al.id " +
                   "WHERE v.id NOT IN " +
                   "(SELECT vr.vehicle_id FROM vehicle_reservation AS vr " +
                   "WHERE ((:pickUpDate is null and :dropOffDate is null) or vr.start_date <= :dropOffDate AND vr.end_date >= :pickUpDate) " +
                   "OR ((:pickUpDate is null and :dropOffDate is null) or vr.start_date >= :dropOffDate AND vr.end_date <= :pickUpDate)) " +
                   "AND (:type is null OR v.type LIKE %:type%) " +
                   "AND (:seats is null OR v.number_of_people <= :seats) " +
                   "AND ((:startRange is null and :endRange is null) OR v.price_per_day between :startRange and :endRange) " +
                   "AND al.city IN (:pickUpLocation, :dropOffLocation) " +
                   "GROUP BY v.id " +
                   "HAVING COUNT(DISTINCT al.city) = :cityCount", nativeQuery = true)
    List<Vehicle> search(String pickUpDate, String dropOffDate, String pickUpLocation, String dropOffLocation, String type, int seats, double startRange, double endRange, int cityCount);

    @Query(value = "SELECT DISTINCT * " +
                   "FROM vehicle v " +
                   "WHERE v.id NOT IN " +
                   "(SELECT vr.vehicle_id FROM vehicle_reservation AS vr " +
                   "WHERE (vr.start_date <= ?2 AND vr.end_date >=  ?1) " +
                   "OR (vr.start_date >= ?2 AND vr.end_date <=  ?1))", nativeQuery = true)
    List<Vehicle> checkAvailability(Date pickUpDate, Date dropOffDate);

    List<Vehicle> findByRentACar_Id(UUID rentUuid);

}
