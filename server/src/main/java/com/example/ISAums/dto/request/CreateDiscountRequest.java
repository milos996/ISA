package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiscountRequest {

    @NotNull
    private String entityId;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

    @NotNull
    private Double rate;

}
