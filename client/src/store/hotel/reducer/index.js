import { PUT_HOTEL_DETAILS } from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  data: null
};

const hotelReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_HOTEL_DETAILS]: computationFunctions.putHotelDetails
};

export default hotelReducer;
