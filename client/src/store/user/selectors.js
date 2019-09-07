import { createSelector } from "reselect";

const reducer = "userReducer";

export const userDataSelector = state => state[reducer].data;
export const userTokenSelector = state => state[reducer].token;
export const selectUserFriends = state => state[reducer].friends;
export const foundFriendsSelector = state => state[reducer].foundUsers;