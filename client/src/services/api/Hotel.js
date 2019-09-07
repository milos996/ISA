import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  HOTEL_SERVICES: "/hotel-services/%s",
  HOTEL_DETAILS: "/hotels/%s",
  SAVE_HOTEL: "/hotels",
  ROOM_DETAILS: "/rooms",
  DELETE_ROOM: "/rooms/%s"
};
class HotelService extends HttpBaseClient {
  fetchServices = hotelId => {
    this.getApiClient().get(format(ENDPOINTS.HOTEL_SERVICES, hotelId));
  };

  saveServices = (hotelId, services) => {
    this.getApiClient().put(
      format(ENDPOINTS.HOTEL_SERVICES, hotelId),
      services
    );
  };

  fetchHotelDetails = hotelId => {
    return this.getApiClient().get(format(ENDPOINTS.HOTEL_DETAILS, hotelId));
  };
  saveRoomDetails = roomDetails => {
    return this.getApiClient().put(ENDPOINTS.ROOM_DETAILS, roomDetails);
  };

  deleteRoom = roomId => {
    return this.getApiClient().delete(format(ENDPOINTS.DELETE_ROOM, roomId));
  };

  saveHotel = hotelDetails => {
    return this.getApiClient().put(ENDPOINTS.SAVE_HOTEL, hotelDetails);
  };
}

function formDataForChangeService(services) {
  return { services };
}

export default new HotelService();
