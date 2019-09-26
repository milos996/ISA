import {
  FETCH_RENT_A_CARS,
  CREATE_RENT_A_CAR,
  PUT_RENT_A_CARS,
  DELETE_RENT_A_CAR,
  SEARCH_RENT_A_CARS,
  SAVE_RENT_A_CAR_DETAILS,
  FETCH_RENT_A_CAR_LOCATION_INFORMATION,
  PUT_RENT_A_CAR_LOCATION_INFORMATION,
  FETCH_RENT_A_CAR_DETAILS,
  PUT_RENT_A_CAR_DETAILS,
  FETCH_RENT_A_CAR_VEHICLES,
  PUT_RENT_A_CAR_VEHICLES,
  CREATE_VEHICLE,
  PUT_CREATED_RENT_A_CAR_VEHICLE,
  PUT_VEHICLE_DETAILS,
  SAVE_VEHICLE_DETAILS,
  DELETE_VEHICLE,
  PUT_DELETE_VEHICLE_WITH_ID,
  FETCH_RENT_A_CAR_OFFICES,
  PUT_RENT_A_CAR_OFFICES,
  CREATE_OFFICE,
  DELETE_OFFICE,
  PUT_DELETE_OFFICE_WITH_ID,
  FETCH_VEHICLES,
  PUT_VEHICLES,
  SEARCH_VEHICLES,
  PUT_VEHICLE_SEARCH_INFORMATION,
  CREATE_VEHICLE_RESERVATION
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

export const fetchRentACarOffices = payload => ({
  type: FETCH_RENT_A_CAR_OFFICES,
  payload
});

export const createRentACarOffice = payload => ({
  type: CREATE_OFFICE,
  payload
});

export const putCreatedRentACarVehicle = payload => ({
  type: PUT_CREATED_RENT_A_CAR_VEHICLE,
  payload
});

export const deleteRentACarOffice = payload => ({
  type: DELETE_OFFICE,
  payload
});

export const putDeleteOfficeWithId = payload => ({
  type: PUT_DELETE_OFFICE_WITH_ID,
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

export const putDeleteVehicleWithId = payload => ({
  type: PUT_DELETE_VEHICLE_WITH_ID,
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
