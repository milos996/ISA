package com.example.ISAums.repository;

import com.example.ISAums.model.Address;
import com.example.ISAums.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@Repository
public interface RentACarRepository extends JpaRepository<RentACar, UUID> {
    RentACar findByName(String rentACarName);

    boolean existsByName(String name);

    RentACar findByAddress_Id(UUID address_id);

    @Query(value = "SELECT * FROM rent_a_car rac " +
                   "LEFT JOIN rent_a_car_location racl ON racl.rent_a_car_id = rac.id " +
                   "LEFT JOIN agency_location al ON racl.agency_location_id = al.id " +
                   "LEFT JOIN vehicle v ON rac.id = v.id " +
                   "WHERE v.id NOT IN " +
                   "(SELECT vr.vehicle_id FROM vehicle_reservation AS vr " +
                   "WHERE (vr.start_date <= :dropOffDate AND vr.end_date >= :pickUpDate) " +
                   "OR (vr.start_date >= :dropOffDate AND vr.end_date <= :pickUpDate )) " +
                   "AND rac.name = :name " +
                   "AND al.city = :city AND al.state = :state ", nativeQuery = true)
    List<RentACar> search(String city,String state,String name,String pickUpDate,String dropOffDate);
}