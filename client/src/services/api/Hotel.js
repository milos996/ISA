import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  HOTEL_SERVICES: "/hotel-services/%s",
  HOTEL_DETAILS: "/hotels/%s",
  ROOM_DETAILS: "/rooms"
};
class HotelService extends HttpBaseClient {
  fetchServices = hotelId => {
    this.getApiClient().get(format(ENDPOINTS.HOTEL_SERVICES, hotelId));
  };

  changeServices = (services, hotelId) => {
    const formedData = formDataForChangeService(services);
    this.getApiClient().put(
      format(ENDPOINTS.HOTEL_SERVICES, hotelId),
      formedData
    );
  };

  fetchHotelDetails = hotelId => {
    return this.getApiClient().get(format(ENDPOINTS.HOTEL_DETAILS, hotelId));
  };
  saveRoomDetails = roomDetails => {
    return this.getApiClient().put(ENDPOINTS.ROOM_DETAILS, roomDetails);
  };
}

function formDataForChangeService(services) {
  return { services };
}

export default new HotelService();
