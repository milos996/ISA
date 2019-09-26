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

export function putIncomeData(state, payload) {
  return {
    ...state,
    income: payload
  };
}

export function putAirlineAdmin(state, payload) {
  return {
    ...state,
    airlineAdmin: payload
  };
}

export function putSoldTicketsData(state, payload) {
  console.log({
    ...state,
    soldTickets: payload
  });
  return {
    ...state,
    soldTickets: payload
  };
}
