import { combineReducers } from "redux";
import userReducer from "./user/reducer/";
import hotelReducer from "./hotel/reducer";
import commonReducer from "./common/reducer";
import airplaneTicketReducer from "./airplane_ticket/reducer";

const rootReducer = combineReducers({
  userReducer,
  hotelReducer,
  commonReducer,
  airplaneTicketReducer
});

export default (state, action) => {
  return rootReducer(state, action);
};
