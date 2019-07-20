package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle")
@Where(clause = "is_deleted='false'")
public class Vehicle extends BaseEntity {

    @Column(name = "brand")
    @NotBlank
    @Size(max = VEHICLE_BRAND_SIZE)
    private String brand;

    @Column(name = "model")
    @NotBlank
    @Size(max = VEHICLE_MODEL_SIZE)
    private String model;

    @Column(name = "type")
    @NotBlank
    @Size(max = VEHICLE_TYPE_SIZE)
    private String type;

    @Column(name = "number_of_people")
    @NotNull
    @Range(min = 1)
    private Integer numberOfPeople;

    @Column(name = "price_per_day")
    @NotNull
    @Range(min = 0)
    private Double pricePerDay;

    @Column(name = "year_of_production")
    @NotNull
    @Range(min = 0)
    private Integer yearOfProduction;

    @Column(name = "rating")
    @Range(min = 0, max = MAX_RATING)
    private Double rating;

    @JoinColumn(name = "rent_a_car_id")
    @OneToMany(fetch = FetchType.LAZY)
    @NotNull
    private RentACar rentACar;
}
