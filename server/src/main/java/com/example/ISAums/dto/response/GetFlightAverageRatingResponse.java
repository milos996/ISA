package com.example.ISAums.dto.response;

import com.example.ISAums.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetFlightAverageRatingResponse {

    private Flight flight;

    private double avgRating;
}
