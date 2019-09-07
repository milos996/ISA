import { take, put } from "redux-saga/effects";
import { LOGOUT } from "./constants";
import { putUserData } from "./actions";

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
