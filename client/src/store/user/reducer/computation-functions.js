export function putUserData(state, payload) {
  return {
    ...state,
    data: payload
  };
}

export function putUserToken(state, payload) {
  return {
    ...state,
    token: payload
  };
}

export function putFriendsData(state, payload) {
  return {
    ...state,
    friends: payload
  };
}

export function putFoundUsersData(state, payload) {
  return {
    ...state,
    foundUsers: payload
  };
}

export function putUsers(state, users) {
  return {
    ...state,
    users
  };
}

export function putUserInvites(state, userInvites) {
  return {
    ...state,
    userInvites
  };
}

export function putUserVehiclesReservation(state, userVehiclesReservation) {
  return {
    ...state,
    userVehiclesReservation
  };
}

export function putUserHotelsReservation(state, userHotelsReservation) {
  return {
    ...state,
    userHotelsReservation
  };
}

export function putUserFlightsReservation(state, userFlightsReservation) {
  return {
    ...state,
    userFlightsReservation
  };
}

export function putFriendshipRequests(state, payload) {
  return {
    ...state,
    friendshipRequests: payload
  };
}

export function putLogged(state, logged) {
  return {
    ...state,
    logged
  };
}
