package com.example.ISAums.repository;

import com.example.ISAums.dto.response.GetDiscountedVehicleResponse;
import com.example.ISAums.model.Discount;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.model.enumeration.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, UUID> {

    @Query(value = "SELECT * FROM discount d " +
                   "WHERE d.start_date BETWEEN :startDate AND :endDate " +
                   "OR d.end_date BETWEEN :startDate AND :endDate " +
                   "AND d.entity_id = :entityId " +
                   "AND d.entity_type = 'VEHICLE' " +
                   "GROUP BY d.entity_id", nativeQuery = true)
    Discount check(String entityId, String startDate, String endDate);

    @Query(value= "SELECT v.model AS model, v.brand AS brand, v.number_of_people AS numberOfSeats, v.year_of_production AS yearOfProduction, v.rating AS rating, " +
                         "(v.price_per_day * (:startDate - :endDate + 1) * (0.01 * (100-d.rate))) AS discounted, (v.price_per_day * (:startDate - :endDate + 1) ) AS originalPrice, " +
                         "d.rate AS discountRate " +
                  "FROM vehicle v " +
                  "JOIN vehicle_reservation vr ON vr.vehicle_id = v.id " +
                  "JOIN discount d ON v.id = d.entity_id " +
                  "WHERE d.start_date BETWEEN :startDate AND :endDate " +
                  "AND d.end_date BETWEEN :startDate AND :endDate " +
                  "AND v.rent_a_car_id = :rentACarId " +
                  "GROUP BY v.id ", nativeQuery = true)
    List<GetDiscountedVehicleResponse> findVehicles(String rentACarId, String startDate, String endDate);
}
