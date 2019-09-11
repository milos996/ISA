package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentACarResponse {

    private String name;

    private String description;

    private String street;

    private String city;

    private String state;

    private Double longitude;

    private Double latitude;


}