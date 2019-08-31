import { combineReducers } from "redux";
import userReducer from "./user/reducer/";
import hotelReducer from "./hotel/reducer";
import commonReducer from "./common/reducer";

const rootReducer = combineReducers({
  userReducer,
  hotelReducer,
  commonReducer
});

export default (state, action) => {
  return rootReducer(state, action);
};
