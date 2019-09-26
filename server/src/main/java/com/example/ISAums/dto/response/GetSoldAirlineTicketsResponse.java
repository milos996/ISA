package com.example.ISAums.dto.response;

import com.example.ISAums.model.AirplaneTicket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "soldAirlineTicketsResponse", types = {AirplaneTicket.class})
public interface GetSoldAirlineTicketsResponse {

    @Value("#{target.price}")
    Double getPrice();

    @Value("#{target.sold_tickets}")
    Integer getSoldTickets();
}
