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
@Table(name = "hotel_reservation_additional_service")
@Where(clause = "is_deleted='false'")
public class HotelReservationAdditionalService  extends BaseEntity {

    @JoinColumn(name = "hotel_reservation_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private HotelReservation hotelReservation;

    @JoinColumn(name = "hotel_service_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private HotelService hotelService;

}

