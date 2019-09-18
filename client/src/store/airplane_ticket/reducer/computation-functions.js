export function putAirlines(state, payload) {
  return {
    ...state,
    airlines: payload
  };
}

export function putSearchResults(state, payload) {
  return {
    ...state,
    searchResults: payload
  };
}

export function putSelectedSeats(state, payload) {
  var seats = [];
  seats = [...state.selectedSeats, payload];

  return {
    ...state,
    selectedSeats: seats
  };
}
