package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.DESCRIPTION_SIZE;
import static com.example.ISAums.util.ValidationConstraints.NAME_SIZE;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rent_a_car")
@Where(clause = "is_deleted='false'")
public class RentACar extends BaseEntity {

    @Column(name = "name")
    @NotBlank
    @Size(max = NAME_SIZE)
    private String name;

    @Column(name = "description")
    @NotBlank
    @Size(max = DESCRIPTION_SIZE)
    private String description;

    @NotNull
    @JoinColumn(name = "address_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;
}
