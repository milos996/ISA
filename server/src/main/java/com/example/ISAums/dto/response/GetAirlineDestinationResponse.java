package com.example.ISAums.dto.response;

import com.example.ISAums.model.Airline;
import com.example.ISAums.model.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAirlineDestinationResponse {

   private Airline airline;

   private Destination destination;

}
