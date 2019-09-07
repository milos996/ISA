export function putUserData(state, payload) {
  return {
    ...state,
    ...payload
  };
}
