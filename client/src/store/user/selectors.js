const reducer = "userReducer";

export const userDataSelector = state => state[reducer].data;
export const userTokenSelector = state => state[reducer].token;
export const selectUserFriends = state => state[reducer].friends;
export const foundFriendsSelector = state => state[reducer].foundUsers;
export const selectUsers = state => state[reducer].users;
export const selectUserInvites = state => state[reducer].userInvites;
export const selectVehiclesReservation = state =>
  state[reducer].userVehiclesReservation;
export const selectHotelsReservation = state =>
  state[reducer].userHotelsReservation;
export const selectFlightsReservation = state =>
  state[reducer].userFlightsReservation;
export const selectFriendshipRequests = state =>
  state[reducer].friendshipRequests;
