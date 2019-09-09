package com.example.ISAums.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRentACarLocationResponse {

    private String city;

    private String state;

    private String rentACarName;

    private String address;

    private Double rating;

}
