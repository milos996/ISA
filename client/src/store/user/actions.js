import { LOGOUT, PUT_USER_DATA } from "./constants";

export const logoutUser = payload => ({
  type: LOGOUT,
  payload
});

export const putUserData = payload => ({
  type: PUT_USER_DATA,
  payload
});
