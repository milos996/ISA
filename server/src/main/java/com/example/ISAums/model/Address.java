package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@Where(clause = "is_deleted='false'")
public class Address extends BaseEntity {

    @Builder
    public Address(UUID id, @NotBlank @Size(max = CITY_SIZE) String city, @NotBlank @Size(max = STATE_SIZE) String state, @NotBlank @Size(max = STREET_SIZE) String street, @NotNull @Range(min = 0, max = 180) Double longitude, @NotNull @Range(min = 0, max = 90) Double latitude) {
        super(id);
        this.city = city;
        this.state = state;
        this.street = street;
        this.longitude = longitude;
        this.latitude = latitude;
    }

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
    @Range(min=0, max=180)
    private Double longitude;

    @Column(name = "latitude")
    @NotNull
    @Range(min=0 , max=90)
    private Double latitude;
}
