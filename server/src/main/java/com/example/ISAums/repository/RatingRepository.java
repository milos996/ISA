package com.example.ISAums.repository;

import com.example.ISAums.model.Rating;
import com.example.ISAums.model.enumeration.RatingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

    @Query(value = "select r.mark from rating r where r.entity_id = ?1 and r.entity_type = ?2", nativeQuery = true)
    List<Integer> getMarksByEntityId(String entityId, String entityType);

    @Query(value = "SELECT AVG(mark) FROM rating r " +
                   "WHERE r.entity_id = :entityId and r.entity_type = :type", nativeQuery = true)
    Double getAverageMarkForEntity(String entityId, String type);

    @Query(value = "SELECT * FROM rating r " +
                   "WHERE r.entity_id = :entityId " +
                   "AND r.user_id = :userId " +
                   "AND r.entity_type = :type ", nativeQuery = true)
    Rating checkIfUserAlreadyRateEntity(String userId, String entityId, String type);

}
