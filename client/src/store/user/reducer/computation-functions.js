export function putUserData(state, payload) {
  console.log({
    ...state,
    data: payload
  });
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
    friends: {
      ...state.friends,
      ...payload
    }
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
