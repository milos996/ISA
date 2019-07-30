package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel")
@Where(clause = "is_deleted='false'")
public class Hotel extends BaseEntity {

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
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<Room> rooms;
}

