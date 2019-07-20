package com.example.ISAums.model;

import com.example.ISAums.model.enumeration.GroupTripStatus;
import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airplane_ticket")
@Where(clause = "is_deleted='false'")
public class AirplaneTicket extends BaseEntity {

    @JoinColumn(name = "user_id")
    @OneToMany(fetch = FetchType.EAGER)
    @NotNull
    private User user;

    @Column(name = "number_of_row")
    @NotNull
    @Range(min = 0)
    private Integer numberOfRow;

    @Column(name = "number_of_column")
    @NotNull
    @Range(min = 0)
    private Integer numberOfColumn;

    @Column(name = "number_of_segment")
    @NotNull
    @Range(min = 0)
    private Integer numberOfSegment;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_trip_id")
    private GroupTrip groupTrip;

    @Column(name = "group_trip_status")
    @Enumerated(EnumType.STRING)
    private GroupTripStatus groupTripStatus;
}
