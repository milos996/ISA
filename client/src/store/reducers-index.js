import { combineReducers } from "redux";
import userReducer from "./user/reducer/";
import hotelReducer from "./hotel/reducer";
import commonReducer from "./common/reducer";
import airlineReducer from "./airline/reducer";

const rootReducer = combineReducers({
  userReducer,
  hotelReducer,
  commonReducer,
  airlineReducer
});

export default (state, action) => {
  return rootReducer(state, action);
};
