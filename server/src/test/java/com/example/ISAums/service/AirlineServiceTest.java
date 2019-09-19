package com.example.ISAums.service;

import static org.assertj.core.api.Java6Assertions.*;
import com.example.ISAums.dto.request.UpdateAddressRequest;
import com.example.ISAums.dto.request.UpdateAirlineRequest;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.Airline;
import com.example.ISAums.repository.AddressRepository;
import com.example.ISAums.repository.AirlineRepository;
import com.example.ISAums.repository.AirplaneRepository;
import com.example.ISAums.repository.RatingRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AirlineServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AirplaneRepository airplaneRepository;

    @InjectMocks
    private AirlineService airlineService;

    @Test
    public void averageRatingIsNumber(){

        List<Integer> marks = new ArrayList<>();
        marks.add(9);
        marks.add(7);
        marks.add(10);
        when(ratingRepository.getMarksByEntityId(any(String.class), any(String.class))).thenReturn(marks);
        Double avgRating = airlineService.getAverageRating(UUID.fromString("1c29e26a-14e2-4610-831a-b716b66f95de"));
        assertThat(avgRating).isNotNaN();
    }

    @Test
    public void updateIsSuccesful(){

        Address address = Address.builder()
                .latitude(40.231)
                .longitude(19.250)
                .state("Serbia")
                .city("Belgrade")
                .street("Nemanjina")
                .build();

        UpdateAddressRequest addressRequest = UpdateAddressRequest.builder()
                .id(UUID.fromString("c96d32e0-d43f-4310-842a-be239b0f3bbb"))
                .latitude(40.231)
                .longitude(19.250)
                .state("Serbia")
                .city("Belgrade")
                .street("Nemanjina")
                .build();

        Airline airline = Airline.builder()
                .name("AirSerbia")
                .handLuggagePrice(200.0)
                .description("Nice")
                .checkingInSuitcasePrice(50.0)
                .address(address)
                .build();

        UpdateAirlineRequest request = UpdateAirlineRequest.builder()
                .address(addressRequest)
                .checkingInSuitcasePrice(220.3)
                .handLuggagePrice(30.0)
                .description("Nice")
                .name("AirSerbia")
                .id(UUID.fromString("48f47cd6-11d9-41ff-9482-4c992c8e88f8"))
                .build();

        when(airlineRepository.getAnotherWithThisName(any(String.class), any(String.class))).thenReturn(null);
        when(addressRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.ofNullable(address));
        when(airlineRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(airline));
        when(airlineRepository.save(any(Airline.class))).then(returnsFirstArg());

        Airline savedAirline = airlineService.update(request);
        assertEquals(savedAirline, airline);
    }
}
