package com.example.ISAums.service;
import com.example.ISAums.dto.request.CreateFlightRequest;
import com.example.ISAums.dto.request.UpdateFlightRequest;
import com.example.ISAums.dto.response.GetFlightAverageRatingResponse;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Flight;
import com.example.ISAums.model.Airplane;
import com.example.ISAums.model.enumeration.RatingType;
import com.example.ISAums.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static com.example.ISAums.util.UtilService.copyNonNullProperties;

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

    @Transactional(rollbackFor = Exception.class)
    public Flight createFlight(CreateFlightRequest request) {

        Optional<AirlineDestination> airlineDestination = airlineDestinationRepository.findById(request.getAirlineDestinationID());
        Optional<Airplane> airplane = airplaneRepository.findById(request.getAirplaneID());

        if(airlineDestination.get() == null){
            throw new EntityWithIdDoesNotExist("AirlineDestination", request.getAirlineDestinationID());
        }

        if(airplane.get() == null){
            throw new EntityWithIdDoesNotExist("Airplane", request.getAirplaneID());
        }

        Flight flight = Flight.builder()
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .duration(request.getDuration())
                .length(request.getLength())
                .price(request.getPrice())
                .airlineDestination(airlineDestination.get())
                .airplane(airplane.get())
                .build();

        flightRepository.save(flight);

        return flight;
    }

    public List<Flight> getFlightsForDestination(UUID destinationId) {

       return flightRepository.getFlightsForDestination(String.valueOf(destinationId));
    }

    public List<Flight> searchFlights(String fromDestinationCity, String toDestinationCity, String departureDate, String arrivalDate) {

        LocalDateTime departureLocalDate = LocalDateTime.parse(departureDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime arrivalLocalDate = LocalDateTime.parse(arrivalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<UUID> flightIDs = flightRepository.search(fromDestinationCity, toDestinationCity, departureLocalDate, arrivalLocalDate);
        List<Flight> flights = new ArrayList<>(flightIDs.size());

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

    public List<GetFlightAverageRatingResponse> getFlightsWithRatings(List<Flight> flights){

        List<GetFlightAverageRatingResponse> flightWithRatings = new ArrayList<>(flights.size());

        for(int i = 0; i < flights.size(); i++){
            Optional<Flight> flight = flightRepository.findById(flights.get(i).getId());
            double rating = getAverageRatingOfFlights(flights.get(i).getId());

            GetFlightAverageRatingResponse getFlightAverageRatingResponse = GetFlightAverageRatingResponse.builder()
                                                                    .flight(flight.get())
                                                                    .avgRating(rating)
                                                                    .build();
            flightWithRatings.add(getFlightAverageRatingResponse);
        }

        return flightWithRatings;
    }

    public List<Flight> getQuickBooking(UUID airlineId) {

        List<UUID> flightIDs = flightRepository.getQuickBookingFlights(airlineId);
        List<Flight> flights = new ArrayList<>(flightIDs.size());
        double discount = 10;
        double currentPrice;

        for(int i = 0; i < flightIDs.size(); i++){
            Optional<Flight> tmp = flightRepository.findById(UUID.fromString(String.valueOf(flightIDs.get(i))));
            flights.add(tmp.get());
        }

        for(int i = 0; i < flights.size(); i++){

            currentPrice = flights.get(i).getPrice();
            flights.get(i).setPrice(currentPrice - currentPrice/100 * discount);
        }

        return  flights;
    }

    @Transactional(rollbackFor = Exception.class)
    public Flight update(UpdateFlightRequest request) {

        if(!flightRepository.existsById(request.getId())){
            throw new EntityWithIdDoesNotExist("Flight", request.getId());
        }

        Optional<Flight> flight = flightRepository.findById(request.getId());
        UUID airlineDestinationId = flight.get().getAirlineDestination().getId();

        if(!String.valueOf(airlineDestinationId).equals(String.valueOf(request.getAirlineDestinationId()))){

            Optional<AirlineDestination> tmpAirlineDestination = airlineDestinationRepository.findById(request.getAirlineDestinationId());
            flight.get().setAirlineDestination(tmpAirlineDestination.get());
        }

        copyNonNullProperties(request, flight.get(), "airlineDestination", "airplane");
        flightRepository.save(flight.get());

        return flight.get();
    }

    public AirlineDestination getFlightDestination(String flightId) {

        UUID airlineDestinationId = flightRepository.getFlightDestination(UUID.fromString(flightId));
        Optional<AirlineDestination> airlineDestination = airlineDestinationRepository.findById(airlineDestinationId);
        return airlineDestination.get();
    }

    public List<Flight> getFlightsOfAirline(UUID airlineId) {

        List<UUID> flightIDs = flightRepository.getFlightsByAirlineId(airlineId);
        List<Flight> flights = new ArrayList<>(flightIDs.size());

        for(int i = 0; i < flightIDs.size(); i++){
            Optional<Flight> tmp = flightRepository.findById(UUID.fromString(String.valueOf(flightIDs.get(i))));
            flights.add(tmp.get());
        }

        return flights;
    }

    public List<AirlineDestination> getDestinations(UUID airlineId) {

        List<UUID> airlineDestinationIDs = flightRepository.getAirlineDestinations(airlineId);
        List<AirlineDestination> airlineDestinations = new ArrayList<>(airlineDestinationIDs.size());

        for(int i = 0; i < airlineDestinationIDs.size(); i++){
            Optional<AirlineDestination> tmp = airlineDestinationRepository.findById(UUID.fromString(String.valueOf(airlineDestinationIDs.get(i))));
            airlineDestinations.add(tmp.get());
        }

        return airlineDestinations;
    }
}
