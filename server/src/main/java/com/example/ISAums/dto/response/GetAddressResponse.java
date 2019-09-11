package com.example.ISAums.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAddressResponse {

    private String city;

    private String country;

    private String street;

    private Double longitude;

    private Double latitude;

}
