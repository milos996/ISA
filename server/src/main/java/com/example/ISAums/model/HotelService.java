package com.example.ISAums.model;

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
@Table(name = "hotel_service")
@Where(clause = "is_deleted='false'")
public class HotelService extends BaseEntity {

    @JoinColumn(name = "hotel_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

    @JoinColumn(name = "service_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    @Range(min = 0)
    @NotNull
    private Double price;
}
