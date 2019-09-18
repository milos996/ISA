export function putAirlineDetails(state, payload) {
  return {
    ...state,
    data: payload
  };
}

export function putAirlineDestinations(state, payload) {
  return {
    ...state,
    destinations: payload
  };
}

export function putAirlineFlights(state, payload) {
  return {
    ...state,
    flights: payload
  };
}

export function putTicketsForFastReservation(state, payload) {
  return {
    ...state,
    ticketsForFastReservation: payload
  };
}

export function putAirlineAirplanes(state, payload) {
  return {
    ...state,
    airplanes: payload
  };
}

export function putAirlineRating(state, payload) {
  return {
    ...state,
    rating: {
      ...state.rating,
      ...payload
    }
  };
}
