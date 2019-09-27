package com.example.ISAums.repository;

import com.example.ISAums.model.Address;
import com.example.ISAums.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.Null;
import java.util.Date;
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
                   "WHERE ((:pickUpDate is null AND :dropOffDate is null) OR vr.start_date <= :dropOffDate AND vr.end_date >= :pickUpDate) " +
                   "OR ((:pickUpDate is null AND :dropOffDate is null) OR vr.start_date >= :dropOffDate AND vr.end_date <= :pickUpDate )) " +
                   "AND (:name is null OR rac.name LIKE :name) " +
                   "AND (:city is null OR al.city LIKE :city) " +
                   "AND (:state is null OR al.state LIKE :state) " +
                   "GROUP BY rac.id", nativeQuery = true)
    List<RentACar> search(String city, String state, String name, String pickUpDate, String dropOffDate);

    @Query(value = "SELECT * FROM rent_a_car rac " +
                   "ORDER BY rac.name ASC", nativeQuery =  true)
    List<RentACar> sortByName();

    @Query(value = "SELECT * FROM rent_a_car rac " +
                   "JOIN address a ON rac.address_id = a.id " +
                   "ORDER BY a.city ASC", nativeQuery =  true)
    List<RentACar> sortByAddress();

    @Query(value = "SELECT * FROM rent_a_car rac " +
                   "ORDER BY rac.rating DESC", nativeQuery =  true)
    List<RentACar> sortByRating();
}
