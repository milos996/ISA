import { take, put, call } from "redux-saga/effects";
import {
  SAVE_SERVICES,
  FETCH_HOTEL_SERVICES,
  FETCH_HOTEL_DETAILS,
  SAVE_ROOM_DETAILS
} from "./constants";
import { putHotelServices, putHotelDetails } from "./actions";
import hotelServices from "../../services/api/Hotel";

export function* fetchServices() {
  const { payload } = yield take(FETCH_HOTEL_SERVICES);

  const { data } = yield call(hotelServices.fetchServices, payload.hotelId);

  yield put(putHotelServices(data));
}

export function* saveServices() {
  const { payload } = yield take(SAVE_SERVICES);
  payload.callback();
}

export function* fetchHotelServices() {
  const { payload } = yield take(FETCH_HOTEL_DETAILS);

  const { data } = yield call(hotelServices.fetchHotelDetails, payload.hotelId);

  yield put(putHotelDetails({ data }));
}

export function* saveRoomDetails() {
  const { payload } = yield take(SAVE_ROOM_DETAILS);

  const { data } = yield call(
    hotelServices.saveRoomDetails,
    payload.roomDetails
  );

  yield put(putHotelDetails({ data }));
}
