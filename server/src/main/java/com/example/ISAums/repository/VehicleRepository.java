package com.example.ISAums.repository;

import com.example.ISAums.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
                   "HAVING COUNT(DISTINCT al.city) = :cityCount " +
                   "AND v.rent_a_car_id = :rentACarId", nativeQuery = true)
    List<Vehicle> search(String rentACarId, String pickUpDate, String dropOffDate, String pickUpLocation, String dropOffLocation, String type, int seats, double startRange, double endRange, int cityCount);

    @Query(value = "SELECT * FROM vehicle v " +
                   "JOIN discount d ON v.id = d.entity_id " +
                   "WHERE d.start_date BETWEEN :startDate AND :endDate " +
                   "AND d.end_date BETWEEN :startDate AND :endDate " +
                   "AND d.entity_type = 'VEHICLE' " +
                   "AND v.rent_a_car_id = :rentACarId " +
                   "GROUP BY d.entity_id", nativeQuery = true)
    List<Vehicle> findVehiclesOnDiscount(String startDate, String endDate, String rentACarId);

    @Query(value = "SELECT DISTINCT * " +
                   "FROM vehicle v " +
                   "WHERE v.id NOT IN " +
                   "(SELECT vr.vehicle_id FROM vehicle_reservation AS vr " +
                   "WHERE (vr.start_date <= :dropOffDate AND vr.end_date >= :pickUpDate) " +
                   "OR (vr.start_date >= :dropOffDate AND vr.end_date <= :pickUpDate)) " +
                   "AND v.id = :vid", nativeQuery = true)
    List<Vehicle> checkAvailability(String vid, String pickUpDate, String dropOffDate);

    @Query(value = "SELECT * FROM vehicle v " +
                   "WHERE v.id NOT IN " +
                   "(SELECT d.entity_id FROM discount as d " +
                   "WHERE d.start_date BETWEEN :currentDate AND :currentDate " +
                   "OR d.end_date BETWEEN :currentDate AND :currentDate " +
                   "AND d.entity_type = 'VEHICLE'" +
                   "GROUP BY d.entity_id) " +
                   "AND v.rent_a_car_id = :rentACarId", nativeQuery = true)
    List<Vehicle> findRentACarVehicles(String rentACarId, String currentDate);

    @Query(value = "SELECT * FROM vehicle v WHERE v.rent_a_car_id = :rentACarId " +
                   "ORDER BY v.brand  ASC ", nativeQuery =  true)
    List<Vehicle> sortByBrand(String rentACarId);

    @Query(value = "SELECT * FROM vehicle v WHERE v.rent_a_car_id = :rentACarId " +
                   "ORDER BY v.model ASC ", nativeQuery =  true)
    List<Vehicle> sortByModel(String rentACarId);

    @Query(value = "SELECT * FROM vehicle v WHERE v.rent_a_car_id = :rentACarId " +
                   "ORDER BY v.year_of_production ASC ", nativeQuery =  true)
    List<Vehicle> sortByYearOfProduction(String rentACarId);

    @Query(value = "SELECT * FROM vehicle v WHERE v.rent_a_car_id = :rentACarId " +
                   "ORDER BY v.rating DESC", nativeQuery =  true)
    List<Vehicle> sortByRating(String rentACarId);

    @Query(value = "SELECT DISTINCT * " +
                   "FROM vehicle v " +
                   "WHERE v.id NOT IN " +
                   "(SELECT vr.vehicle_id FROM vehicle_reservation AS vr " +
                   "WHERE (vr.start_date <= :endDate AND vr.end_date >= :startDate) " +
                   "OR (vr.start_date >= :endDate AND vr.end_date <= :startDate)) " +
                   "AND v.rent_a_car_id = :rentACarId", nativeQuery = true)
    List<Vehicle> findAllAvailable(String rentACarId, String startDate, String endDate);

    @Query(value = "SELECT DISTINCT * " +
                   "FROM vehicle v " +
                   "WHERE v.id IN " +
                   "(SELECT vr.vehicle_id FROM vehicle_reservation AS vr " +
                   "WHERE (vr.start_date <= :endDate AND vr.end_date >= :startDate) " +
                   "OR (vr.start_date >= :endDate AND vr.end_date <= :startDate)) " +
                   "AND v.rent_a_car_id = :rentACarId", nativeQuery = true)
    List<Vehicle> findAllUnavailable(String rentACarId, String startDate, String endDate);
}
