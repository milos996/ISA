package com.example.ISAums.repository;
import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {

    @Query(value = "select * from flight where flight.airline_destination_id = ?1", nativeQuery = true)
    List<Flight> getFlightsForDestination(String airlineDestinationId);

    @Query(value = "select f.id from flight f left join airplane a on f.airplane_id = a.id where a.airline_id = ?1 and f.airline_destination_id = ?2 and f.departure_time = ?3 and f.arrival_time = ?4", nativeQuery = true)
    List<UUID> search(String departureAirportID, String destinationAirportID, LocalDate departureTime, LocalDate arrivalTime);

    @Query(value = "select f.id from flight f left join airplane a" +
            " on f.airplane_id = a.id where curdate()+3 = f.departure_time and a.airline_id = ?1", nativeQuery = true)
    List<UUID> getQuickBookingFlights(UUID airlineId);

    @Query(value = "select f.airline_destination_id from flight f where f.id = 1?", nativeQuery = true)
    UUID getFlightDestination(UUID flightId);

    @Query(value = "select f.id from flight f left join airplane a on f.airplane_id = a.id where a.airline_id = ?1", nativeQuery = true)
    List<UUID> getFlightsByAirlineId(UUID airlineId);

    @Query(value = "select f.airline_destination_id from flight f left join airplane a on f.airplane_id = a.id where a.airline_id = ?1", nativeQuery = true)
    List<UUID> getAirlineDestinations(UUID airlineId);
}
