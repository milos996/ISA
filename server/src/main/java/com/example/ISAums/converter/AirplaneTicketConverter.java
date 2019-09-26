package com.example.ISAums.converter;

import com.example.ISAums.dto.response.CreateQuickTicketBookingResponse;
import com.example.ISAums.model.AirplaneTicket;

public class AirplaneTicketConverter {

    public static CreateQuickTicketBookingResponse toCreateQuickTicketBookingResponseFromAirplaneTicket(AirplaneTicket airplaneTicket){

        return CreateQuickTicketBookingResponse.builder()
                .quickTicketId(airplaneTicket.getId())
                .build();
    }

}
