
const reducer = "airlineReducer";

export const selectAirlineDetails = state => state[reducer].data;

export const selectDestinations = state => state[reducer].destinations;

export const selectFlights = state => state[reducer].flights;

export const selectTicketForFastReservation = state => state[reducer].ticketsForFastReservation;

export const selectAirlineAirplanes = state => state[reducer].airplanes;

export const selectAirlineRating = state => state[reducer].rating;
