import { take } from "redux-saga/effects";

export function* userSaga() {
  yield take("USER_SAGA_OPTION");
  console.log("user saga izvrsena");
}
