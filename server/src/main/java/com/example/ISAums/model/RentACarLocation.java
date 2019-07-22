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
@Table(name = "rent_a_car_location")
@Where(clause = "is_deleted='false'")
public class RentACarLocation extends BaseEntity {

    @NotNull
    @JoinColumn(name = "rent_a_car_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private RentACar rentACar;

    @NotNull
    @JoinColumn(name = "agency_location_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private AgencyLocation agencyLocation;
}
