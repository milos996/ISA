package com.example.ISAums.repository;
import com.example.ISAums.model.AirlineDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface AirlineDestinationRepository  extends JpaRepository<AirlineDestination, UUID> {

    @Query(value = "select d.id from destination d left join airline_destination ad" +
            " on d.id = ad.destination_id where ad.airline_id = ?1", nativeQuery = true)
    List<UUID> getDestinationsByAirlineId(String airlineId);

    @Query(value = "delete from airline_destination ad where ad.destination_id = ?1 and ad.airline_id = ?2", nativeQuery = true)
    void remove(UUID destinationId, UUID airlineId);
}
