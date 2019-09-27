import {
  REGISTRATION,
  LOGIN,
  LOGOUT,
  PUT_USER_DATA,
  SAVE_USER_DATA,
  PUT_USER_TOKEN,
  PUT_FRIENDS_DATA,
  SAVE_NEW_PASSWORD,
  PUT_FOUND_USERS_DATA,
  SEND_FRIENDSHIP_REQUEST,
  REMOVE_FRIEND,
  SEARCH_USERS,
  FETCH_USERS_THAT_DONT_HAVE_ENTITY,
  PUT_USERS,
  FETCH_USER_VEHICLES_RESERVATION,
  PUT_USER_VEHICLES_RESERVATION,
  FETCH_USER_HOTELS_RESERVATION,
  PUT_USER_HOTELS_RESERVATION,
  FETCH_USER_FLIGHTS_RESERVATION,
  PUT_USER_FLIGHTS_RESERVATION,
  CHANGE_RENT_A_CAR_ADMIN_PASSWORD,
  FETCH_USER_INVITES,
  PUT_USER_INVITES,
  ACCEPT_INVITE,
  DECLINE_INVITE,
  FETCH_USER_DATA,
  FETCH_FRIENDS_DATA,
  FETCH_FRIENDSHIP_REQUESTS,
  PUT_FRIENDSHIP_REQUESTS,
  UPDATE_FRIENDSHIP_REQUEST,
  PUT_LOGGED
} from "./constants";

export const registerUser = payload => ({
  type: REGISTRATION,
  payload
});

export const loginUser = payload => ({
  type: LOGIN,
  payload
});

export const putLogged = payload => ({
  type: PUT_LOGGED,
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

export const fetchUsersThatDontHaveEntity = payload => ({
  type: FETCH_USERS_THAT_DONT_HAVE_ENTITY,
  payload
});

export const putUsers = payload => ({
  type: PUT_USERS,
  payload
});

export const fetchUserVehiclesReservation = payload => ({
  type: FETCH_USER_VEHICLES_RESERVATION,
  payload
});

export const putUserVehiclesReservation = payload => ({
  type: PUT_USER_VEHICLES_RESERVATION,
  payload
});

export const fetchUserHotelsReservation = payload => ({
  type: FETCH_USER_HOTELS_RESERVATION,
  payload
});

export const putUserHotelsReservation = payload => ({
  type: PUT_USER_HOTELS_RESERVATION,
  payload
});

export const fetchUserFlightsReservation = payload => ({
  type: FETCH_USER_FLIGHTS_RESERVATION,
  payload
});

export const putUserFlightsReservation = payload => ({
  type: PUT_USER_FLIGHTS_RESERVATION,
  payload
});

export const fetchUserInvites = payload => ({
  type: FETCH_USER_INVITES,
  payload
});

export const putUserInvites = payload => ({
  type: PUT_USER_INVITES,
  payload
});

export const acceptInvite = payload => ({
  type: ACCEPT_INVITE,
  payload
});

export const declineInvite = payload => ({
  type: DECLINE_INVITE,
  payload
});

export const changeRentACarAdminPassword = payload => ({
  type: CHANGE_RENT_A_CAR_ADMIN_PASSWORD,
  payload
});

export const fetchUserData = payload => ({
  type: FETCH_USER_DATA,
  payload
});

export const fetchFriendsData = payload => ({
  type: FETCH_FRIENDS_DATA,
  payload
});

export const fetchFriendshipRequests = payload => ({
  type: FETCH_FRIENDSHIP_REQUESTS,
  payload
});

export const putFriendshipRequests = payload => ({
  type: PUT_FRIENDSHIP_REQUESTS,
  payload
});

export const updateFriendshipRequest = payload => ({
  type: UPDATE_FRIENDSHIP_REQUEST,
  payload
});
