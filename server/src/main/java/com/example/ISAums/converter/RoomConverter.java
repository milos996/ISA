package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateRoomRequest;
import com.example.ISAums.dto.response.CreateRoomResponse;
import com.example.ISAums.dto.response.DeleteRoomResponse;
import com.example.ISAums.dto.response.GetRoomResponse;
import com.example.ISAums.dto.response.UpdateRoomResponse;
import com.example.ISAums.model.Hotel;
import com.example.ISAums.model.Room;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoomConverter {
    public static List<GetRoomResponse> toGetRoomsResponseFromListRooms(List<Room> rooms) {
        return rooms.stream()
                .map(room -> GetRoomResponse.builder()
                    .id(room.getId())
                    .floor(room.getFloor())
                    .number(room.getNumber())
                    .numberOfPeople(room.getNumberOfPeople())
                    .priceAutumn(room.getPriceAutumn())
                    .priceSpring(room.getPriceSpring())
                    .priceWinter(room.getPriceWinter())
                    .priceSummer(room.getPriceSummer())
                    .build())
                .collect(Collectors.toList());
    }

    public static CreateRoomResponse toCreateRoomResponseFromRoom(Room room){
        return CreateRoomResponse.builder()
                .id(room.getId())
                .floor(room.getFloor())
                .number(room.getNumber())
                .build();
    }

    public static UpdateRoomResponse toUpdateRoomResponseFromRoom(Room room) {
        return UpdateRoomResponse.builder()
                .id(room.getId())
                .build();
    }

    public static DeleteRoomResponse toDeleteRoomResponseFromDeleteRoomRequest(UUID roomId, Boolean isDeleted) {
        return DeleteRoomResponse.builder()
                .id(roomId)
                .isDeleted(isDeleted)
                .build();
    }

    public static Room toRoomFromRequest(CreateRoomRequest request, Hotel hotel) {
        return Room.builder()
                .floor(request.getFloor())
                .number(request.getNumber())
                .hotel(hotel)
                .numberOfPeople(request.getNumberOfPeople())
                .priceAutumn(request.getAutumnPrice())
                .priceSpring(request.getSpringPrice())
                .priceSummer(request.getSummerPrice())
                .priceWinter(request.getWinterPrice())
                .build();
    }
}
