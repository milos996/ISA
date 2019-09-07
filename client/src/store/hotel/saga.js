import { take, put, call } from "redux-saga/effects";
import {
  SAVE_SERVICES,
  FETCH_HOTEL_SERVICES,
  FETCH_HOTEL_DETAILS,
  SAVE_ROOM_DETAILS,
  DELETE_ROOM,
  GET_HOTEL_LOCATION_ON_LAT_LNG,
  SAVE_HOTEL_DETAILS,
  FETCH_HOTELS,
  FETCH_HOTEL_ROOMS,
  RESERVE_ROOMS,
  SEARCH_HOTEL_BASED_ON_FILTERS
} from "./constants";
import {
  putHotelServices,
  putHotelDetails,
  putHotelLocationInformation,
  putDeleteRoomWithId,
  putHotels,
  putHotelRooms
} from "./actions";
import hotelServices from "../../services/api/Hotel";
import locationService from "../../services/LocationService";

export function* fetchServices() {
  const { payload } = yield take(FETCH_HOTEL_SERVICES);

  // TODO pozovi BE i setuje data umjesto mokovanih podataka
  // const { data } = yield call(hotelServices.fetchServices, payload.hotelId);

  yield put(putHotelServices(MOCK_HOTEL_SERVICES));
}

export function* saveServices() {
  const { payload } = yield take(SAVE_SERVICES);

  const services = Object.keys(payload.services).map(
    val => payload.services[val]
  );

  yield call(hotelServices.saveServices, payload.hotelId, services);

  payload.callback();
}

export function* fetchHotelDetails() {
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

  yield call(hotelServices.deleteRoom, payload.roomId);

  yield put(putDeleteRoomWithId(payload.roomId));
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

  yield call(hotelServices.saveHotel, payload);
}

export function* fetchHotels() {
  yield take(FETCH_HOTELS);

  const { data } = yield call(hotelServices.fetchHotels, {});

  yield put(putHotels(data));
}

export function* fetchHotelRooms() {
  const { payload } = yield take(FETCH_HOTEL_ROOMS);

  // TODO Odkomentarisi kada server bude radio i promjeni MOCK_ROOMS sa data
  // const { data } = yield call(hotelServices.fetchHotelRooms, payload);

  yield put(putHotelRooms(MOCK_ROOMS));
}

export function* reserveRooms() {
  const { payload } = yield take(RESERVE_ROOMS);

  yield call(hotelServices.reserve(payload));
}

export function* searchHotelsBasedOnFilters() {
  const { payload } = yield take(SEARCH_HOTEL_BASED_ON_FILTERS);

  const { data } = yield hotelServices.fetchHotels(payload);

  yield put(putHotels(data));
}

const MOCK_ROOMS = [
  {
    id: 0,
    number: 2,
    floor: 1,
    price: 12,
    priceSummer: 12.4,
    priceWinter: 123.2,
    priceAutumn: 59.12,
    priceSpring: 12,
    numberOfPeople: 2
  },
  {
    id: 2,
    number: 3,
    floor: 1,
    price: 1,
    priceSummer: 12.4,
    priceWinter: 123.2,
    priceAutumn: 59.12,
    priceSpring: 12,
    numberOfPeople: 2
  },
  {
    id: 3,
    number: 4,
    floor: 1,
    price: 12,
    priceSummer: 12.4,
    priceWinter: 123.2,
    priceAutumn: 59.12,
    priceSpring: 12,
    numberOfPeople: 1
  },
  {
    id: 4,
    number: 2,
    floor: 2,
    price: 1,
    priceSummer: 12.4,
    priceWinter: 123.2,
    priceAutumn: 59.12,
    priceSpring: 12,
    numberOfPeople: 3
  },
  {
    id: 5,
    number: 3,
    floor: 2,
    price: 4,
    priceSummer: 12.4,
    priceWinter: 123.2,
    priceAutumn: 59.12,
    priceSpring: 12,
    numberOfPeople: 56
  },
  {
    id: 123,
    number: 10,
    floor: 2,
    price: 1,
    priceSummer: 12.4,
    priceWinter: 123.2,
    priceAutumn: 59.12,
    priceSpring: 12,
    numberOfPeople: 1
  }
];

const MOCK_HOTEL_SERVICES = [
  { id: 122, name: "air-conditioning", price: 0.56, selected: false },
  {
    id: 124,
    name: "air-conditioning",
    price: 23,
    selected: true
  },
  {
    id: 125,
    name: "air-conditioning",
    price: 12,
    selected: true
  },
  {
    id: 128,
    name: "air-conditioning",
    price: 4,
    selected: true
  },
  {
    id: 123,
    name: "wi-fi",
    price: 0.9,
    selected: true
  }
];
