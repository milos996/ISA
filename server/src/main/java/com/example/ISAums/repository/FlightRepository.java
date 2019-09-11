package com.example.ISAums.repository;
import com.example.ISAums.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {

    @Query(value = "select * from flight where flight.airline_destination_id = ?1", nativeQuery = true)
    List<Flight> getFlightsForDestination(String airlineDestinationId);

    @Query(value = "select f.id from isa_database.flight f left join isa_database.airplane airp on f.airplane_id = airp.id " +
            "join isa_database.airline airl on airp.airline_id = airl.id join isa_database.address a on airl.address_id = a.id " +
            "join isa_database.airline_destination ad on f.airline_destination_id = ad.id " +
            "join isa_database.destination d on ad.destination_id = d.id where a.city=?1 and d.city=?2", nativeQuery = true)
    List<UUID> search(String fromDestinationCity, String toDestinationCity);

    @Query(value = "select f.id from flight f left join airplane a" +
            " on f.airplane_id = a.id where f.departure_time = curdate()+3 and a.airline_id = ?1", nativeQuery = true)
    List<UUID> getQuickBookingFlights(String airlineId);

    @Query(value = "select f.airline_destination_id from flight f where f.id = ?1", nativeQuery = true)
    UUID getFlightDestination(UUID flightId);

    @Query(value = "select f.id from isa_database.flight f left join isa_database.airplane a on f.airplane_id = a.id where a.airline_id = ?1", nativeQuery = true)
    List<UUID> getFlightsByAirlineId(String airlineId);

    @Query(value = "select f.airline_destination_id from isa_database.flight f join isa_database.airplane a on f.airplane_id = a.id where a.airline_id = ?1", nativeQuery = true)
    List<UUID> getAirlineDestinations(String airlineId);

}
