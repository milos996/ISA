package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.CreateRoomRequest;
import com.example.ISAums.dto.request.UpdateRoomRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.HotelAdmin;
import com.example.ISAums.model.HotelReservation;
import com.example.ISAums.model.Rating;
import com.example.ISAums.model.Room;
import com.example.ISAums.model.enumeration.RatingType;
import com.example.ISAums.repository.HotelReservationRepository;
import com.example.ISAums.repository.RatingRepository;
import com.example.ISAums.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.ISAums.converter.RatingConverter.toRatingFromCreateRequest;
import static com.example.ISAums.converter.RoomConverter.toRoomFromRequest;
import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelReservationRepository hotelReservationRepository;
    private final RatingRepository ratingRepository;

    public RoomService(RoomRepository roomRepository, HotelReservationRepository hotelReservationRepository, RatingRepository ratingRepository) {
        this.roomRepository = roomRepository;
        this.hotelReservationRepository = hotelReservationRepository;
        this.ratingRepository = ratingRepository;
    }

    @Transactional(readOnly = true)
    public List<Room> get(UUID hotelId, LocalDate startDate, Integer nights, Integer people, Double fromPrice, Double toPrice) {
        LocalDate endDate = startDate != null ? startDate.plusDays(nights) : null;
        return this.roomRepository.getRooms(hotelId, people, fromPrice, toPrice);
//        return this.roomRepository.getRooms(hotelId, startDate, endDate, people, fromPrice, toPrice);
    }

    @Transactional(readOnly = true)
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    // TODO: Use token to get user and his hotel id
    @Transactional(rollbackFor = Exception.class)
    public Room createRoom(CreateRoomRequest request) {

        // TODO: Delete this line when  authentication gets implemented
        HotelAdmin user = new HotelAdmin();
        if (roomRepository.existsByFloorAndNumberAndHotelId(request.getFloor(), request.getNumber(), user.getHotel().getId())) {
            throw new CustomException("Room at this floor " + request.getFloor() + " and with this room number " + request.getNumber() + " already exist!");
        }

        Room room = toRoomFromRequest(request, user.getHotel());

        roomRepository.save(room);

        return room;
    }

    @Transactional(rollbackFor = Exception.class)
    public Room updateRoom(@Valid UUID roomId, UpdateRoomRequest request) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);

        if (!optionalRoom.isPresent()) {
            throw new EntityWithIdDoesNotExist("Room", roomId);
        }

        Room room = optionalRoom.get();

        // TODO: Check this, may be problem because you have two different classes
        copyNonNullProperties(request, room, "hotel");
        roomRepository.save(room);

        return room;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRoom(UUID roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (!room.isPresent()) {
            throw new EntityWithIdDoesNotExist("Room", roomId);
        }
//        Room roomToDelete = hotelReservationRepository.existsByRoomWhereEndDateIsAfterToday(roomId.toString());

        roomRepository.delete(room.get());

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public void rate(CreateRatingRequest request) {
        HotelReservation hotelReservation = hotelReservationRepository.findById(request.getReservationId()).orElse(null);

        if (hotelReservation == null)
            throw new EntityWithIdDoesNotExist("hotel reservation",request.getReservationId());

//        if (hotelReservation.getEndDate().compareTo(new LocalDate()) >= 0)
//            throw new CustomException("You did not left hotel yet!");


        Room room = hotelReservation.getRoom();

//        if (ratingRepository.checkIfUserAlreadyRateEntity(userId, request.getEntityId(), RatingType.RENT_A_CAR.name()) != null)
//            throw new CustomException("You already rate this room!");

        Rating rating = toRatingFromCreateRequest(room.getId(), request, RatingType.ROOM);
//        rating.setUserID(userId);
        ratingRepository.save(rating);

        room.setRating(ratingRepository.getAverageMarkForEntity(room.getId().toString(), RatingType.ROOM.name()));
        roomRepository.save(room);

    }
}
