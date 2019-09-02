export function putUserData(state, payload) {

  return {
    ...state,
    data:{
      ...state.data,
      ...payload
    }
  };
}
