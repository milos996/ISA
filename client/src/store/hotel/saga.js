import { take, put, call } from 'redux-saga/effects';
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
  SEARCH_HOTEL_BASED_ON_FILTERS,
  FETCH_HOTELS_WITHOUT_ADMIN,
  FETCH_HOTEL_SERVICE_AND_SERVICES,
  ADD_NEW_ROOM,
  RATE_HOTEL,
  RATE_ROOM,
  SORT_HOTELS
} from './constants';
import {
  putHotelServices,
  putHotelDetails,
  putHotelLocationInformation,
  putDeleteRoomWithId,
  putHotels,
  putHotelRooms,
  putRoomDetailsChange,
  putNewRoom
} from './actions';
import hotelServices from '../../services/api/Hotel';
import locationService from '../../services/LocationService';

export function* fetchServices() {
  const { payload } = yield take(FETCH_HOTEL_SERVICES);

  const { data } = yield call(hotelServices.fetchServices, payload.hotelId);

  yield put(putHotelServices(data.services));
}

export function* saveServices() {
  const { payload } = yield take(SAVE_SERVICES);

  const services = Object.keys(payload.services).map(val => {
    const service = payload.services[val];
    const { selected, ...restServiceData } = service;
    return { ...restServiceData };
  });

  yield call(hotelServices.saveServices, payload.hotelId, services);

  payload.callback();
}

export function* fetchHotelDetails() {
  const { payload } = yield take(FETCH_HOTEL_DETAILS);

  const { data } = yield call(hotelServices.fetchHotelDetails, payload.hotelId);

  yield put(putHotelDetails(data));
}

export function* saveRoomDetails() {
  const { payload } = yield take(SAVE_ROOM_DETAILS);
  const { id, ...restData } = payload.roomDetails;

  yield call(hotelServices.saveRoomDetails, id, restData);

  yield put(putRoomDetailsChange({ id, data: restData }));
}

export function* deleteRoom() {
  const { payload } = yield take(DELETE_ROOM);

  yield call(hotelServices.deleteRoom, payload.roomId);

  yield put(putDeleteRoomWithId(payload.roomId));
}

export function* getHotelLocationOnLatLng() {
  const { payload } = yield take(GET_HOTEL_LOCATION_ON_LAT_LNG);
  console.log(payload);

  const { data } = yield call(
    locationService.getLocationBasedOnLatLong,
    payload
  );

  if (!data.geonames) {
    return;
  }

  yield put(
    putHotelLocationInformation({
      state: data.geonames[0].countryName,
      city: data.geonames[0].name,
      longitude: payload.lng,
      latitude: payload.lat
    })
  );
}

export function* saveHotelDetails() {
  const { payload } = yield take(SAVE_HOTEL_DETAILS);

  const { id, ...data } = payload;

  yield call(hotelServices.saveHotel, id, data);
}

export function* fetchHotels() {
  yield take(FETCH_HOTELS);

  const { data } = yield call(hotelServices.fetchHotels, {});

  yield put(putHotels(data));
}

export function* fetchHotelRooms() {
  const { payload } = yield take(FETCH_HOTEL_ROOMS);

  const { data } = yield call(hotelServices.fetchHotelRooms, payload);

  yield put(putHotelRooms(data));
}

export function* reserveRooms() {
  // to be checked
  const { payload } = yield take(RESERVE_ROOMS);
  const { callback, ...restData } = payload;
  yield call(hotelServices.reserve, restData);

  callback();
}

export function* searchHotelsBasedOnFilters() {
  const { payload } = yield take(SEARCH_HOTEL_BASED_ON_FILTERS);

  const { data } = yield call(hotelServices.fetchHotels, payload);

  yield put(putHotels(data));
}

export function* fetchHotelsWithoutAdmin() {
  //to be checked
  yield take(FETCH_HOTELS_WITHOUT_ADMIN);

  const { data } = yield call(hotelServices.fetchHotelsWithoutAdmin);

  yield put(putHotels(data));
}

export function* fetchHotelServiceAndServices() {
  const { payload } = yield take(FETCH_HOTEL_SERVICE_AND_SERVICES);

  const { data } = yield call(
    hotelServices.fetchHotelServiceAndServices,
    payload.hotelId
  );

  yield put(putHotelServices(data));
}

export function* addNewRoom() {
  const { payload } = yield take(ADD_NEW_ROOM);

  const { data } = yield call(hotelServices.addNewRoom, payload);

  yield put(putNewRoom({ ...payload, id: data.id }));
}

export function* rateRoom() {
  const { payload } = yield take(RATE_ROOM);

  const { data } = yield call(hotelServices.rateRoom, payload);
}

export function* rateHotel() {
  const { payload } = yield take(RATE_HOTEL);

  const { data } = yield call(hotelServices.rateHotel, payload);
}

export function* sortHotels() {
  const { payload } = yield take(SORT_HOTELS);

  const { data } = yield call(hotelServices.sortHotels, payload);

  yield put(putHotels(data));
}

const MOCK_ROOMS = [
  {
    id: 0,
    number: 2,
    floor: 1,
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
    priceSummer: 12.4,
    priceWinter: 123.2,
    priceAutumn: 59.12,
    priceSpring: 12,
    numberOfPeople: 1
  }
];

