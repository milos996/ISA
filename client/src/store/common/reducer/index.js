import { PUT_ERROR } from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  error: null
};

const commonReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_ERROR]: computationFunctions.putError
};

export default commonReducer;
