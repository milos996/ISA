package com.example.ISAums.dto.response;

<<<<<<< HEAD

=======
>>>>>>> 31a16195bf1a4babfc7afd87a207eefc0d2b8135
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.util.UUID;

=======
>>>>>>> 31a16195bf1a4babfc7afd87a207eefc0d2b8135
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAddressResponse {

<<<<<<< HEAD
    private UUID id;

    private String city;

    private String country;
=======
    private String city;

    private String state;
>>>>>>> 31a16195bf1a4babfc7afd87a207eefc0d2b8135

    private String street;

    private Double longitude;

    private Double latitude;

}
