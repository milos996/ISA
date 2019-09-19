package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

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
    private Double summerPrice;


    @NotNull
    @Range(min = 0)
    private Double winterPrice;


    @NotNull
    @Range(min = 0)
    private Double springPrice;


    @NotNull
    @Range(min = 0)
    private Double autumnPrice;

    @NotNull
    @Range(min = 1)
    private Integer numberOfPeople;

    @NotBlank
    private UUID hotelId;
}
