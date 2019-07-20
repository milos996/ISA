package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@Where(clause = "is_deleted='false'")
public class Address extends BaseEntity {

    @Column(name = "city")
    @NotBlank
    @Size(max = CITY_SIZE)
    private String city;

    @Column(name = "state")
    @NotBlank
    @Size(max = STATE_SIZE)
    private String state;

    @Column(name = "street")
    @NotBlank
    @Size(max = STREET_SIZE)
    private String street;

    @Column(name = "longitude")
    @NotNull
    private Double longitude;

    @Column(name = "latitude")
    @NotNull
    private Double latitude;
}
