import { combineReducers } from "redux";
import userReducer from "./user/reducer/";
import hotelReducer from "./hotel/reducer";
import commonReducer from "./common/reducer";
import rentACarReducer from "./rent-a-car/reducer";

const rootReducer = combineReducers({
  userReducer,
  hotelReducer,
  rentACarReducer,
  commonReducer
});

export default (state, action) => {
  return rootReducer(state, action);
};
