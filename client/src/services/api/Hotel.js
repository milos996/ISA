import HttpBaseClient from '../HttpBaseClient';
import { format } from 'util';

const ENDPOINTS = {
  HOTEL_SERVICES: '/hotel-services/%s',
  HOTEL_DETAILS: '/hotels/%s',
  HOTELS: '/hotels/%s',
  FETCH_HOTELS: '/hotels?startDate=%s&endDate=%s&name=%s&city=%s&state=%s',
  HOTEL_ROOMS:
    '/rooms?id=%s&startDate=%s&nights=%d&people=%d&fromPrice=%d&toPrice=%d',
  ROOMS: '/rooms',
  EDIT_ROOM: '/rooms/%s'
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

  saveRoomDetails = (roomId, newData) => {
    return this.getApiClient().put(
      fromat(ENDPOINTS.EDIT_ROOM, roomId),
      newData
    );
  };

  deleteRoom = roomId => {
    return this.getApiClient().delete(format(ENDPOINTS.EDIT_ROOM, roomId));
  };

  saveHotel = (hotelId, newData) => {
    return this.getApiClient().put(fromat(ENDPOINTS.HOTELS, hotelId), newData);
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
    console.log(data);

    return this.getApiClient().post(ENDPOINTS.ROOMS, data);
  };
}

function formDataForChangeService(services) {
  return { services };
}

export default new HotelService();
