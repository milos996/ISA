package com.example.ISAums.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.CITY_SIZE;
import static com.example.ISAums.util.ValidationConstraints.STATE_SIZE;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "destination")
@Where(clause = "is_deleted='false'")
public class Destination extends BaseEntity {

    @Column(name = "city")
    @NotBlank
    @Size(max = CITY_SIZE)
    private String  city;

    @Column(name = "state")
    @NotBlank
    @Size(max = STATE_SIZE)
    private String  state;
}
