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
