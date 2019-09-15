import { take, put, call } from "redux-saga/effects";
import {
  SAVE_AIRLINE_DETAILS,
  FETCH_AIRLINE_DETAILS,
  FETCH_AIRLINE_DESTINATIONS,
  FETCH_AIRLINE_FLIGHTS,
  FETCH_TICKETS_FOR_FAST_RESERVATION,
  CREATE_FAST_TICKET_RESERVATION,
  SAVE_AIRPLANE_DETAILS,
  FETCH_AIRLINE_AIRPLANES,
  SAVE_FLIGHT,
  FETCH_AIRLINE_RATING
} from "./constants";
import airlineService from "../../services/api/Airline";
import flightService from "../../services/api/Flight";
import airplaneTicketService from "../../services/api/AirplaneTicket";
import airplaneService from "../../services/api/Airplane";
import {
  putAirlineDetails,
  putAirlineDestinations,
  putAirlineFlights,
  putTicketsForFastReservation,
  putAirlineAirplanes,
  putAirlineRating,
  putAirplaneDetails
} from "./actions";

export function* saveAirline() {
  const { payload } = yield take(SAVE_AIRLINE_DETAILS);
  yield call(airlineService.saveAirline, payload);
}

export function* fetchAirlineDetails() {
  const { payload } = yield take(FETCH_AIRLINE_DETAILS);
  const { data } = yield call(
    airlineService.fetchAirlineDetails,
    payload.airlineId
  );
  yield put(putAirlineDetails(data));
}

export function* fetchDestinations() {
  const { payload } = yield take(FETCH_AIRLINE_DESTINATIONS);
  const { data } = yield call(
    flightService.fetchDestinations,
    payload.airlineId
  );
  yield put(putAirlineDestinations(data));
}

export function* fetchFlights() {
  const { payload } = yield take(FETCH_AIRLINE_FLIGHTS);
  const { data } = yield call(flightService.fetchFlights, payload.airlineId);
  yield put(putAirlineFlights(data));
}

export function* fetchTicketsForFastReservation() {
  const { payload } = yield take(FETCH_TICKETS_FOR_FAST_RESERVATION);
  const { data } = yield call(
    flightService.fetchTicketsForFastReservation,
    payload.airlineId
  );
  yield put(putTicketsForFastReservation(data));
}

export function* createFastTicketReservation() {
  const { payload } = yield take(CREATE_FAST_TICKET_RESERVATION);
  yield call(
    airplaneTicketService.createFastTicketReservation,
    payload.flightId
  );
}

export function* saveAirplane() {
  const { payload } = yield take(SAVE_AIRPLANE_DETAILS);
  const { data } = yield call(airplaneService.save, payload);
  yield put(putAirplaneDetails(data));
}

export function* fetchAirlineAirplanes() {
  const { payload } = yield take(FETCH_AIRLINE_AIRPLANES);
  const { data } = yield call(
    airplaneService.fetchAirplanes,
    payload.airlineId
  );
  yield put(putAirlineAirplanes(data));
}

export function* saveFlight() {
  const { payload } = yield take(SAVE_FLIGHT);
  yield call(flightService.save(payload));
}

export function* fetchAirlineRating() {
  const { payload } = yield take(FETCH_AIRLINE_RATING);
  const { data } = yield call(airlineService.getRating, payload);
  yield put(putAirlineRating(data));
}
