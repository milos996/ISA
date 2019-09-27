package com.example.ISAums.dto.response;

import com.example.ISAums.model.AirplaneTicket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "airlineIncome", types = {AirplaneTicket.class})
public interface GetAirlineIncomeResponse {

    @Value("#{'(' + target.t_segment + ', ' + target.t_row + ', ' + target.t_column + ')'}")
    String getTicket();

    @Value("#{target.income}")
    Double getIncome();


}
