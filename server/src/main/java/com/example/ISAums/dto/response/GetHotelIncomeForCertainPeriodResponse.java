package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetHotelIncomeForCertainPeriodResponse {

    private Date startDate;

    private Date endDate;

    private Double income;
}
