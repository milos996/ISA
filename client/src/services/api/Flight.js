import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
    FETCH_DESTINATION: "/flights/flightDestination/%s",
    FETCH_FLIGHTS: "/flights/%s",
    FETCH_TICKETS_FOR_FAST_RESERVATION: "/flights/quickBooking/%s",
    FETCH_DESTINATIONS: "/flights/destinations/%s",
    SAVE_FLIGHT: "/flights"
}

class FlightService extends HttpBaseClient{

    fetchFlights = airlineId => {
        return this.getApiClient().get(format(ENDPOINTS.FETCH_FLIGHTS, airlineId));
    }

    fetchTicketsForFastReservation = airlineId => {
        return this.getApiClient().get(format(ENDPOINTS.FETCH_TICKETS_FOR_FAST_RESERVATION, airlineId));
    }

    fetchDestinations = airlineId => {
        return this.getApiClient().get(format(ENDPOINTS.FETCH_DESTINATIONS, airlineId));
    }

    save = flight => {
        this.getApiClient().post(ENDPOINTS.SAVE_FLIGHT, flight);
    }
}

export default new FlightService();

