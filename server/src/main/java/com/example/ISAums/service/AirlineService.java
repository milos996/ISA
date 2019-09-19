package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.UpdateAirlineRequest;
import com.example.ISAums.dto.request.UpdateSeatConfigurationRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.RatingType;
import com.example.ISAums.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.ISAums.converter.RatingConverter.toRatingFromCreateRequest;
import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
public class AirlineService {

    private final RatingRepository ratingRepository;
    private final AirlineRepository airlineRepository;
    private final AddressRepository addressRepository;
    private final AirplaneRepository airplaneRepository;
    private final AirplaneTicketRepository airplaneTicketRepository;

    public AirlineService(RatingRepository ratingRepository,
                          AirlineRepository airlineRepository,
                          AddressRepository addressRepository,
                          AirplaneRepository airplaneRepository, AirplaneTicketRepository airplaneTicketRepository){

        this.ratingRepository = ratingRepository;
        this.airlineRepository = airlineRepository;
        this.addressRepository = addressRepository;
        this.airplaneRepository = airplaneRepository;
        this.airplaneTicketRepository = airplaneTicketRepository;
    }

    public Double getAverageRating(UUID airlineId) {

        double sum = 0;
        List<Integer> marks = ratingRepository.getMarksByEntityId(String.valueOf(airlineId), RatingType.AIRLINE);

        for(int i : marks)
            sum += i;

        return sum/marks.size();
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateAirlineRequest request){

        Airline tmpAirline = airlineRepository.getAnotherWithThisName(request.getName(), String.valueOf(request.getId()));

        if(tmpAirline != null){
            throw new EntityAlreadyExistsException(request.getName());
        }

        Optional<Address> address = addressRepository.findById(request.getAddress().getId());

        if (address.get() == null) {
            throw new EntityWithIdDoesNotExist("Address", request.getAddress().getId());
        }

        copyNonNullProperties(request.getAddress(), address.get());
        addressRepository.save(address.get());

        updateSeatConfiguration(request.getSeatConfiguration(), request.getId());

        Optional<Airline> airline = airlineRepository.findById(request.getId());
        airline.get().setCheckingInSuitcasePrice(request.getPricesRequest().getCheckingInSuitcasePrice());
        airline.get().setHandLuggagePrice(request.getPricesRequest().getHandLuggagePrice());

        copyNonNullProperties(request, airline.get(), "address", "seatConfiguration", "pricesRequest");

        airlineRepository.save(airline.get());
    }

    private void updateSeatConfiguration(UpdateSeatConfigurationRequest request, UUID airlineId){

        List<Airplane> airplane = airplaneRepository.findAllByAirlineId(airlineId);

        for(int i = 0; i < airplane.size(); i++){
            airplane.get(i).setNumberOfColumnsPerSegment(request.getNumberOfColumnsPerSegment());
            airplane.get(i).setNumberOfRows(request.getNumberOfRows());
            airplane.get(i).setNumberOfSegments(request.getNumberOfSegments());
            airplaneRepository.save(airplane.get(i));
        }
    }

    public Airline getAirline(String airlineId) {
        return airlineRepository.findById(UUID.fromString(airlineId)).get();
    }

    @Transactional(rollbackFor = Exception.class)
    public void rate(CreateRatingRequest request) {
        AirplaneTicket airplaneTicket = airplaneTicketRepository.getOne(request.getReservationId());

        if (airplaneTicket == null)
            throw new EntityWithIdDoesNotExist("airplane ticket",request.getReservationId());

        Airline airline = airplaneTicket.getFlight().getAirplane().getAirline();
        if (ratingRepository.checkIfUserAlreadyRateEntity("1a8591af-7141-4ecf-aee4-a4963b56db31", airline.getId().toString(), RatingType.AIRLINE.name()) != null)
            throw new CustomException("You already rate this airline!");

        Rating rating = toRatingFromCreateRequest(airline.getId(), request, RatingType.AIRLINE);
        rating.setUserID(UUID.fromString("1a8591af-7141-4ecf-aee4-a4963b56db31"));
        ratingRepository.save(rating);

        airline.setRating(ratingRepository.getAverageMarkForEntity(airline.getId().toString(), RatingType.AIRLINE.name()));
        airlineRepository.save(airline);
    }

    @Transactional(readOnly = true)
    public List<Airline> sort(String by) {
        if (by.equals("name"))
            return airlineRepository.sortByName();
        else if(by.equals("handLuggage"))
            return airlineRepository.sortByHandLuggagePrice();
        else if(by.equals("suitcasePrice"))
            return airlineRepository.sortBySuitcasePrice();
        else if(by.equals("rating"))
            return airlineRepository.sortByRating();
        else if(by.equals("address"))
            return airlineRepository.sortByAddress();
        else
            throw  new CustomException("Unknown attribute!");
    }
}
