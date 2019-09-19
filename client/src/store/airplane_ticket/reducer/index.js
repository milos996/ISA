import {
  PUT_AIRLINES,
  PUT_SEARCH_RESULTS,
  PUT_SELECTED_SEATS
} from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  data: {},
  airlines: [],
  searchResults: [],
  selectedSeats: []
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
  [PUT_SELECTED_SEATS]: computationFunctions.putSelectedSeats
};

export default airplaneTicketReducer;
