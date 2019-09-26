import {
  FETCH_RENT_A_CARS,
  CREATE_RENT_A_CAR,
  PUT_RENT_A_CARS,
  DELETE_RENT_A_CAR,
  SEARCH_RENT_A_CARS,
  SORT_RENT_A_CARS,
  RATE_RENT_A_CAR,
  SHOW_RENT_A_CAR_INCOME,
  PUT_RENT_A_CAR_INCOME,
  SHOW_RENT_A_CAR_BUSYNESS,
  PUT_RENT_A_CAR_BUSYNESS,
  SHOW_AVAILABLE_RENT_A_CAR_VEHICLES,
  PUT_AVAILABLE_RENT_A_CAR_VEHICLES,
  SAVE_RENT_A_CAR_DETAILS,
  FETCH_RENT_A_CAR_LOCATION_INFORMATION,
  PUT_RENT_A_CAR_LOCATION_INFORMATION,
  FETCH_RENT_A_CAR_DETAILS,
  PUT_RENT_A_CAR_DETAILS,
  FETCH_RENT_A_CAR_VEHICLES,
  PUT_RENT_A_CAR_VEHICLES,
  FETCH_RENT_A_CAR_VEHICLES_ON_DISCOUNT,
  PUT_RENT_A_CAR_VEHICLES_ON_DISCOUNT,
  CREATE_VEHICLE,
  PUT_VEHICLE_DETAILS,
  SAVE_VEHICLE_DETAILS,
  DELETE_VEHICLE,
  FETCH_RENT_A_CAR_OFFICES,
  PUT_RENT_A_CAR_OFFICES,
  CREATE_OFFICE,
  DELETE_OFFICE,
  FETCH_VEHICLES,
  PUT_VEHICLES,
  SEARCH_VEHICLES,
  SORT_VEHICLES,
  PUT_VEHICLE_SEARCH_INFORMATION,
  RATE_VEHICLE,
  DISCOUNT_VEHICLE,
  CREATE_VEHICLE_RESERVATION,
  CANCEL_VEHICLE_RESERVATION
} from "./constants";

export const fetchRentACars = payload => ({
  type: FETCH_RENT_A_CARS,
  payload
});

export const createRentACar = payload => ({
  type: CREATE_RENT_A_CAR,
  payload
});

export const deleteRentACar = payload => ({
  type: DELETE_RENT_A_CAR,
  payload
});

export const putRentACars = payload => ({
  type: PUT_RENT_A_CARS,
  payload
});

export const searchRentACars = payload => ({
  type: SEARCH_RENT_A_CARS,
  payload
});

export const sortRentACars = payload => ({
  type: SORT_RENT_A_CARS,
  payload
});

export const rateRentACar = payload => ({
  type: RATE_RENT_A_CAR,
  payload
});

export const showRentACarIncome = payload => ({
  type: SHOW_RENT_A_CAR_INCOME,
  payload
});

export const putRentACarIncome = payload => ({
  type: PUT_RENT_A_CAR_INCOME,
  payload
});

export const showRentACarBusyness = payload => ({
  type: SHOW_RENT_A_CAR_BUSYNESS,
  payload
});

export const putRentACarBusyness = payload => ({
  type: PUT_RENT_A_CAR_BUSYNESS,
  payload
});

export const showAvailableRentACarVehicles = payload => ({
  type: SHOW_AVAILABLE_RENT_A_CAR_VEHICLES,
  payload
});

export const putAvailableRentACarVehicles = payload => ({
  type: PUT_AVAILABLE_RENT_A_CAR_VEHICLES,
  payload
});

export const fetchRentACarDetails = payload => ({
  type: FETCH_RENT_A_CAR_DETAILS,
  payload
});

export const putRentACarDetails = payload => ({
  type: PUT_RENT_A_CAR_DETAILS,
  payload
});

export const saveRentACarDetails = payload => ({
  type: SAVE_RENT_A_CAR_DETAILS,
  payload
});

export const putRentACarLocationInformation = payload => ({
  type: PUT_RENT_A_CAR_LOCATION_INFORMATION,
  payload
});

export const fetchRentACarLocationInformation = payload => ({
  type: FETCH_RENT_A_CAR_LOCATION_INFORMATION,
  payload
});

export const fetchRentACarVehicles = payload => ({
  type: FETCH_RENT_A_CAR_VEHICLES,
  payload
});

export const putRentACarOffices = payload => ({
  type: PUT_RENT_A_CAR_OFFICES,
  payload
});

export const fetchRentACarVehiclesOnDiscount = payload => ({
  type: FETCH_RENT_A_CAR_VEHICLES_ON_DISCOUNT,
  payload
});

export const putRentACarVehiclesOnDiscount = payload => ({
  type: PUT_RENT_A_CAR_VEHICLES_ON_DISCOUNT,
  payload
});

export const fetchRentACarOffices = payload => ({
  type: FETCH_RENT_A_CAR_OFFICES,
  payload
});

export const createRentACarOffice = payload => ({
  type: CREATE_OFFICE,
  payload
});

export const deleteRentACarOffice = payload => ({
  type: DELETE_OFFICE,
  payload
});

export const putRentACarVehicles = payload => ({
  type: PUT_RENT_A_CAR_VEHICLES,
  payload
});

export const fetchVehicles = payload => ({
  type: FETCH_VEHICLES,
  payload
});

export const putVehicles = payload => ({
  type: PUT_VEHICLES,
  payload
});

export const searchVehicles = payload => ({
  type: SEARCH_VEHICLES,
  payload
});

export const sortVehicles = payload => ({
  type: SORT_VEHICLES,
  payload
});

export const rateVehicle = payload => ({
  type: RATE_VEHICLE,
  payload
});

export const discountVehicle = payload => ({
  type: DISCOUNT_VEHICLE,
  payload
});

export const createRentACarVehicle = payload => ({
  type: CREATE_VEHICLE,
  payload
});

export const deleteVehicle = payload => ({
  type: DELETE_VEHICLE,
  payload
});

export const putVehicleDetails = payload => ({
  type: PUT_VEHICLE_DETAILS,
  payload
});

export const saveVehicleDetails = payload => ({
  type: SAVE_VEHICLE_DETAILS,
  payload
});

export const putSearchInformation = payload => ({
  type: PUT_VEHICLE_SEARCH_INFORMATION,
  payload
});

export const createVehicleReservation = payload => ({
  type: CREATE_VEHICLE_RESERVATION,
  payload
});

export const cancelVehicleReservation = payload => ({
  type: CANCEL_VEHICLE_RESERVATION,
  payload
});
