import { 
  LOGOUT,
  PUT_USER_DATA,
  SAVE_USER_DATA,
  FETCH_USER_DATA
} from "./constants";

export const logoutUser = payload => ({
  type: LOGOUT,
  payload
});

export const putUserData = payload => ({
  type: PUT_USER_DATA,
  payload
});

export const saveUserData = payload => ({
  type: SAVE_USER_DATA,
  payload
});

export const fetchUserData = payload => ({
  type: FETCH_USER_DATA,
  payload
});