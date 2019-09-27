package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel")
@Where(clause = "is_deleted='false'")
public class Hotel extends BaseEntity {

    @Builder
    public Hotel(UUID id, @NotBlank @Size(max = NAME_SIZE) String name, @NotBlank @Size(max = DESCRIPTION_SIZE) String description, @Range(min = 0, max = MAX_RATING) Double rating, @NotNull Address address, Integer version) {
        super(id);
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.address = address;
        this.version = version;
    }

    @Column(name = "name")
    @NotBlank
    @Size(max = NAME_SIZE)
    private String name;

    @Column(name = "description")
    @NotBlank
    @Size(max = DESCRIPTION_SIZE)
    private String description;

    @Range(min = 0, max = MAX_RATING)
    @Column(name = "rating")
    private Double rating;

    @NotNull
    @JoinColumn(name = "address_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    @Version
    private Integer version = 1;
}

