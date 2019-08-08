package com.example.ISAums.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airline_rating")
@Where(clause = "is_deleted='false'")
public class AirlineRating {

    @JoinColumn(name = "airline_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Airline airline;

    @JoinColumn(name = "rating_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Rating rating;
}
