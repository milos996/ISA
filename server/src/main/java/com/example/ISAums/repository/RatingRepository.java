package com.example.ISAums.repository;

import com.example.ISAums.model.Rating;
import com.example.ISAums.model.enumeration.RatingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

    @Query(value = "select r.mark from rating r where r.entity_id = ?1 and r.entity_type = ?2", nativeQuery = true)
    List<Integer> getMarksByEntityId(String entityId, RatingType entityType);
}
