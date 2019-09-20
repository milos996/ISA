import { take, put, call } from "redux-saga/effects";
import {
  FETCH_RENT_A_CARS,
  CREATE_RENT_A_CAR,
  DELETE_RENT_A_CAR,
  FETCH_RENT_A_CAR_DETAILS,
  FETCH_RENT_A_CAR_VEHICLES,
  FETCH_RENT_A_CAR_OFFICES,
  CREATE_OFFICE,
  DELETE_OFFICE,
  SEARCH_RENT_A_CARS,
  SORT_RENT_A_CARS,
  FETCH_VEHICLES,
  SORT_VEHICLES,
  DELETE_VEHICLE,
  SEARCH_VEHICLES,
  FETCH_RENT_A_CAR_LOCATION_INFORMATION,
  CREATE_VEHICLE,
  CREATE_VEHICLE_RESERVATION,
  CANCEL_VEHICLE_RESERVATION,
  RATE_VEHICLE,
  SAVE_VEHICLE_DETAILS,
  SAVE_RENT_A_CAR_DETAILS,
  RATE_RENT_A_CAR,
  SHOW_RENT_A_CAR_INCOME,
  SHOW_RENT_A_CAR_BUSYNESS
} from "./constants";
import {
  putRentACars,
  putRentACarDetails,
  putRentACarVehicles,
  putRentACarOffices,
  putDeleteOfficeWithId,
  putRentACarLocationInformation,
  putVehicles,
  putCreatedRentACarVehicle,
  putDeleteVehicleWithId,
  putSearchInformation,
  putRentACarIncome,
  putRentACarBusyness
} from "./actions";

import { putUserVehiclesReservation } from "../user/actions";
import rentACarService from "../../services/api/RentACar";
import locationService from "../../services/LocationService";

export function* fetchRentACars() {
  yield take(FETCH_RENT_A_CARS);
  const { data } = yield call(rentACarService.fetchRentACars);
  yield put(putRentACars(data));
}

export function* createRentACAr() {
  const { payload } = yield take(CREATE_RENT_A_CAR);
  const location = yield call(locationService.getLocationBasedOnLatLong, {
    lat: payload.location.latlng.lat,
    lng: payload.location.latlng.lng
  });
  const address = {
    street: payload.street,
    city: location.data.geonames[0].name,
    state: location.data.geonames[0].countryName,
    longitude: payload.location.latlng.lng,
    latitude: payload.location.latlng.lat
  };

  const rentACar = {
    address: address,
    name: payload.name,
    description: payload.description
  };

  yield call(rentACarService.createRentACar, rentACar);
}

export function* updateRentACar() {
  const { payload } = yield take(SAVE_RENT_A_CAR_DETAILS);
  const location = yield call(locationService.getLocationBasedOnLatLong, {
    lat: payload.location.latlng.lat,
    lng: payload.location.latlng.lng
  });
  const address = {
    street: "Slavise Pesica",
    city: location.data.geonames[0].name,
    state: location.data.geonames[0].countryName,
    longitude: payload.location.latlng.lng,
    latitude: payload.location.latlng.lat
  };

  const rentACar = {
    address: address,
    id: payload.id,
    name: payload.name,
    description: payload.description
  };

  const { data } = yield call(rentACarService.updateRentACar, rentACar);

  yield put(putRentACars(data));
}

export function* deleteRentACar() {
  const { payload } = yield take(DELETE_RENT_A_CAR);
  const { data } = yield call(rentACarService.deleteRentACar, payload);

  yield put(putRentACars(data));
}

export function* fetchRentACarDetails() {
  const { payload } = yield take(FETCH_RENT_A_CAR_DETAILS);
  const { data } = yield call(rentACarService.fetchRentACarDetails, payload);
  yield put(putRentACarDetails(data));
}

export function* rateRentACar() {
  const { payload } = yield take(RATE_RENT_A_CAR);
  console.log(payload);
  const { data } = yield call(rentACarService.rateRentACar, payload);
}

export function* showRentACarIncome() {
  const { payload } = yield take(SHOW_RENT_A_CAR_INCOME);

  const { data } = yield call(rentACarService.showRentACarIncome, payload);

  yield put(putRentACarIncome(data));
}

