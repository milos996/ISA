package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAirlineIncomeRequest {

    @NotNull
    private UUID airlineID;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

}
