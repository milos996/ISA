package com.example.ISAums.repository;

import com.example.ISAums.model.AgencyLocation;
import com.example.ISAums.model.RentACar;
import com.example.ISAums.model.RentACarLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RentACarLocationRepository extends JpaRepository<RentACarLocation, UUID> {
    RentACarLocation findByRentACar_IdAndAgencyLocation_Id(UUID rentACarId, UUID agencyLocationId);

    List<RentACarLocation> findByRentACar_Id(UUID rentACarId);

    @Query(value = "SELECT * FROM rent_a_car_location racl " +
                   "JOIN agency_location al ON al.id = racl.agency_location_id " +
                   "WHERE racl.rent_a_car_id = ?1 " +
                   "AND al.city = ?2 AND al.state = ?3;", nativeQuery = true)
    List<RentACarLocation> checkLocation(UUID rentACarId, String city, String state);

    @Query(value = "SELECT * FROM rent_a_car_location racl " +
                   "LEFT JOIN rent_a_car rac ON rac.id = racl.rent_a_car_id " +
                   "LEFT JOIN address a ON a.id = rac.address_id " +
                   "LEFT JOIN vehicle v ON v.rent_a_car_id " +
                   "WHERE v.id NOT IN " +
                   "(SELECT vr.vehicle_id FROM vehicle_reservation AS vr " +
                   "WHERE ((:pickUpDate is null AND :dropOffDate is null ) OR vr.start_date <= :dropOffDate AND vr.end_date >= :pickUpDate) " +
                   "OR ((:pickUpDate is null AND :dropOffDate is null ) OR vr.start_date >= :dropOffDate AND vr.end_date <= :pickUpDate)) " +
                   "AND (:name is null OR rac.name = :name) " +
                   "AND (:city is null OR a.city = :city) " +
                   "AND (:state is null OR a.state = :state) " +
                   "GROUP BY racl.id", nativeQuery = true)
    List<RentACarLocation> search(String city, String state, String name, String pickUpDate, String dropOffDate);
}
