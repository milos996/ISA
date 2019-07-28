package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateRoomRequest {

    @NotNull
    private Integer number;

    @NotNull
    private Integer floor;

    @NotNull
    @Range(min = 0)
    private Double priceSummer;


    @NotNull
    @Range(min = 0)
    private Double priceWinter;


    @NotNull
    @Range(min = 0)
    private Double priceSpring;


    @NotNull
    @Range(min = 0)
    private Double priceAutumn;

    @NotNull
    @Range(min = 1)
    private Integer numberOfPeople;

}
