package com.example.ISAums.repository;

import com.example.ISAums.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    boolean existsByName(String name);

    @Query(value = "select * " +
            "from hotel h left join address a on h.address_id =  a.id " +
            "where  lower(h.name) like CONCAT('%', LOWER(:name) , '%') " +
            "and lower(a.city) like CONCAT('%',  LOWER(:city), '%')")
    List<Hotel> findAllByNameOrLocation(String name, String city);
}
