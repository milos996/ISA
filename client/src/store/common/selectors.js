const reducer = "commonReducer";

export const selectError = state => {
  return state[reducer].error;
};
