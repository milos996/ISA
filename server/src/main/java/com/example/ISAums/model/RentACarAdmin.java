package com.example.ISAums.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rent_a_car_admin")
@Where(clause = "is_deleted='false'")
public class RentACarAdmin extends BaseEntity {
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_a_car_id")
    private RentACar rentACar;

    @Column(name = "is_not_first_login")
    private boolean isNotFirstLogin;
}
