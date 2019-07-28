package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomRequest {

    @NotNull
    private UUID id;

    private Integer number;

    private Integer floor;

    @Range(min = 0)
    private Double priceSummer;


    @Range(min = 0)
    private Double priceWinter;


    @Range(min = 0)
    private Double priceSpring;


    @Range(min = 0)
    private Double priceAutumn;

    @Range(min = 1)
    private Integer numberOfPeople;
}
