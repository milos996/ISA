import { PUT_USER_DATA } from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  data: null,
  token: null,
  service: null
};

const userReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_USER_DATA]: computationFunctions.putUserData
};

export default userReducer;
