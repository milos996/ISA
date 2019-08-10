package com.example.ISAums.repository;
import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AirlineDestinationRepository  extends JpaRepository<AirlineDestination, UUID> {


    @Query(value = "select d.id from destination d left join airline_destination ad" +
            " on d.id = ad.destination_id where ad.airline_id = ?1", nativeQuery = true)
    List<UUID> getDestinationsByAirlineId(String airlineId);


    @Query(value = "select ad.airline_id from airline_destination ad where ad.destination_id = ?1", nativeQuery = true)
    UUID findAirlineByDestinationId(String destinationId);

    @Query(value = "select * from airline_destination ad where ad.destination_id = ?1", nativeQuery = true)
    AirlineDestination findByDestinationId(String destinationId);
}
