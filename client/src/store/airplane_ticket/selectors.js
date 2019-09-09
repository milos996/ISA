const reducer = "airplaneTicketReducer";

export const selectAirlines = state => state[reducer].airlines;

export const selectSearchResults = state => state[reducer].searchResults;
