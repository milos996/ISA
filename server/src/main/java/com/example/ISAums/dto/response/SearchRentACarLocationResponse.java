package com.example.ISAums.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRentACarLocationResponse {

    private UUID id;

    private String name;

    private GetAddressResponse address;

    private List<GetAgencyLocation> locations;

    private String description;

    private Double rating;

}
