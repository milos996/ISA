package com.example.ISAums.repository;

import com.example.ISAums.model.AirplaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AirplaneTicketRepository extends JpaRepository<AirplaneTicket, UUID> {

    @Query(value = "select f.id from flight f inner join airplane_ticket airpT " +
            "on f.id = airpT.flight_id and airpT.time_created between ?2 and ?3 " +
            "inner join airplane a on f.airplane_id = a.id where a.airline_id = ?1", nativeQuery = true)
    List<UUID> getBoughtFlights(String airlineID, Date startDate, Date endDate);

    List<AirplaneTicket> findAllByFlightId(UUID flightId);

    List<AirplaneTicket> findByUser_Id(UUID id);
}
