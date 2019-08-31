import {
  PUT_HOTEL_DETAILS,
  PUT_CHANGE_HOTEL_SERVICES,
  PUT_ADD_NEW_SERVICE,
  SAVE_SERVICES,
  PUT_HOTEL_SERVICES,
  FETCH_HOTEL_SERVICES,
  FETCH_HOTEL_DETAILS,
  SAVE_ROOM_DETAILS,
  DELETE_ROOM,
  GET_HOTEL_LOCATION_ON_LAT_LNG,
  PUT_HOTEL_LOCATION_INFORMATION,
  SAVE_HOTEL_DETAILS
} from "./constants";

export const putHotelDetails = payload => ({
  type: PUT_HOTEL_DETAILS,
  payload
});

export const putChangeHotelServices = payload => ({
  type: PUT_CHANGE_HOTEL_SERVICES,
  payload
});

export const putAddNewService = payload => ({
  type: PUT_ADD_NEW_SERVICE,
  payload
});

export const saveServices = payload => ({
  type: SAVE_SERVICES,
  payload
});

export const putHotelServices = payload => ({
  type: PUT_HOTEL_SERVICES,
  payload
});

export const fetchHotelService = payload => ({
  type: FETCH_HOTEL_SERVICES,
  payload
});

export const fetchHotelDetails = payload => ({
  type: FETCH_HOTEL_DETAILS,
  payload
});

export const saveRoomDetails = payload => ({
  type: SAVE_ROOM_DETAILS,
  payload
});

export const deleteRoom = payload => ({
  type: DELETE_ROOM,
  payload
});

export const getHotelLocationOnLatLng = payload => ({
  type: GET_HOTEL_LOCATION_ON_LAT_LNG,
  payload
});

export const putHotelLocationInformation = payload => ({
  type: PUT_HOTEL_LOCATION_INFORMATION,
  payload
});

export const saveHotelDetails = payload => ({
  type: SAVE_HOTEL_DETAILS,
  payload
});
