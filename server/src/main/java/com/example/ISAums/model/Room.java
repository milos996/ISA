package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.example.ISAums.util.ValidationConstraints.MAX_RATING;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
@Where(clause = "is_deleted='false'")
public class Room extends BaseEntity {

    @Column(name = "number")
    @NotNull
    private Integer number;

    @Column(name = "floor")
    @NotNull
    private Integer floor;

    @Column(name = "price_summer")
    @NotNull
    @Range(min = 0)
    private Double priceSummer;


    @Column(name = "price_winter")
    @NotNull
    @Range(min = 0)
    private Double priceWinter;


    @Column(name = "price_spring")
    @NotNull
    @Range(min = 0)
    private Double priceSpring;


    @Column(name = "price_autumn")
    @NotNull
    @Range(min = 0)
    private Double priceAutumn;

    @Column(name = "number_of_people")
    @NotNull
    @Range(min = 1)
    private Integer numberOfPeople;

    @JoinColumn(name = "hotel_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Hotel hotel;

    @Version
    private Integer version;

    @Column(name = "rating")
    @Range(min = 0, max = MAX_RATING)
    private Double rating;


}
