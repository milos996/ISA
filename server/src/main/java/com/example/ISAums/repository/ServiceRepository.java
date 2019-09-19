package com.example.ISAums.repository;

import com.example.ISAums.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
    boolean existsByName(String name);

    @Query("Select distinct s " +
            "from Service s left join HotelService hs on s.id = hs.service.id " +
            "where hs.hotel.id is null or hs.hotel.id <> :hotelId")
    List<Service> findAllWhereHotelServiceDontHave(UUID hotelId);
}
