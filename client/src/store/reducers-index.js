import { combineReducers } from "redux";
import userReducer from "./user/reducer/";
import hotelReducer from "./hotel/reducer";
import commonReducer from "./common/reducer";
import airplaneTicketReducer from "./airplane_ticket/reducer";
import rentACarReducer from "./rent-a-car/reducer";
import airlineReducer from "./airline/reducer";

const rootReducer = combineReducers({
  userReducer,
  hotelReducer,
  rentACarReducer,
  commonReducer,
  airplaneTicketReducer,
  airlineReducer
});

export default (state, action) => {
  return rootReducer(state, action);
};
