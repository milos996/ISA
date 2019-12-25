package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

import static com.example.ISAums.util.ValidationConstraints.MAX_NUMBER_OF_SEGMENTS;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSeatConfigurationRequest {

    @NotNull
    @Range(min = 0)
    private Integer numberOfRows;

    @NotNull
    @Range(min = 0)
    private Integer numberOfColumnsPerSegment;

    @Range(min = 0, max = MAX_NUMBER_OF_SEGMENTS)
    @NotNull
    private Integer numberOfSegments;

}
