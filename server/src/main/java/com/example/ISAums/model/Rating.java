package com.example.ISAums.model;

import com.example.ISAums.model.enumeration.RatingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating")
@Where(clause = "is_deleted='false'")
public class Rating extends BaseEntity{

    @Column(name = "entity_id")
    @NotNull
    private UUID entityID;

    @Column(name = "entity_type")
    @Enumerated(EnumType.STRING)
    private RatingType type;

    @Column(name = "mark")
    @NotNull
    @Range(min = 1, max = 10)
    private Integer mark;


}
