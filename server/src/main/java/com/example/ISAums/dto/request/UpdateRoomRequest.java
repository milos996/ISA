package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomRequest {

    @Nullable
    private Integer number;

    @Nullable
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
