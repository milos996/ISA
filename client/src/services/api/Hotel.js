import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  HOTEL_SERVICES: "/hotel-services/%s",
  HOTEL_DETAILS: "/hotels/%s",
  HOTELS: "/hotels",
  FETCH_HOTELS: "/hotels?startDate=%s&endDate=%s&name=%s&city=%s&state=%s",
  HOTEL_ROOMS:
    "/hotels/%s/rooms?date=%s&numberOfNights=%d&numberOfPeople=%d&fromPrice=%d&toPrice=%d",
  ROOMS: "/rooms",
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
    return this.getApiClient().put(ENDPOINTS.ROOMS, roomDetails);
  };

  deleteRoom = roomId => {
    return this.getApiClient().delete(format(ENDPOINTS.DELETE_ROOM, roomId));
  };

  saveHotel = hotelDetails => {
    return this.getApiClient().put(ENDPOINTS.HOTELS, hotelDetails);
  };

  fetchHotels = ({
    startDate = null,
    endDate = null,
    hotelName = null,
    city = null,
    country = null
  }) => {
    return this.getApiClient().get(
      format(
        ENDPOINTS.FETCH_HOTELS,
        startDate && startDate.toISOString(),
        endDate && endDate.toISOString(),
        hotelName,
        city,
        country
      )
    );
  };

  fetchHotelRooms = ({
    hotelId,
    date,
    numberOfNights,
    numberOfPeople,
    price
  }) => {
    return this.getApiClient().get(
      format(
        ENDPOINTS.HOTEL_ROOMS,
        hotelId,
        date,
        numberOfNights,
        numberOfPeople,
        price.min,
        price.max
      )
    );
  };

  reserve = data => {
    return this.getApiClient().post(ENDPOINTS.ROOMS, data);
  };
}

function formDataForChangeService(services) {
  return { services };
}

export default new HotelService();
