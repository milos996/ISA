package com.example.ISAums.dto.request;

import com.example.ISAums.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.AIRLINE_NAME_SIZE;
import static com.example.ISAums.util.ValidationConstraints.DESCRIPTION_SIZE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineReq {
    @NotBlank
    @Size(max = AIRLINE_NAME_SIZE)
    private String name;

    @NotBlank
    @Size(max = DESCRIPTION_SIZE)
    private String description;

    @NotNull
    @Range(min = 0)
    private Double checkingInSuitcasePrice;

    @NotNull
    @Range(min = 0)
    private Double handLuggagePrice;

    private UUID addressId;
}
