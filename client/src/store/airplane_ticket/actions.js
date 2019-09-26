import {
  FETCH_AIRLINES,
  PUT_AIRLINES,
  DO_SEARCH,
  PUT_SEARCH_RESULTS,
  MAKE_TICKET_RESERVATION,
  PUT_SELECTED_SEATS,
  SORT_AIRLINES,
  FETCH_FLIGHT,
  PUT_SELECTED_FLIGHT
} from "./constants";

export const putSelectedFlight = payload => ({
  type: PUT_SELECTED_FLIGHT,
  payload
});

export const fetchAirlines = payload => ({
  type: FETCH_AIRLINES,
  payload
});

export const putAirlines = payload => ({
  type: PUT_AIRLINES,
  payload
});

export const doSearch = payload => ({
  type: DO_SEARCH,
  payload
});

export const putSearchResults = payload => ({
  type: PUT_SEARCH_RESULTS,
  payload
});

export const makeTicketReservation = payload => ({
  type: MAKE_TICKET_RESERVATION,
  payload
});

export const putSelectedSeats = payload => ({
  type: PUT_SELECTED_SEATS,
  payload
});

export const sortAirlines = payload => ({
  type: SORT_AIRLINES,
  payload
});

export const fetchFlight = payload => ({
  type: FETCH_FLIGHT,
  payload
});
