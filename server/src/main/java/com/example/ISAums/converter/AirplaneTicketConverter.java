package com.example.ISAums.converter;

import com.example.ISAums.dto.response.CreateQuickTicketBookingResponse;
import com.example.ISAums.dto.response.TicketReservationResponse;
import com.example.ISAums.dto.response.GetAirplaneTicketResponse;
import com.example.ISAums.model.AirplaneTicket;

import java.util.List;
import java.util.stream.Collectors;

public class AirplaneTicketConverter {

    public static CreateQuickTicketBookingResponse toCreateQuickTicketBookingResponseFromAirplaneTicket(AirplaneTicket airplaneTicket){

        return CreateQuickTicketBookingResponse.builder()
                .quickTicketId(airplaneTicket.getId())
                .build();
    }

    public static TicketReservationResponse toTicketReservationResponseFromTicket(AirplaneTicket ticket) {
        return TicketReservationResponse.builder()
                .reservationId(ticket.getId())
                .build();
    }
    public static List<GetAirplaneTicketResponse> toGetAirplaneTicketResponseFromTickets(List<AirplaneTicket> airplaneTickets) {
        return airplaneTickets.stream()
                .map(airplaneTicket -> toGetAirplaneTicketResponseFromTicket(airplaneTicket))
                .collect(Collectors.toList());
    }

    public static GetAirplaneTicketResponse toGetAirplaneTicketResponseFromTicket(AirplaneTicket airplaneTicket) {
        return GetAirplaneTicketResponse
                .builder()
                .ticketId(airplaneTicket.getId())
                .flightId(airplaneTicket.getFlight().getId())
                .airline(airplaneTicket.getFlight().getAirplane().getAirline().getName())
                .returnTime(airplaneTicket.getFlight().getArrivalTime().toString())
                .departureTime(airplaneTicket.getFlight().getDepartureTime().toString())
                .duration(airplaneTicket.getFlight().getDuration())
                .price(airplaneTicket.getFlight().getPrice())
                .airlineRating(airplaneTicket.getFlight().getAirplane().getAirline().getRating())
                .flightRating(airplaneTicket.getFlight().getRating())
                .build();
    }

}
