import {
  PUT_AIRLINES,
  PUT_SEARCH_RESULTS,
  PUT_SELECTED_SEATS,
  PUT_SELECTED_FLIGHT,
  CLEAR_SELECTED_SEATS
} from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  data: {},
  airlines: [],
  searchResults: [],
  selectedSeats: [],
  selectedFlight: {
    id: "",
    departureTime: "",
    arrivalTime: "",
    duration: "",
    length: 0,
    price: 0,
    airlineDestination: {},
    airplane: {}
  }
};

const airplaneTicketReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_AIRLINES]: computationFunctions.putAirlines,
  [PUT_SEARCH_RESULTS]: computationFunctions.putSearchResults,
  [PUT_SELECTED_SEATS]: computationFunctions.putSelectedSeats,
  [PUT_SELECTED_FLIGHT]: computationFunctions.putSelectedFlight,
  [CLEAR_SELECTED_SEATS]: computationFunctions.clearSelectedSeats
};

export default airplaneTicketReducer;
