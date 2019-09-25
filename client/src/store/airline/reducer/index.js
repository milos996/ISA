import * as computationFunctions from "./computation-functions";
import {
  PUT_AIRLINE_DETAILS,
  PUT_AIRLINE_DESTINATIONS,
  PUT_AIRLINE_FLIGHTS,
  PUT_TICKETS_FOR_FAST_RESERVATION,
  PUT_AIRLINE_AIRPLANES,
  PUT_AIRLINE_RATING,
  PUT_AIRLINE_INCOME,
  PUT_AIRLINE_ADMIN,
  PUT_SOLD_AIRLINE_TICKETS
} from "../constants";

const initialState = {
  data: {
    id: "",
    name: "",
    description: "",
    address: {
      id: "",
      city: "",
      state: "",
      street: "",
      longitude: null,
      latitude: null
    },
    checkingInSuitcasePrice: 0.0,
    handLuggagePrice: 0.0
  },
  destinations: [],
  flights: [],
  ticketsForFastReservation: [],
  airplanes: [],

  rating: {
    airlineId: 0,
    avgRating: 0
  },
  income: [],
  airlineAdmin: {
    airline: {
      id: ""
    }
  },
  soldTickets: []
};

const airlineReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }
  return state;
};
const actionHandler = {
  [PUT_AIRLINE_DETAILS]: computationFunctions.putAirlineDetails,
  [PUT_AIRLINE_DESTINATIONS]: computationFunctions.putAirlineDestinations,
  [PUT_AIRLINE_FLIGHTS]: computationFunctions.putAirlineFlights,
  [PUT_TICKETS_FOR_FAST_RESERVATION]:
    computationFunctions.putTicketsForFastReservation,
  [PUT_AIRLINE_AIRPLANES]: computationFunctions.putAirlineAirplanes,
  [PUT_AIRLINE_RATING]: computationFunctions.putAirlineRating,
  [PUT_AIRLINE_INCOME]: computationFunctions.putIncomeData,
  [PUT_AIRLINE_ADMIN]: computationFunctions.putAirlineAdmin,
  [PUT_SOLD_AIRLINE_TICKETS]: computationFunctions.putSoldTicketsData
};

export default airlineReducer;
