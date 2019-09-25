package com.example.ISAums.dto.response;

import com.example.ISAums.model.Airline;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAirlineAdminResponse {

    private Airline airline;
}
