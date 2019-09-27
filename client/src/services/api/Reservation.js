import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  USER_VEHICLES_RESERVATION: "/vehicle-reservations/user/",
  USER_HOTELS_RESERVATION: "/hotel-reservations/user/",
  USER_FLIGHTS_RESERVATION: "/tickets/user/",
  CANCEL_FLIGHT_RESERVATION: "/tickets/%s",
  CANCEL_HOTEL_RESERVATION: "/hotel-reservations/%s",
  CANCEL_VEHICLE_RESERVATION: "/vehicle-reservations/%s"
};

class ReservationService extends HttpBaseClient {
  fetchUserVehiclesReservation = () => {
    return this.getApiClient().get(ENDPOINTS.USER_VEHICLES_RESERVATION);
  };

  fetchUserFlightsReservation = () => {
    return this.getApiClient().get(ENDPOINTS.USER_FLIGHTS_RESERVATION);
  };

  fetchUserHotelsReservation = () => {
    return this.getApiClient().get(ENDPOINTS.USER_HOTELS_RESERVATION);
  };

  cancelVehicleReservation = reservationId => {
    return this.getApiClient().delete(ENDPOINTS.CANCEL_VEHICLE_RESERVATION);
  };

  cancelFlightReservation = reservationId => {
    return this.getApiClient().delete(
      format(ENDPOINTS.CANCEL_FLIGHT_RESERVATION, reservationId)
    );
  };

  cancelHotelReservation = reservationId => {
    return this.getApiClient().delete(
      format(ENDPOINTS.CANCEL_HOTEL_RESERVATION, reservationId)
    );
  };
}

export default new ReservationService();
