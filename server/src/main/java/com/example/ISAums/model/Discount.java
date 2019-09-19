package com.example.ISAums.model;

import com.example.ISAums.model.enumeration.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.UUID;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount")
@Where(clause = "is_deleted='false'")
public class Discount  extends  BaseEntity {

    @Column(name = "start_date")
    @NotNull
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull
    private LocalDate endDate;

    @Column(name = "entity_id")
    @NotNull
    @Type(type = "uuid-char")
    private UUID entityID;

    @Column(name = "entity_type")
    @Enumerated(EnumType.STRING)
    private DiscountType type;





}
