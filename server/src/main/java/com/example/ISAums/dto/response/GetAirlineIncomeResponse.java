package com.example.ISAums.dto.response;

import com.example.ISAums.model.AirplaneTicket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.sql.Time;

@Projection(name = "airlineIncome", types = {AirplaneTicket.class})
public interface GetAirlineIncomeResponse {

    @Value("#{target.duration}")
    Time getDuration();

    @Value("#{target.income}")
    Double getIncome();


}
