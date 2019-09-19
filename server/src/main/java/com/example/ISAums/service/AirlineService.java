package com.example.ISAums.service;

import com.example.ISAums.dto.request.UpdateAirlineRequest;
import com.example.ISAums.dto.request.UpdateSeatConfigurationRequest;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.Airline;
import com.example.ISAums.model.Airplane;
import com.example.ISAums.model.enumeration.RatingType;
import com.example.ISAums.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
@RequiredArgsConstructor
public class AirlineService {

    private final RatingRepository ratingRepository;
    private final AirlineRepository airlineRepository;
    private final AddressRepository addressRepository;
    private final AirplaneRepository airplaneRepository;

    public Double getAverageRating(UUID airlineId) {

        double sum = 0;
        List<Integer> marks = ratingRepository.getMarksByEntityId(String.valueOf(airlineId), RatingType.AIRLINE.name());

        for(int i : marks)
            sum += i;

        return sum/marks.size();
    }

    @Transactional(rollbackFor = Exception.class)
    public Airline update(UpdateAirlineRequest request){

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

        Optional<Airline> airline = airlineRepository.findById(request.getId());
        airline.get().setCheckingInSuitcasePrice(request.getCheckingInSuitcasePrice());
        airline.get().setHandLuggagePrice(request.getHandLuggagePrice());
        copyNonNullProperties(request, airline.get(), "address");

        return airlineRepository.save(airline.get());
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

    public List<Airline> getAll() {
        return airlineRepository.findAll();
    }
}
