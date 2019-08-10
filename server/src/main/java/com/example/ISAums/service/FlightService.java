package com.example.ISAums.service;
import com.example.ISAums.dto.request.CreateFlightRequest;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Flight;
import com.example.ISAums.model.Airplane;
import com.example.ISAums.model.enumeration.RatingType;
import com.example.ISAums.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirlineDestinationRepository airlineDestinationRepository;
    private final AirplaneRepository airplaneRepository;
    private final RatingRepository ratingRepository;

    public FlightService(FlightRepository flightRepository, AirlineDestinationRepository airlineDestinationRepository,
                         AirplaneRepository airplaneRepository,
                         RatingRepository ratingRepository){

        this.flightRepository = flightRepository;
        this.airplaneRepository = airplaneRepository;
        this.airlineDestinationRepository = airlineDestinationRepository;
        this.ratingRepository = ratingRepository;

    }

    @Transactional(noRollbackFor = Exception.class)
    public Flight createFlight(CreateFlightRequest req) {

        Optional<AirlineDestination> airlineDestination = airlineDestinationRepository.findById(req.getAirlineDestination().getId());
        Optional<Airplane> airplane = airplaneRepository.findById(req.getAirplaneID());

        if(airlineDestination.get() == null){
            throw new EntityWithIdDoesNotExist("AirlineDestination", req.getAirlineDestination().getId());
        }

        if(airplane.get() == null){
            throw new EntityWithIdDoesNotExist("Airplane", req.getAirplaneID());
        }


        Flight flight = Flight.builder()
                .departureTime(req.getDepartureTime())
                .arrivalTime(req.getArrivalTime())
                .duration(req.getDuration())
                .length(req.getLength())
                .price(req.getPrice())
                .airlineDestination(airlineDestination.get())
                .airplane(airplane.get())
                .build();

        flightRepository.save(flight);

        return flight;
    }


    public List<Flight> getFlightsForDestination(UUID destinationId) {

        return flightRepository.getFlightsForDestination(String.valueOf(destinationId));

    }

    public List<Flight> searchFlights(UUID depAirportID, UUID  destAirportID, LocalDate departureTime, LocalDate arrivalTime) {

        String departureAirportID = String.valueOf(depAirportID);
        String destinationAirportID = String.valueOf(destAirportID);

        List<UUID> flightIDs = flightRepository.search(departureAirportID, destinationAirportID, departureTime, arrivalTime);
        List<Flight> flights = new ArrayList<Flight>(flightIDs.size());

        for(int i = 0; i < flightIDs.size(); i++){
            Optional<Flight> tmp = flightRepository.findById(UUID.fromString(String.valueOf(flightIDs.get(i))));
            flights.add(tmp.get());
        }

        return flights;
    }

    public Double getAverageRatingOfFlights(UUID flightId) {

        double sum = 0;
        List<Integer> marks = ratingRepository.getMarksByEntityId(String.valueOf(flightId), RatingType.FLIGHT);

        for(int i : marks)
            sum += i;

        return sum/marks.size();

    }
}
