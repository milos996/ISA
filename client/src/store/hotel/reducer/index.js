import {
  PUT_HOTEL_DETAILS,
  PUT_CHANGE_HOTEL_SERVICES,
  PUT_ADD_NEW_SERVICE,
  PUT_HOTEL_SERVICES,
  PUT_HOTEL_LOCATION_INFORMATION,
  PUT_DELETE_ROOM_WITH_ID,
  PUT_HOTELS,
  PUT_HOTEL_ROOMS,
  PUT_ROOM_DETAILS_CHANGE,
  PUT_NEW_ROOM
} from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  data: {},
  report: {},
  services: {},
  hotels: [],
  rooms: []
};

const hotelReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_HOTEL_DETAILS]: computationFunctions.putHotelDetails,
  [PUT_CHANGE_HOTEL_SERVICES]: computationFunctions.changeHotelServices,
  [PUT_ADD_NEW_SERVICE]: computationFunctions.addNewService,
  [PUT_HOTEL_SERVICES]: computationFunctions.setServices,
  [PUT_HOTEL_LOCATION_INFORMATION]:
    computationFunctions.putHotelLocationInformation,
  [PUT_DELETE_ROOM_WITH_ID]: computationFunctions.deleteRoomWithId,
  [PUT_HOTELS]: computationFunctions.putHotels,
  [PUT_HOTEL_ROOMS]: computationFunctions.putHotelRooms,
  [PUT_ROOM_DETAILS_CHANGE]: computationFunctions.putRoomDetailsChange,
  [PUT_NEW_ROOM]: computationFunctions.putNewRoom
};

export default hotelReducer;
