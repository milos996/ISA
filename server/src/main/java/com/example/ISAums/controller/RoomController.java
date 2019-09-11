package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRoomRequest;
import com.example.ISAums.dto.request.UpdateRoomRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Room;
import com.example.ISAums.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.RoomConverter.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

//    @GetMapping
//    public ResponseEntity<List<GetRoomResponse>> get() {
//        List<Room> rooms = roomService.getRooms();
//        return ResponseEntity.ok(toGetRoomsResponseFromListRooms(rooms));
//    }

    @GetMapping
    public ResponseEntity<List<GetRoomResponse>> get(@Valid @RequestParam("id") UUID hotelId,
                                                                   @Valid @Nullable @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
                                                                   @Valid @Nullable @RequestParam("nights") Integer nights,
                                                                   @Valid @Nullable @RequestParam("people") Integer people,
                                                                   @Valid @Nullable @RequestParam("fromPrice") Double fromPrice,
                                                                    @Valid @Nullable @RequestParam("toPrice") Double toPrice) {

        List<Room> rooms = roomService.get(hotelId, startDate, nights, people, fromPrice, toPrice);
        return ResponseEntity.ok(toGetRoomsResponseFromListRooms(rooms));
    }

    @PostMapping
    public ResponseEntity<CreateRoomResponse> create(CreateRoomRequest request) {
        Room room = roomService.createRoom(request);
        return ResponseEntity.ok(toCreateRoomResponseFromRoom(room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateRoomResponse> update(@Valid @PathVariable("id") UUID roomId, UpdateRoomRequest request) {
        Room room = roomService.updateRoom(roomId, request);
        return ResponseEntity.ok(toUpdateRoomResponseFromRoom(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteRoomResponse> delete(@PathVariable(name = "id") UUID roomId) {
        Boolean isDeleted = roomService.deleteRoom(roomId);
        return ResponseEntity.ok(toDeleteRoomResponseFromDeleteRoomRequest(roomId, isDeleted));
    }
}
