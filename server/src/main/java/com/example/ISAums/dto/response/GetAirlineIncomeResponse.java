package com.example.ISAums.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAirlineIncomeResponse {

    private Date startDate;

    private Date endDate;

    private Double income;

}
