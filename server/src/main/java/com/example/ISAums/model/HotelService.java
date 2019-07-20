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
@Table(name = "hotel_service")
@Where(clause = "is_deleted='false'")
public class HotelService extends BaseEntity {

    @JoinColumn(name = "hotel_id")
    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    private Hotel hotel;

    @JoinColumn(name = "service_id")
    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    private Service service;
}
