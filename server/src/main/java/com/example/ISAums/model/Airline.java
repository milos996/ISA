package com.example.ISAums.model;
import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airline")
@Where(clause = "is_deleted='false'")
public class Airline extends BaseEntity {

    @Column(name = "name")
    @NotBlank
    @Size(max = AIRLINE_NAME_SIZE)
    private String name;

    @Column(name = "description")
    @NotBlank
    @Size(max = DESCRIPTION_SIZE)
    private String description;

    @Column(name = "checking_in_suitcase_price")
    @NotNull
    @Range(min = 0)
    private Double checkingInSuitcasePrice;

    @Column(name = "hand_luggage_price")
    @NotNull
    @Range(min = 0)
    private Double handLuggagePrice;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "rating")
    @Range(min = 0, max = MAX_RATING)
    private Double rating;

}
