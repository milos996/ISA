package com.example.ISAums.service;

import com.example.ISAums.repository.AirlineDestinationRepository;
import com.example.ISAums.repository.AirplaneRepository;
import com.example.ISAums.repository.FlightRepository;
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
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private AirlineDestinationRepository airlineDestinationRepository;

    @Mock
    private AirplaneRepository airplaneRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    public void averageRatingOfFlightsIsNumber(){

        List<Integer> marks = new ArrayList<>();
        marks.add(9);
        marks.add(7);
        marks.add(10);

        when(ratingRepository.getMarksByEntityId(any(String.class), any(String.class))).thenReturn(marks);
        Double avgRating = flightService.getAverageRatingOfFlights(UUID.fromString("1c29e26a-14e2-4610-831a-b716b66f95de"));
        assertThat(avgRating).isNotNaN();
    }
}
