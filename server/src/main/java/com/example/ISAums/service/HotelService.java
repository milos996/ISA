package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateHotelRequest;
import com.example.ISAums.dto.request.UpdateHotelRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.Hotel;
import com.example.ISAums.model.HotelAdmin;
import com.example.ISAums.model.HotelReservation;
import com.example.ISAums.model.enumeration.ReportType;
import com.example.ISAums.repository.AddressRepository;
import com.example.ISAums.repository.HotelRepository;
import com.example.ISAums.repository.HotelReservationRepository;
import com.example.ISAums.util.UtilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.example.ISAums.converter.HotelConverter.toAddressFromRequest;
import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;
    private final HotelReservationRepository hotelReservationRepository;

    public HotelService(HotelRepository hotelRepository, AddressRepository addressRepository, HotelReservationRepository hotelReservationRepository) {
        this.hotelRepository = hotelRepository;
        this.addressRepository = addressRepository;
        this.hotelReservationRepository = hotelReservationRepository;
    }

    @Transactional(readOnly = true)
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Hotel createHotel(CreateHotelRequest request) {
        if (hotelRepository.existsByName(request.getName())) {
            throw new EntityAlreadyExistsException(request.getName());
        }

        Address address = toAddressFromRequest(request);
        addressRepository.save(address);

        Hotel hotel = Hotel.builder()
                .name(request.getName())
                .description(request.getDescription())
                .address(address)
                .build();

        hotelRepository.save(hotel);

        return hotel;
    }


    @Transactional(rollbackFor = Exception.class)
    public Hotel updateHotel(UpdateHotelRequest request) {
        if (!hotelRepository.existsById(request.getId())) {
            throw new CustomException("Hotel with this id " + request.getId() + " does not exist");
        }

        if (hotelRepository.existsByName(request.getName())) {
            throw new EntityAlreadyExistsException(request.getName());
        }

        Optional<Address> address = addressRepository.findById(request.getAddress().getId());

        if (address.get() == null) {
            throw new CustomException("Address with this id " + request.getAddress().getId() + " does not exits!");
        }

        // TODO: Check this, may be problem because you have two different classes
        copyNonNullProperties(request.getAddress(), address.get());
        addressRepository.save(address.get());

        Optional<Hotel> hotel = hotelRepository.findById(request.getId());
        copyNonNullProperties(request, hotel.get(), "address");
        hotelRepository.save(hotel.get());

        return hotel.get();
    }

    @Transactional(readOnly = true)
    public List<HotelReservation> getHotelReservationsBasedOnReportType(UUID hotelId, ReportType reportType) {
        Map<String, Date> dateFilter = UtilService.getStartEndDateFromReportType(reportType);
        return null;
    }

    // TODO: When authentication is finished, use signed-in user to get to his hotel id
    @Transactional(readOnly = true)
    public Double getHotelIncomeForDate(Date startDate, Date endDate) {

        // TODO: Delete this line when u get user from token
        HotelAdmin admin = new HotelAdmin();

        List<HotelReservation> hotelReservation = hotelReservationRepository.findAllWhereDateBetweenStartAndEndDate(startDate, endDate, admin.getHotel().getId().toString());
        return hotelReservation.stream()
                .mapToDouble(currReservations -> {
                    return currReservations.getPrice();
                })
                .reduce(0, (subtotal, price) -> subtotal + price);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteHotel(UUID hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);

        if (hotel.get() == null) {
            throw new CustomException("Hotel with that id " + hotelId + " does not exist!");
        }

        hotelRepository.delete(hotel.get());
    }
}
