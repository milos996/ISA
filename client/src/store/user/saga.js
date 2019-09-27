import { take, put, call } from "redux-saga/effects";
import {
  REGISTRATION,
  LOGIN,
  LOGOUT,
  SAVE_USER_DATA,
  SAVE_NEW_PASSWORD,
  SEND_FRIENDSHIP_REQUEST,
  REMOVE_FRIEND,
  FETCH_USERS_THAT_DONT_HAVE_ENTITY,
  CHANGE_RENT_A_CAR_ADMIN_PASSWORD,
  FETCH_USER_HOTELS_RESERVATION,
  FETCH_USER_FLIGHTS_RESERVATION,
  FETCH_USER_VEHICLES_RESERVATION,
  FETCH_USER_INVITES,
  ACCEPT_INVITE,
  DECLINE_INVITE,
  FETCH_USER_DATA,
  FETCH_FRIENDS_DATA,
  FETCH_FRIENDSHIP_REQUESTS,
  UPDATE_FRIENDSHIP_REQUEST
} from "./constants";
import {
  putUserData,
  putUserToken,
  putFriendsData,
  putFoundUsersData,
  putUsers,
  putUserFlightsReservation,
  putUserHotelsReservation,
  putUserVehiclesReservation,
  putUserInvites,
  putFriendshipRequests,
  putLogged
} from "./actions";
import userService from "../../services/api/User";
import authService from "../../services/api/Auth";
import reservationService from "../../services/api/Reservation";

export function* registration() {
  const { payload } = yield take(REGISTRATION);
  const { data } = yield call(authService.registration, payload);
  alert(data.message);
  payload.callback();
}

export function* login() {
  const { payload } = yield take(LOGIN);
  const { data } = yield call(authService.login, payload);
  yield put(putUserData(data));
  yield put(putUserToken(data.token));
}

export function* logout() {
  const { payload } = yield take(LOGOUT);
  window.localStorage.clear();
  yield put(putUserToken(null));
  yield put(putLogged(null));
  yield put(putUserData(null));
  payload.callback();
}

export function* saveUserData() {
  const { payload } = yield take(SAVE_USER_DATA);
  yield call(userService.saveUser, payload);
}

export function* savePassword() {
  const { payload } = yield take(SAVE_NEW_PASSWORD);
  yield call(userService.savePassword, payload.requestNewPassword);
}

export function* sendFriendshipRequest() {
  const { payload } = yield take(SEND_FRIENDSHIP_REQUEST);
  yield call(userService.sendFriendshipRequest, payload.invitedUserId);
}

export function* removeFriend() {
  const { payload } = yield take(REMOVE_FRIEND);
  yield call(userService.removeFriend, payload.friendsId);
}

export function* fetchUsersWithoutEntity() {
  yield take(FETCH_USERS_THAT_DONT_HAVE_ENTITY);
  // TODO zamjeni data za MOCK_USERS kada server bude radio
  // const { data } = yield call(userService.fetchUsersWithoutEntity);

  yield put(putUsers(MOCK_USERS));
}

export function* fetchUserVehiclesReservation() {
  yield take(FETCH_USER_VEHICLES_RESERVATION);

  const vehiclesReservation = yield call(
    reservationService.fetchUserVehiclesReservation
  );

  yield put(putUserVehiclesReservation(vehiclesReservation.data));
}

export function* fetchUserHotelsReservation() {
  yield take(FETCH_USER_HOTELS_RESERVATION);
  const hotelsReservation = yield call(
    reservationService.fetchUserHotelsReservation
  );

  yield put(putUserHotelsReservation(hotelsReservation.data));
}

export function* fetchUserFlightsReservation() {
  yield take(FETCH_USER_FLIGHTS_RESERVATION);
  const flightsReservation = yield call(
    reservationService.fetchUserFlightsReservation
  );
  yield put(putUserFlightsReservation(flightsReservation.data));
}

export function* changeRentACarAdminPassword() {
  const { payload } = yield take(CHANGE_RENT_A_CAR_ADMIN_PASSWORD);
  yield call(authService.changeRentACarAdminPassword, payload);
  payload.callback();
}

export function* fetchUserInvites() {
  const { payload } = yield take(FETCH_USER_INVITES);
  const { data } = yield call(userService.fetchUserInvites, payload);

  yield put(putUserInvites(data));
}

export function* acceptInvite() {
  const { payload } = yield take(ACCEPT_INVITE);
  const { data } = yield call(userService.acceptInvite, payload);

  yield put(putUserInvites(data));
}

export function* declineInvite() {
  const { payload } = yield take(DECLINE_INVITE);
  const { data } = yield call(userService.declineInvite, payload);

  yield put(putUserInvites(data));
}

export function* fetchUserData() {
  const { payload } = yield take(FETCH_USER_DATA);
  const { data } = yield call(userService.fetchUser, payload);
  yield put(putUserData(data));
}

export function* fetchFriendsData() {
  const { payload } = yield take(FETCH_FRIENDS_DATA);
  const { data } = yield call(userService.fetchFriends, payload);
  yield put(putFriendsData(data));
}

export function* fetchFriendshipRequests() {
  const { payload } = yield take(FETCH_FRIENDSHIP_REQUESTS);
  const { data } = yield call(userService.fetchFriendshipRequests, payload);
  yield put(putFriendshipRequests(data));
}

export function* updateFriendshipRequest() {
  const { payload } = yield take(UPDATE_FRIENDSHIP_REQUEST);
  yield call(userService.updateFriendshipRequest, payload);
}

const MOCK_USERS = [
  {
    id: 23,
    name: "Milos",
    email: "ivfv@gmail.com",
    role: "USER"
  },
  {
    id: 212,
    name: "Nemanja",
    email: "mmmfv@gmail.com",
    role: "USER"
  },
  {
    id: 255,
    name: "Milica",
    email: "nnnn@gmail.com",
    role: "USER"
  },
  {
    id: 1,
    name: "Ljubica",
    email: "aaa@gmail.com",
    role: "USER"
  }
];
