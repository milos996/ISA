import { take, put, call } from "redux-saga/effects";
import { LOGOUT,
         SAVE_USER_DATA,
         FETCH_USER_DATA
} from "./constants";
import { putUserData } from "./actions";
import userService from "../../services/api/User";
export function* logout() {
  const { payload } = yield take(LOGOUT);

  // TODO Implement delete from window.storage
  yield put(
    putUserData({
      data: null,
      token: null
    })
  );
  console.log("sad");

  payload.callback();
}

export function* saveUserData(){

  const { payload } = yield take(SAVE_USER_DATA);
  yield call(userService.saveUser, payload); 
}
