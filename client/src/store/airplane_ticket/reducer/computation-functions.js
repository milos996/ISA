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

export function clearSelectedSeats(state, payload) {
  console.log({
    ...state,
    selectedSeats: payload
  });
  return {
    ...state,
    selectedSeats: payload
  };
}

export function putSelectedFlight(state, payload) {
  return {
    ...state,
    selectedFlight: payload
  };
}
