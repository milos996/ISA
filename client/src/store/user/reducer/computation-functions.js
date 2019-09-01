export function putUserData(state, payload) {
  console.log({
    ...state,
    ...payload
  });

  return {
    ...state,
    ...payload
  };
}
