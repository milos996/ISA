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

import static com.example.ISAums.util.ValidationConstraints.NAME_SIZE;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group_trip")
@Where(clause = "is_deleted='false'")
public class GroupTrip extends BaseEntity {
    @Column(name = "name")
    @NotBlank
    @Size(max = NAME_SIZE)
    private String name;
}
