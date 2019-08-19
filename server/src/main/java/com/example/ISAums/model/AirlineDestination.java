package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airline_destination")
@Where(clause = "is_deleted='false'")
public class AirlineDestination extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_id")
    @NotNull
    private Destination destination;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id")
    @NotNull
    private Airline airline;
}
