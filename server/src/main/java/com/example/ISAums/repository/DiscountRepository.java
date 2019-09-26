package com.example.ISAums.repository;

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
}
