import { all, spawn, call, put } from "redux-saga/effects";
import flatten from "lodash.flatten";
import * as userSaga from "./user/saga";
import * as hotelSaga from "./hotel/saga";
import * as airplaneTicketSaga from "./airplane_ticket/saga";
import { putError } from "./common/actions";

export default function* rootSaga() {
  let sagas = flatten(
    [userSaga, hotelSaga, airplaneTicketSaga].map(saga =>
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
            // TODO: Uncomment when finished with application
            // yield put(putError(e.message));
          }
        }
      })
    )
  );
}
