package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateHotelReservationsRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.*;
import com.example.ISAums.model.HotelService;
import com.example.ISAums.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelReservationService {
    private final AirplaneTicketRepository airplaneTicketRepository;
    private final HotelReservationRepository hotelReservationRepository;
    private final RoomRepository roomRepository;
    private final HotelServiceRepository hotelServiceRepository;
    private final UserRepository userRepository;

    public HotelReservationService(AirplaneTicketRepository airplaneTicketRepository, HotelReservationRepository hotelReservationRepository, RoomRepository roomRepository, HotelServiceRepository hotelServiceRepository, UserRepository userRepository) {
        this.airplaneTicketRepository = airplaneTicketRepository;
        this.hotelReservationRepository = hotelReservationRepository;
        this.roomRepository = roomRepository;
        this.hotelServiceRepository = hotelServiceRepository;
        this.userRepository = userRepository;
    }

    public void create(CreateHotelReservationsRequest request) {
        Optional<AirplaneTicket> optionalAirplaneTicket = airplaneTicketRepository.findById(request.getAirplaneTicketId());
        if (!optionalAirplaneTicket.isPresent()) {
            throw new EntityWithIdDoesNotExist("AirplaneTicker", request.getAirplaneTicketId());
        }

        List<HotelService> hotelServices = hotelServiceRepository.findAllById(request.getAdditionalServices());
        List<HotelReservation> hotelReservations =  request.getRooms().stream().map(roomId -> reserveRoom(roomId, optionalAirplaneTicket.get(), hotelServices, request.getDate(), request.getNumberOfNights())).collect(Collectors.toList());
        
        hotelReservationRepository.saveAll(hotelReservations);
    }

    private HotelReservation reserveRoom(UUID roomId, AirplaneTicket airplaneTicket, List<HotelService> hotelServices, LocalDate date, Integer numberOfNights) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (!optionalRoom.isPresent()) {
            throw new EntityWithIdDoesNotExist("Room", roomId);
        }
        LocalDate endDate = date.plusDays(numberOfNights);

         return HotelReservation.builder()
                .additionalServices(hotelServices)
                .room(optionalRoom.get())
                .price(optionalRoom.get().getPriceSummer())
                .startDate(date)
                .endDate(endDate)
                .airplaneTicket(airplaneTicket)
                .build();

    }

    @Transactional(readOnly = true)
    public List<HotelReservation> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());

        return hotelReservationRepository.findByAirplaneTicket_User_Id(user.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public List<HotelReservation> cancel(String hotelReservationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());

        HotelReservation hotelReservation = hotelReservationRepository.findById(UUID.fromString(hotelReservationId)).orElse(null);

        if (hotelReservation.getAirplaneTicket().getUser().getId() != user.getId())
            throw new CustomException("This reservation does not belong to you!");

        hotelReservationRepository.delete(hotelReservation);

        return  hotelReservationRepository.findByAirplaneTicket_User_Id(user.getId());
    }
}
