import { combineReducers } from "redux";
import userReducer from "./user/reducer/";
import hotelReducer from "./hotel/reducer";

const rootReducer = combineReducers({ userReducer, hotelReducer });

export default (state, action) => {
  return rootReducer(state, action);
};
