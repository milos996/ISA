package com.example.ISAums.dto.response;

import com.example.ISAums.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentACarResponse {

    private UUID id;

    private String name;

    private String description;

    private GetAddressResponse address;

}