export function* showRentACarBusyness() {
  const { payload } = yield take(SHOW_RENT_A_CAR_BUSYNESS);

  const { data } = yield call(rentACarService.showRentACarBusyness, payload);

  yield put(putRentACarBusyness(data));
}

export function* fetchRentACarLocationInformation() {
  const { payload } = yield take(FETCH_RENT_A_CAR_LOCATION_INFORMATION);
  yield put(putRentACarLocationInformation(payload));
}

export function* fetchRentACarVehicles() {
  const { payload } = yield take(FETCH_RENT_A_CAR_VEHICLES);
  const { data } = yield call(
    rentACarService.fetchRentACarVehicles,
    payload.rentACarId
  );
  yield put(putRentACarVehicles(data));
}

export function* fetchRentACarOffices() {
  const { payload } = yield take(FETCH_RENT_A_CAR_OFFICES);
  const { data } = yield call(
    rentACarService.fetchRentACarOffices,
    payload.rentACarId
  );
  yield put(putRentACarOffices(data));
}

export function* createRentACarOffice() {
  const { payload } = yield take(CREATE_OFFICE);
  const { data } = yield call(rentACarService.createRentACarOffice, payload);
  yield put(putRentACarOffices(data));
}

export function* deleteRentACarOffice() {
  const { payload } = yield take(DELETE_OFFICE);
  const { data } = yield call(rentACarService.deleteOffice, payload);
  yield put(putRentACarOffices(data));
}

export function* searchRentACars() {
  const { payload } = yield take(SEARCH_RENT_A_CARS);
  const { data } = yield call(rentACarService.searchRentACars, payload);
  yield put(putRentACars(data));
}

export function* sortRentACars() {
  const { payload } = yield take(SORT_RENT_A_CARS);
  const { data } = yield call(rentACarService.sortRentACars, payload);
  yield put(putRentACars(data));
}

export function* fetchVehicles() {
  const { payload } = yield take(FETCH_VEHICLES);
  const { data } = yield call(rentACarService.fetchVehicles, payload);
  yield put(putVehicles(data));
}

export function* updateVehicle() {
  const { payload } = yield take(SAVE_VEHICLE_DETAILS);
  const { data } = yield call(rentACarService.updateVehicle, payload);
  yield put(putRentACarVehicles(data));
}

export function* searchVehicles() {
  const { payload } = yield take(SEARCH_VEHICLES);
  const { data } = yield call(rentACarService.searchVehicles, payload);
  yield put(putRentACarVehicles(data));

  const info = {
    pickUpDate: payload.pickUpDate,
    dropOffDate: payload.dropOffDate,
    pickUpLocation: payload.pickUpLocation,
    dropOffLocation: payload.dropOffLocation
  };

  yield put(putSearchInformation(info));
}

export function* sortVehicles() {
  const { payload } = yield take(SORT_VEHICLES);
  console.log(payload);
  const { data } = yield call(rentACarService.sortVehicles, payload);

  yield put(putRentACarVehicles(data));
}

export function* createVehicle() {
  const { payload } = yield take(CREATE_VEHICLE);
  const { data } = yield call(rentACarService.createRentACarVehicle, payload);
  yield put(putRentACarVehicles(data));
}

export function* deleteVehicle() {
  const { payload } = yield take(DELETE_VEHICLE);
  const { data } = yield call(rentACarService.deleteVehicle, payload);
  yield put(putRentACarVehicles(data));
}

export function* rateVehicle() {
  const { payload } = yield take(RATE_VEHICLE);
  console.log(payload);

  const { data } = yield call(rentACarService.rateVehicle, payload);
}

export function* createVehicleReservation() {
  const { payload } = yield take(CREATE_VEHICLE_RESERVATION);
  const { data } = yield call(rentACarService.reserveVehicle, payload);
}

export function* cancelVehicleReservation() {
  const { payload } = yield take(CANCEL_VEHICLE_RESERVATION);
  const { data } = yield call(
    rentACarService.cancelVehicleReservation,
    payload
  );

  yield put(putUserVehiclesReservation(data));
}
