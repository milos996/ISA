import airlineService from "../../services/api/Airline";
import {
  putAirlines,
  putSearchResults,
  putSelectedFlight,
  putErrorMessage
} from "./actions";
import { take, put, call } from "redux-saga/effects";
import {
  FETCH_AIRLINES,
  DO_SEARCH,
  MAKE_TICKET_RESERVATION,
  PUT_SELECTED_SEATS,
  SORT_AIRLINES,
  FETCH_FLIGHT
} from "./constants";
import { SEARCH_USERS } from "../../store/user/constants";
import userService from "../../services/api/User";
import flightService from "../../services/api/Flight";
import airplaneTicketService from "../../services/api/AirplaneTicket";
import { putFoundUsersData } from "../user/actions";

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

export function* makeTicketReservation() {
  const { payload } = yield take(MAKE_TICKET_RESERVATION);
  const { data } = yield call(airplaneTicketService.reserve, payload.ticket);
  payload.callback(data.reservationId);
}

export function* searchUsers() {
  const { payload } = yield take(SEARCH_USERS);
  const { data } = yield call(userService.searchByName, payload.name);
  yield put(putFoundUsersData(data));
}

export function* sortAirlines() {
  const { payload } = yield take(SORT_AIRLINES);
  const { data } = yield call(airlineService.sortAirlines, payload);
  yield put(putAirlines(data));
}

export function* fetchFlight() {
  const { payload } = yield take(FETCH_FLIGHT);
  const { data } = yield call(flightService.fetchFlight, payload);
  yield put(putSelectedFlight(data));
}
