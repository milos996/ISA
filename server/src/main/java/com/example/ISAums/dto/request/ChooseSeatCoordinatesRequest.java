package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChooseSeatCoordinatesRequest {

    private Integer rowNumber;

    private Integer columnNumber;

    private Integer segmentNumber;

}
