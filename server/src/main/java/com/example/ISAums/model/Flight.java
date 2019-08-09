package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight")
@Where(clause = "is_deleted='false'")
public class Flight extends BaseEntity {

  @Column(name = "departure_time")
  @NotNull
  private LocalDate departureTime;

  @Column(name = "arrival_time")
  @NotNull
  private LocalDate arrivalTime;

  @Column(name = "duration")
  @NotNull
  private Time duration;

  @Column(name = "length")
  @NotNull
  private Double length;

  @Column(name = "price")
  @NotNull
  @Range(min = 0)
  private Double price;


  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "airline_destination_id")
  @NotNull
  private AirlineDestination airlineDestination;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "airplane_id")
  @NotNull
  private Airplane airplane;


}

