import { take, put, call } from "redux-saga/effects";
import {
  REGISTRATION,
  LOGIN,
  LOGOUT,
  SAVE_USER_DATA,
  FETCH_USER_FRIENDS,
  SAVE_NEW_PASSWORD,
  SEND_FRIENDSHIP_REQUEST,
  REMOVE_FRIEND,
  FETCH_USER_DATA,
  FETCH_USERS_BY_NAME,
  FETCH_USERS_THAT_DONT_HAVE_ENTITY
} from "./constants";
import {
  putUserData,
  putUserToken,
  putFriendsData,
  putFoundUsersData,
  putUsers
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

export function* fetchUserData() {
  const { payload } = yield take(FETCH_USER_DATA);
  const { data } = yield call(userService.fetchUser, payload);
  yield put(putUserData(data));
}
export function* fetchUsersWithoutEntity() {
  yield take(FETCH_USERS_THAT_DONT_HAVE_ENTITY);
  // TODO zamjeni data za MOCK_USERS kada server bude radio
  // const { data } = yield call(userService.fetchUsersWithoutEntity);

  yield put(putUsers(MOCK_USERS));
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
