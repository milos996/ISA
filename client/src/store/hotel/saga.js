import { take, put, call } from "redux-saga/effects";
import {
  SAVE_SERVICES,
  FETCH_HOTEL_SERVICES,
  FETCH_HOTEL_DETAILS,
  SAVE_ROOM_DETAILS,
  DELETE_ROOM,
  GET_HOTEL_LOCATION_ON_LAT_LNG,
  SAVE_HOTEL_DETAILS
} from "./constants";
import {
  putHotelServices,
  putHotelDetails,
  putHotelLocationInformation
} from "./actions";
import hotelServices from "../../services/api/Hotel";
import locationService from "../../services/LocationService";

export function* fetchServices() {
  const { payload } = yield take(FETCH_HOTEL_SERVICES);

  const { data } = yield call(hotelServices.fetchServices, payload.hotelId);

  yield put(putHotelServices(data));
}

export function* saveServices() {
  const { payload } = yield take(SAVE_SERVICES);
  console.log(payload);

  const services = Object.keys(payload.services).map(
    val => payload.services[val]
  );

  yield call(hotelServices.saveServices, payload.hotelId, services);

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

export function* deleteRoom() {
  const { payload } = yield take(DELETE_ROOM);

  const { data } = yield call(hotelServices.deleteRoom, payload.roomId);
}

export function* getHotelLocationOnLatLng() {
  const { payload } = yield take(GET_HOTEL_LOCATION_ON_LAT_LNG);

  const { data } = yield call(
    locationService.getLocationBasedOnLatLong,
    payload
  );

  if (!data.geonames) {
    return;
  }

  yield put(
    putHotelLocationInformation({
      country: data.geonames[0].countryName,
      city: data.geonames[0].name
    })
  );
}
export function* saveHotelDetails() {
  const { payload } = yield take(SAVE_HOTEL_DETAILS);

  const { data } = yield call(hotelServices.saveHotel, payload);
}
