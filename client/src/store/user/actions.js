import { PUT_HOTEL_DETAILS } from "./constants";

export const putHotelDetails = payload => ({
  type: PUT_HOTEL_DETAILS,
  payload
});
