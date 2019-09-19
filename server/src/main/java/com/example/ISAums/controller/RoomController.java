package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRoomRequest;
import com.example.ISAums.dto.request.UpdateRoomRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Room;
import com.example.ISAums.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import javax.validation.Valid;
import java.time.*;
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

    @GetMapping
    public ResponseEntity<List<GetRoomResponse>> get(@Valid @RequestParam("id") UUID hotelId,
                                                                   @Valid @Nullable @RequestParam(value = "startDate", required = false) String startDate,
                                                                   @Valid @Nullable @RequestParam(value = "nights", required = false) Integer nights,
                                                                   @Valid @Nullable @RequestParam(value = "people", required = false) Integer people,
                                                                   @Valid @Nullable @RequestParam(value = "fromPrice", required = false) Double fromPrice,
                                                                    @Valid @Nullable @RequestParam(value = "toPrice", required = false) Double toPrice) {

        Instant instant = startDate.equals("null") ? null : Instant.parse(startDate);
        LocalDate start = startDate.equals("null") ? null : LocalDate.from(LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId())));

        List<Room> rooms = roomService.get(hotelId, start, nights, people, fromPrice, toPrice);
        return ResponseEntity.ok(toGetRoomsResponseFromListRooms(rooms));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('HOTEL_ADMIN')")
    public ResponseEntity<CreateRoomResponse> create(@AuthenticationPrincipal UUID userId, @RequestBody CreateRoomRequest request) {
        Room room = roomService.createRoom(request, userId);
        return ResponseEntity.ok(toCreateRoomResponseFromRoom(room));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('HOTEL_ADMIN')")
    public ResponseEntity<UpdateRoomResponse> update(@Valid @PathVariable("id") UUID roomId, @RequestBody  UpdateRoomRequest request) {
        Room room = roomService.updateRoom(roomId, request);
        return ResponseEntity.ok(toUpdateRoomResponseFromRoom(room));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('HOTEL_ADMIN')")
    public ResponseEntity<DeleteRoomResponse> delete(@PathVariable(name = "id") UUID roomId) {
        Boolean isDeleted = roomService.deleteRoom(roomId);
        return ResponseEntity.ok(toDeleteRoomResponseFromDeleteRoomRequest(roomId, isDeleted));
    }
}
