export function putRentACars(state, rentACars) {
  return {
    ...state,
    rentACars
  };
}

export function putCreatedRentACarVehicle(state, rentACarVehicle) {
  const { data } = state["rentACarReducer"].rentACarVehicles;
  return {
    ...state,
    rentACarVehicle
  };
}

export function putVehicleDetails(state, vehicleDetails) {
  return {
    ...state,
    vehicleDetails
  };
}

export function putVehicleSearchInformation(state, vehicleSearchInformation) {
  return {
    ...state,
    vehicleSearchInformation
  };
}

export function putRentACarVehicles(state, rentACarVehicles) {
  return {
    ...state,
    rentACarVehicles
  };
}

export function putRentACarOffices(state, rentACarOffices) {
  return {
    ...state,
    rentACarOffices
  };
}

export function putRentACarDetails(state, rentACar) {
  return {
    ...state,
    rentACar
  };
}

export function putRentACarLocationInformation(
  state,
  rentACarLocationInformation
) {
  return {
    ...state,
    rentACarLocationInformation
  };
}

export function putVehicles(state, vehicles) {
  return {
    ...state,
    vehicles
  };
}
