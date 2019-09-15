import {
  REGISTRATION,
  LOGIN,
  LOGOUT,
  PUT_USER_DATA,
  SAVE_USER_DATA,
  PUT_USER_TOKEN,
  FETCH_USER_FRIENDS,
  PUT_FRIENDS_DATA,
  SAVE_NEW_PASSWORD,
  PUT_FOUND_USERS_DATA,
  SEND_FRIENDSHIP_REQUEST,
  REMOVE_FRIEND,
  SEARCH_USERS,
  FETCH_USER_DATA,
  FETCH_USERS_THAT_DONT_HAVE_ENTITY,
  PUT_USERS
} from "./constants";
import Password from "../../components/user/Password";

export const registerUser = payload => ({
  type: REGISTRATION,
  payload
});

export const loginUser = payload => ({
  type: LOGIN,
  payload
});

export const logoutUser = payload => ({
  type: LOGOUT,
  payload
});

export const putUserData = payload => ({
  type: PUT_USER_DATA,
  payload
});

export const putUserToken = payload => ({
  type: PUT_USER_TOKEN,
  payload
});

export const saveUserData = payload => ({
  type: SAVE_USER_DATA,
  payload
});

export const saveNewPassword = payload => ({
  type: SAVE_NEW_PASSWORD,
  payload
});

export const fetchUserFriends = payload => ({
  type: FETCH_USER_FRIENDS,
  payload
});

export const putFriendsData = payload => ({
  type: PUT_FRIENDS_DATA,
  payload
});

export const putFoundUsersData = payload => ({
  type: PUT_FOUND_USERS_DATA,
  payload
});

export const sendFriendshipRequest = payload => ({
  type: SEND_FRIENDSHIP_REQUEST,
  payload
});

export const removeFriend = payload => ({
  type: REMOVE_FRIEND,
  payload
});

export const searchUsers = payload => ({
  type: SEARCH_USERS,
  payload
});

export const fetchUserData = payload => ({
  type: FETCH_USER_DATA,
  payload
});

export const fetchUsersThatDontHaveEntity = payload => ({
  type: FETCH_USERS_THAT_DONT_HAVE_ENTITY,
  payload
});

export const putUsers = payload => ({
  type: PUT_USERS,
  payload
});
