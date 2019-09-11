import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  HOTEL_SERVICES: "/hotel-services/%s",
  HOTEL_DETAILS: "/hotels/%s",
  HOTELS: "/hotels/%s",
  FETCH_HOTELS: "/hotels?startDate=%s&endDate=%s&name=%s&city=%s&state=%s",
  HOTEL_ROOMS:
    "/rooms?id=%s&startDate=%s&nights=%d&people=%d&fromPrice=%d&toPrice=%d",
  ROOMS: "/rooms",
  EDIT_ROOM: "/rooms/%s",
  DELETE_ROOM: "/rooms/%s",
  HOTELS_WITHOUT_ADMIN: "/hotels/no/admin"
};
class HotelService extends HttpBaseClient {
  fetchServices = hotelId => {
    return this.getApiClient().get(format(ENDPOINTS.HOTEL_SERVICES, hotelId));
  };

  saveServices = (hotelId, services) => {
    return this.getApiClient().put(
      format(ENDPOINTS.HOTEL_SERVICES, hotelId),
      services
    );
  };

  fetchHotelDetails = hotelId => {
    return this.getApiClient().get(format(ENDPOINTS.HOTEL_DETAILS, hotelId));
  };

  saveRoomDetails = (roomId, newData) => {
    return this.getApiClient().put(
      format(ENDPOINTS.EDIT_ROOM, roomId),
      newData
    );
  };

  deleteRoom = roomId => {
    return this.getApiClient().delete(format(ENDPOINTS.EDIT_ROOM, roomId));
  };

  saveHotel = (hotelId, newData) => {
    console.log(hotelId, newData);

    return this.getApiClient().put(format(ENDPOINTS.HOTELS, hotelId), newData);
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
    date = null,
    numberOfNights = 0,
    numberOfPeople = 0,
    price = {
      min: 0.0,
      max: 0.0
    }
  }) => {
    return this.getApiClient().get(
      format(
        ENDPOINTS.HOTEL_ROOMS,
        hotelId,
        date && date.toISOString(),
        numberOfNights,
        numberOfPeople,
        price.min,
        price.max
      )
    );
  };

  reserve = data => {
    console.log(data);

    return this.getApiClient().post(ENDPOINTS.ROOMS, data);
  };

  fetchHotelsWithoutAdmin = () => {
    return this.getApiClient().get(ENDPOINTS.HOTELS_WITHOUT_ADMIN);
  };
}

function formDataForChangeService(services) {
  return { services };
}

export default new HotelService();
