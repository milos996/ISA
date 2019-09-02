import { createSelector } from "reselect";

const reducer = "userReducer";

export const userDataSelector = state => state[reducer].data;
export const userTokenSelector = state => state[reducer].token;

export const selectUserDetails = state => {
    return state[reducer].data;
}

export const selectUserFriends = state => {
    return state[reducer].friends;
}
