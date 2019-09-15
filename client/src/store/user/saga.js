import { take, put, call } from "redux-saga/effects";
import {
  LOGOUT,
  SAVE_USER_DATA,
  FETCH_USER_FRIENDS,
  SAVE_NEW_PASSWORD,
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

export function* logout() {
  const { payload } = yield take(LOGOUT);

  // TODO Implement delete from window.storage
  yield put(
    putUserData({
      data: null
    }),
    putUserToken(null)
  );

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
