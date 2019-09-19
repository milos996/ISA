import {
  PUT_AIRLINE_DETAILS,
  PUT_AIRLINE_LOCATION_INFORMATION,
  SAVE_AIRLINE_DETAILS,
  FETCH_AIRLINE_DETAILS,
  FETCH_AIRLINE_DESTINATIONS,
  PUT_AIRLINE_DESTINATIONS,
  PUT_AIRLINE_FLIGHTS,
  FETCH_AIRLINE_FLIGHTS,
  FETCH_TICKETS_FOR_FAST_RESERVATION,
  PUT_TICKETS_FOR_FAST_RESERVATION,
  CREATE_FAST_TICKET_RESERVATION,
  PUT_AIRPLANE_DETAILS,
  SAVE_AIRPLANE_DETAILS,
  FETCH_AIRLINE_AIRPLANES,
  PUT_AIRLINE_AIRPLANES,
  SAVE_FLIGHT,
  FETCH_AIRLINE_RATING,
  PUT_AIRLINE_RATING,
  RATE_AIRLINE,
  RATE_FLIGHT,
  CANCEL_FLIGHT
} from "./constants";

export const putAirlineDetails = payload => ({
  type: PUT_AIRLINE_DETAILS,
  payload
});

export const putAirlineLocationInformation = payload => ({
  type: PUT_AIRLINE_LOCATION_INFORMATION,
  payload
});

export const saveAirlineDetails = payload => ({
  type: SAVE_AIRLINE_DETAILS,
  payload
});

export const fetchAirlineDetails = payload => ({
  type: FETCH_AIRLINE_DETAILS,
  payload
});

export const fetchAirlineDestinations = payload => ({
  type: FETCH_AIRLINE_DESTINATIONS,
  payload
});

export const putAirlineDestinations = payload => ({
  type: PUT_AIRLINE_DESTINATIONS,
  payload
});

export const putAirlineFlights = payload => ({
  type: PUT_AIRLINE_FLIGHTS,
  payload
});

export const fetchAirlineFlights = payload => ({
  type: FETCH_AIRLINE_FLIGHTS,
  payload
});

export const fetchTicketsForFastReservation = payload => ({
  type: FETCH_TICKETS_FOR_FAST_RESERVATION,
  payload
});

export const putTicketsForFastReservation = payload => ({
  type: PUT_TICKETS_FOR_FAST_RESERVATION,
  payload
});

export const createFastTicketReservation = payload => ({
  type: CREATE_FAST_TICKET_RESERVATION,
  payload
});

export const putAirplaneDetails = payload => ({
  type: PUT_AIRPLANE_DETAILS,
  payload
});

export const saveAirplaneDetails = payload => ({
  type: SAVE_AIRPLANE_DETAILS,
  payload
});

export const fetchAirlineAirplanes = payload => ({
  type: FETCH_AIRLINE_AIRPLANES,
  payload
});

export const putAirlineAirplanes = payload => ({
  type: PUT_AIRLINE_AIRPLANES,
  payload
});

export const saveFlight = payload => ({
  type: SAVE_FLIGHT,
  payload
});

export const fetchAirlineRating = payload => ({
  type: FETCH_AIRLINE_RATING,
  payload
});

export const putAirlineRating = payload => ({
  type: PUT_AIRLINE_RATING,
  payload
});

export const rateAirline = payload => ({
  type: RATE_AIRLINE,
  payload
});

export const rateFlight = payload => ({
  type: RATE_FLIGHT,
  payload
});

export const cancelFlight = payload => ({
  type: CANCEL_FLIGHT,
  payload
});
