export function putError(state, payload) {
  return {
    ...state,
    error: payload
  };
}
