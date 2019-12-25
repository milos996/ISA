package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.MARK_SIZE;
import static com.example.ISAums.util.ValidationConstraints.MAX_NUMBER_OF_SEGMENTS;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAirplaneRequest {

    @NotBlank
    @Size(max = MARK_SIZE)
    private String mark;

    @NotNull
    @Range(min = 0)
    private Integer numberOfRows;

    @NotNull
    @Range(min = 0)
    private Integer numberOfColumnsPerSegment;

    @Range(min = 0, max = MAX_NUMBER_OF_SEGMENTS)
    @NotNull
    private Integer numberOfSegments;

    @NotNull
    private UUID airlineId;

}
