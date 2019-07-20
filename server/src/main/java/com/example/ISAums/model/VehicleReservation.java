package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle_reservation")
@Where(clause = "is_deleted='false'")
public class VehicleReservation extends BaseEntity {

    @Column(name = "start_date")
    @NotNull
    private Date startDate;

    @Column(name = "end_date")
    @NotNull
    private Date endDate;

    @Column(name = "price")
    @NotNull
    @Range(min = 0)
    private Double price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_ticket_id")
    @NotNull
    private AirplaneTicket airplaneTicket;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @NotNull
    private Vehicle vehicle;

}
