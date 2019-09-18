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

export function putFriendshipRequests(state, payload) {
  return {
    ...state,
    friendshipRequests: payload
  };
}
