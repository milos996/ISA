import { take, put, call } from "redux-saga/effects";
import {
  REGISTRATION,
  LOGIN,
  LOGOUT,
  SAVE_USER_DATA,
  FETCH_USER_FRIENDS,
  SAVE_NEW_PASSWORD,
  FETCH_USERS_BY_NAME,
  SEND_FRIENDSHIP_REQUEST,
  REMOVE_FRIEND
} from "./constants";
import {
  putUserData,
  putUserToken,
  putFriendsData,
  putFoundUsersData
} from "./actions";
import userService from "../../services/api/User";
import authService from "../../services/api/Auth";

export function* registration() {
  const { payload } = yield take(REGISTRATION);
  alert(yield call(authService.registration, payload));
  payload.callback();
}

export function* login() {
  const { payload } = yield take(LOGIN);
  const { data } = yield call(authService.login, payload);
  yield put(putUserToken(data.token));
  yield put(putUserData(data));
  payload.callback();
}

export function* logout() {
  const { payload } = yield take(LOGOUT);
  window.localStorage.clear();
  yield put(putUserToken(null));
  yield put(putUserData(null));
  payload.callback();
}

export function* saveUserData() {
  const { payload } = yield take(SAVE_USER_DATA);
  yield call(userService.saveUser, payload);
}

export function* fetchListOfFriends() {
  const { payload } = yield take(FETCH_USER_FRIENDS);
  const { data } = yield call(userService.fetchFriends, payload.userId);
  yield put(putFriendsData({ data }));
}

export function* savePassword() {
  const { payload } = yield take(SAVE_NEW_PASSWORD);
  yield call(userService.savePassword, payload.password, payload.userId);
}

export function* findByName() {
  const { payload } = yield take(FETCH_USERS_BY_NAME);
  const { data } = yield call(userService.fetchByName, payload);
  yield put(putFoundUsersData({ data }));
}

export function* sendFriendshipRequest() {
  const { payload } = yield take(SEND_FRIENDSHIP_REQUEST);
  yield call(
    userService.sendFriendshipRequest,
    payload.senderUserId,
    payload.invitedUserId
  );
}

export function* removeFriend() {
  const { payload } = yield take(REMOVE_FRIEND);
  yield call(userService.removeFriend, payload.friendsId);
  const { data } = yield call(userService.fetchFriends, payload.userId);
  yield put(putFriendsData({ data }));
}
