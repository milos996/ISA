import { createSelector } from "reselect";

const reducer = "rentACarReducer";

export const selectRentACars = state => {
  return state[reducer].rentACars;
};

export const selectRentACarDetails = state => {
  return state[reducer].rentACarDetails;
};

export const selectVehicleSearchInformation = state => {
  return state[reducer].vehicleSearchInformation;
};

export const selectRentACarLocationInformation = state => {
  return state[reducer].rentACarLocationInformation;
};

export const selectRentACarVehicles = state => {
  return state[reducer].rentACarVehicles;
};

export const selectRentACarVehiclesOnDiscount = state => {
  return state[reducer].rentACarVehiclesOnDiscount;
};

export const selectRentACarVehiclesIncome = state => {
  return state[reducer].rentACarVehiclesIncome;
};

export const selectRentACarVehiclesBusyness = state => {
  return state[reducer].rentACarVehiclesBusyness;
};

export const selectRentACarAvailableVehicles = state => {
  return state[reducer].rentACarAvailableVehicles;
};

export const selectRentACarOffices = state => {
  return state[reducer].rentACarOffices;
};

export const selectVehicles = state => {
  return state[reducer].vehicles;
};

export const selectOffices = state => {
  console.log(state);
  return state[reducer].offices;
};
