import {
  FETCH_AIRLINES,
  PUT_AIRLINES,
  DO_SEARCH,
  PUT_SEARCH_RESULTS,
  MAKE_TICKET_RESERVATION,
  PUT_SELECTED_SEATS
} from "./constants";

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
