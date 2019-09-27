package com.example.ISAums.dto.response;

import com.example.ISAums.model.AirplaneTicket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "soldAirlineTicketsResponse", types = {AirplaneTicket.class})
public interface GetSoldAirlineTicketsResponse {

    @Value("#{'(' + target.t_segment + ', ' + target.t_row + ', ' + target.t_column + ')'}")
    String getTicket();

    @Value("#{target.sold_tickets}")
    Integer getSoldTickets();
}
