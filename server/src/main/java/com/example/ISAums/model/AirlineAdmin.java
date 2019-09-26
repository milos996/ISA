package com.example.ISAums.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airline_admin")
@Where(clause = "is_deleted='false'")
public class AirlineAdmin extends BaseEntity {

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    private Airline airline;
}



