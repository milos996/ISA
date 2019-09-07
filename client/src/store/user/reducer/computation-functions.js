export function putUserData(state, payload) {
  return {
    ...state,
    data: {
      ...state.data,
      ...payload
    }
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
    friends: {
      ...state.friends,
      ...payload
    }
  };
}

export function putFoundUsersData(state, payload) {
  return {
    ...state,
    payload
  };
}
