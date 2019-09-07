package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRentACarResponse {

    private String rentACarName;

    private List<SearchRentACarLocationResponse> rentACarOffices;

    private double rating;
}
