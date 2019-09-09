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
  SAVE_HOTEL_DETAILS,
  PUT_DELETE_ROOM_WITH_ID,
  FETCH_HOTELS,
  PUT_HOTELS,
  FETCH_HOTEL_ROOMS,
  PUT_HOTEL_ROOMS,
  RESERVE_ROOMS,
  SEARCH_HOTEL_BASED_ON_FILTERS
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

export const putDeleteRoomWithId = payload => ({
  type: PUT_DELETE_ROOM_WITH_ID,
  payload
});

export const fetchHotels = payload => ({
  type: FETCH_HOTELS,
  payload
});
export const putHotels = payload => ({
  type: PUT_HOTELS,
  payload
});

export const fetchHotelRooms = payload => ({
  type: FETCH_HOTEL_ROOMS,
  payload
});

export const putHotelRooms = payload => ({
  type: PUT_HOTEL_ROOMS,
  payload
});

export const reserveRooms = payload => ({
  type: RESERVE_ROOMS,
  payload
});

export const searchHotelsBasedOnFilters = payload => ({
  type: SEARCH_HOTEL_BASED_ON_FILTERS,
  payload
});
