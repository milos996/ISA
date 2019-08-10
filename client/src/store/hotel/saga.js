import { take } from "redux-saga/effects";
import { PUT_HOTEL_DETAILS } from "./constants";

export function* nestoSaga() {
  const action = yield take(PUT_HOTEL_DETAILS);
  console.log("izvrsavam se", { action });
  const action2 = yield take(PUT_HOTEL_DETAILS);
  console.log("drugi put se izvrsavam", { action2 });
}
