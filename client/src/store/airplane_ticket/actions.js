import {
  FETCH_AIRLINES,
  PUT_AIRLINES,
  DO_SEARCH,
  PUT_SEARCH_RESULTS
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
