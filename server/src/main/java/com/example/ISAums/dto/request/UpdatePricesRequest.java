package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePricesRequest {

    @NotNull
    @Range(min = 0)
    private Double checkingInSuitcasePrice;

    @NotNull
    @Range(min = 0)
    private Double handLuggagePrice;

}
