import { PUT_ERROR } from "./constants";

export const putError = payload => ({
  type: PUT_ERROR,
  payload
});
