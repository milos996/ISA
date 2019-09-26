import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  FETCH_DESTINATION: "/flights/flightDestination/%s",
  FETCH_FLIGHTS: "/flights/%s",
  FETCH_TICKETS_FOR_FAST_RESERVATION: "/flights/quickBooking/%s",
  FETCH_DESTINATIONS: "/flights/destinations/%s",
  SAVE_FLIGHT: "/flights",
  RATE_FLIGHT: "/flights/rating",
  SEARCH:
    "/flights/search/fromDestinationCity=%s&toDestinationCity=%s&departureDate=%s&arrivalDate=%s",
  FETCH_FLIGHT: "/flights/flight/%s"
};

class FlightService extends HttpBaseClient {
  fetchFlights = airlineId => {
    return this.getApiClient().get(format(ENDPOINTS.FETCH_FLIGHTS, airlineId));
  };

  fetchTicketsForFastReservation = airlineId => {
    return this.getApiClient().get(
      format(ENDPOINTS.FETCH_TICKETS_FOR_FAST_RESERVATION, airlineId)
    );
  };

  fetchDestinations = airlineId => {
    return this.getApiClient().get(
      format(ENDPOINTS.FETCH_DESTINATIONS, airlineId)
    );
  };

  save = flight => {
    this.getApiClient().post(ENDPOINTS.SAVE_FLIGHT, flight);
  };

  rateFlight = rateData => {
    return this.getApiClient().post(ENDPOINTS.RATE_FLIGHT, rateData);
  };

  search = data => {
    return this.getApiClient().get(
      format(
        ENDPOINTS.SEARCH,
        data.fromDestination,
        data.toDestination,
        data.departureDate,
        data.arrivalDate
      )
    );
  };

  fetchFlight = id => {
    return this.getApiClient().get(format(ENDPOINTS.FETCH_FLIGHT, id));
  };
}

export default new FlightService();