const MOCK_HOTEL_SERVICES = [
  { id: 122, name: 'air-conditioning', price: 0.56, selected: false },
  {
    id: 124,
    name: 'air-conditioning',
    price: 23,
    selected: true
  },
  {
    id: 125,
    name: 'air-conditioning',
    price: 12,
    selected: true
  },
  {
    id: 128,
    name: 'air-conditioning',
    price: 4,
    selected: true
  },
  {
    id: 123,
    name: 'wi-fi',
    price: 0.9,
    selected: true
  }
];

const MOCK_HOTELS = [
  {
    id: 12,
    name: 'hotel1',
    description:
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla molestie nisi erat, hendrerit molestie felis fermentum non.',
    address: {
      street: 'Ulica',
      city: 'Wien',
      state: 'Austria',
      longitude: 119.0,
      latitude: 40
    },
    mark: 6.7
  },
  {
    id: 13,
    name: 'hotel nbla',
    description:
      ' Pellentesque venenatis nec tellus rhoncus tempor. Donec imperdiet tortor dapibus vestibulum condimentum. Cras tristique magna eros, quis sollicitudin risus rutrum at. Sed laoreet semper ex. Nullam ligula felis, mattis in ante at, euismod luctus risus. Curabitur tristique rhoncus orci, sed faucibus velit auctor at. Nullam risus ex, venenatis id massa sit amet, finibus vehicula orci. Sed consectetur, purus eu posuere pulvinar,',
    address: {
      street: 'Ulica',
      city: 'Nis',
      state: 'Srbija',
      longitude: 119.0,
      latitude: 40
    },
    mark: 6.7
  },
  {
    id: 121525,
    name: 'hotel blaa',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. ',
    address: {
      street: 'Ulica',
      city: 'Novi Sad',
      state: 'Srbija',
      longitude: 19.8335,
      latitude: 45.2671
    },
    mark: 6.7
  },
  {
    id: 121233,
    name: 'asdasd hotel',
    description:
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla molestie nisi erat, hendrerit molestie felis fermentum non. Sed nec euismod massa, non volutpat elit. Aliquam non accumsan quam. Pellentesque venenatis nec tellus rhoncus tempor. Donec imperdiet tortor dapibus vestibulum condimentum. Cras tristique magna eros, quis sollicitudin risus rutrum at. Sed laoreet semper ex. Nullam ligula felis, mattis in ante at, euismod luctus risus. Curabitur tristique rhoncus orci, sed faucibus velit auctor at. Nullam risus ex, venenatis id massa sit amet, finibus vehicula orci. Sed consectetur, purus eu posuere pulvinar, magna turpis imperdiet risus, eget sodales felis risus non orci. Sed sodales venenatis arcu, eu dictum nulla varius in. Morbi nec accumsan orci. Vivamus facilisis orci sed felis auctor porttitor. Mauris semper vulputate congue.',
    address: {
      street: 'Ulica',
      city: 'Beograd',
      state: 'Srbija',
      longitude: 20.48,
      latitude: 44.7866
    },
    mark: 6.7
  },
  {
    id: 1523,
    name: 'hotel nbla',
    description:
      ' Pellentesque venenatis nec tellus rhoncus tempor. Donec imperdiet tortor dapibus vestibulum condimentum. Cras tristique magna eros, quis sollicitudin risus rutrum at. Sed laoreet semper ex. Nullam ligula felis, mattis in ante at, euismod luctus risus. Curabitur tristique rhoncus orci, sed faucibus velit auctor at. Nullam risus ex, venenatis id massa sit amet, finibus vehicula orci. Sed consectetur, purus eu posuere pulvinar,',
    address: {
      street: 'Ulica',
      city: 'Nis',
      state: 'Srbija',
      longitude: 21.8958,
      latitude: 43.3209
    },
    mark: 6.7
  },
  {
    id: 1512,
    name: 'hotel blaa',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. ',
    address: {
      street: 'Ulica',
      city: 'Novi Sad',
      state: 'Srbija',
      longitude: 19.8335,
      latitude: 45.2671
    },
    mark: 6.7
  },
  {
    id: 512,
    name: 'asdasd hotel',
    description:
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla molestie nisi erat, hendrerit molestie felis fermentum non. Sed nec euismod massa, non volutpat elit. Aliquam non accumsan quam. Pellentesque venenatis nec tellus rhoncus tempor. Donec imperdiet tortor dapibus vestibulum condimentum. Cras tristique magna eros, quis sollicitudin risus rutrum at. Sed laoreet semper ex. Nullam ligula felis, mattis in ante at, euismod luctus risus. Curabitur tristique rhoncus orci, sed faucibus velit auctor at. Nullam risus ex, venenatis id massa sit amet, finibus vehicula orci. Sed consectetur, purus eu posuere pulvinar, magna turpis imperdiet risus, eget sodales felis risus non orci. Sed sodales venenatis arcu, eu dictum nulla varius in. Morbi nec accumsan orci. Vivamus facilisis orci sed felis auctor porttitor. Mauris semper vulputate congue.',
    address: {
      street: 'Ulica',
      city: 'Beograd',
      state: 'Srbija',
      longitude: 19.8335,
      latitude: 45.2671
    },
    mark: 6.7
  }
];
