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
  FETCH_USERS_BY_NAME,
  PUT_FOUND_USERS_DATA,
  SEND_FRIENDSHIP_REQUEST,
  REMOVE_FRIEND,
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
  DECLINE_INVITE
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
