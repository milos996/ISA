package com.example.ISAums.service;
import com.example.ISAums.dto.request.CreateFlightRequest;
import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.UpdateFlightRequest;
import com.example.ISAums.dto.response.GetFlightAverageRatingResponse;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.RatingType;
import com.example.ISAums.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.ISAums.converter.RatingConverter.toRatingFromCreateRequest;
import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirlineDestinationRepository airlineDestinationRepository;
    private final AirplaneRepository airplaneRepository;
    private final AirplaneTicketRepository airplaneTicketRepository;
    private final RatingRepository ratingRepository;

    public FlightService(FlightRepository flightRepository, AirlineDestinationRepository airlineDestinationRepository,
                         AirplaneRepository airplaneRepository,
                         AirplaneTicketRepository airplaneTicketRepository, RatingRepository ratingRepository){

        this.flightRepository = flightRepository;
        this.airplaneRepository = airplaneRepository;
        this.airlineDestinationRepository = airlineDestinationRepository;
        this.airplaneTicketRepository = airplaneTicketRepository;
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

    public List<Flight> searchFlights(UUID depAirlineID, UUID  destAirlineID, LocalDate departureTime, LocalDate arrivalTime) {

        String departureAirlineID = String.valueOf(depAirlineID);
        String destinationAirlineID = String.valueOf(destAirlineID);

        List<UUID> flightIDs = flightRepository.search(departureAirlineID, destinationAirlineID, departureTime, arrivalTime);
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

    @Transactional(rollbackFor = Exception.class)
    public void rate(CreateRatingRequest request) {
        AirplaneTicket airplaneTicket = airplaneTicketRepository.getOne(request.getReservationId());

        if (airplaneTicket == null)
            throw new EntityWithIdDoesNotExist("airplane ticket",request.getReservationId());

        Flight flight = airplaneTicket.getFlight();

        if (ratingRepository.checkIfUserAlreadyRateEntity("1a8591af-7141-4ecf-aee4-a4963b56db31", request.getReservationId().toString(), RatingType.FLIGHT.name()) != null)
            throw new CustomException("You already rate this flight!");

        Rating rating = toRatingFromCreateRequest(flight.getId(), request, RatingType.FLIGHT);
        rating.setUserID(UUID.fromString("1a8591af-7141-4ecf-aee4-a4963b56db31"));
        ratingRepository.save(rating);

        flight.setRating(ratingRepository.getAverageMarkForEntity(flight.getId().toString(), RatingType.FLIGHT.name()));
        flightRepository.save(flight);
    }
}
