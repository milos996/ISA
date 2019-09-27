package com.example.ISAums.repository;

import com.example.ISAums.dto.response.GetAirlineIncomeResponse;
import com.example.ISAums.dto.response.GetSoldAirlineTicketsResponse;
import com.example.ISAums.model.AirplaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface AirplaneTicketRepository extends JpaRepository<AirplaneTicket, UUID> {

    List<AirplaneTicket> findAllByFlightId(UUID flightId);

    @Query(value = "select ticket.number_of_segment as t_segment, " +
            "ticket.number_of_row as t_row, ticket.number_of_column as t_column, sum(f.price) as income from isa_database.flight f join isa_database.airplane_ticket ticket on " +
            "f.id = ticket.flight_id inner join isa_database.airplane a on f.airplane_id = a.id" +
            " where a.airline_id = :airlineID and ticket.time_created between :startDate and :endDate" +
            " group by ticket.id", nativeQuery = true)
    List<GetAirlineIncomeResponse> getIncome(String airlineID, String startDate, String endDate);

    @Query(value = "select ticket.number_of_segment as t_segment, ticket.number_of_row as t_row, ticket.number_of_column as t_column, " +
            "count(ticket.id) as sold_tickets from isa_database.airplane_ticket ticket join " +
            "isa_database.flight flight on ticket.flight_id = flight.id join isa_database.airplane airplane on " +
            "flight.airplane_id = airplane.id join isa_database.airline airline on airplane.airline_id = airline.id " +
            "where airline.id = :airlineID and ticket.time_created between :startDate and :endDate group by ticket.id", nativeQuery = true)
    List<GetSoldAirlineTicketsResponse> getSoldTickets(String airlineID, String startDate, String endDate);

    List<AirplaneTicket> findByUser_Id(UUID id);
}
