package com.example.ISAums.repository;

import com.example.ISAums.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Address findByCity(String city);

    Address findByLongitudeAndLatitude(Double longitude, Double latitude);

    Address findByStreetAndCityAndState(String street, String city, String street1);
}
