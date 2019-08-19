package com.example.ISAums.dto.response;

import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAirlineAverageRatingResponse {

    private UUID airlineId;
    private Double avgRating;

}
