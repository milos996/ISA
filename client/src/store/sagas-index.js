import { all, spawn, call } from "redux-saga/effects";
import * as userSaga from "./user/saga";
import * as hotelSaga from "./hotel/saga";
import flatten from "lodash.flatten";

export default function* rootSaga() {
  let sagas = flatten(
    [userSaga, hotelSaga].map(saga =>
      Object.keys(saga).map(sagaFunctionName => saga[sagaFunctionName])
    )
  );

  yield all(
    sagas.map(saga =>
      spawn(function*() {
        while (true) {
          try {
            yield call(saga);
          } catch (e) {
            console.log(e);
          }
        }
      })
    )
  );
}
