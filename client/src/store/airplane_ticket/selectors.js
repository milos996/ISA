const reducer = "airplaneTicketReducer";

export const selectAirlines = state => state[reducer].airlines;

export const selectSearchResults = state => state[reducer].searchResults;

export const selectSeats = state => state[reducer].selectedSeats;

export const selectSelectedFlight = state => state[reducer].selectedFlight;
