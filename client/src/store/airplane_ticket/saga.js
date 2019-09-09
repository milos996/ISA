import airlineService from "../../services/api/Airline";
import { putAirlines, putSearchResults } from "./actions";
import { take, put, call } from "redux-saga/effects";
import { FETCH_AIRLINES, DO_SEARCH } from "./constants";
import flightService from "../../services/api/Flight";

export function* fetchAirlines() {
  yield take(FETCH_AIRLINES);
  const { data } = yield call(airlineService.fetchAirlines);
  yield put(putAirlines(data));
}

export function* doSearch() {
  const { payload } = yield take(DO_SEARCH);
  const { data } = yield call(flightService.search, payload);
  yield put(putSearchResults(data));
}
