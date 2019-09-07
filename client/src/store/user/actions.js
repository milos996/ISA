import { 
  LOGOUT,
  PUT_USER_DATA,
  SAVE_USER_DATA,
  PUT_USER_TOKEN,
  FETCH_USER_FRIENDS,
  PUT_FRIENDS_DATA,
  SAVE_NEW_PASSWORD,
  FETCH_USERS_BY_NAME,
  PUT_FOUND_USERS_DATA,
  SEND_FRIENDSHIP_REQUEST,
  REMOVE_FRIEND
} from "./constants";

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

export const fetchUsersByName = payload => ({
  type: FETCH_USERS_BY_NAME,
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