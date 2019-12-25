package com.example.ISAums.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.MARK_SIZE;
import static com.example.ISAums.util.ValidationConstraints.MAX_NUMBER_OF_SEGMENTS;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airplane")
@Where(clause = "is_deleted='false'")
public class Airplane extends BaseEntity {

    @Column(name = "mark")
    @NotBlank
    @Size(max = MARK_SIZE)
    private String mark;

    @Column(name = "number_of_rows")
    @NotNull
    @Range(min = 0)
    private Integer numberOfRows;

    @Column(name = "number_of_columns_per_segment")
    @NotNull
    @Range(min = 0)
    private Integer numberOfColumnsPerSegment;

    @Column(name = "number_of_segments")
    @Range(min = 0, max = MAX_NUMBER_OF_SEGMENTS)
    @NotNull
    private Integer numberOfSegments;

    @JoinColumn(name = "airline_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Airline airline;
}
