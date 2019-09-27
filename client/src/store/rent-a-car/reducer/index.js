import {
  PUT_RENT_A_CARS,
  PUT_RENT_A_CAR_DETAILS,
  PUT_RENT_A_CAR_VEHICLES,
  PUT_RENT_A_CAR_VEHICLES_ON_DISCOUNT,
  PUT_RENT_A_CAR_OFFICES,
  PUT_RENT_A_CAR_LOCATION_INFORMATION,
  PUT_VEHICLES,
  PUT_OFFICES,
  PUT_VEHICLE_SEARCH_INFORMATION,
  PUT_VEHICLE_DETAILS,
  PUT_RENT_A_CAR_INCOME,
  PUT_RENT_A_CAR_BUSYNESS,
  PUT_AVAILABLE_RENT_A_CAR_VEHICLES
} from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  rentACars: [
    {
      id: "12312",
      city: "Beograd",
      name: "Deluxe Auto",
      description: "Najudobnija vozila",
      address: {
        state: "Republika Srbija",
        street: "Ulica",
        city: "Beograd",
        country: "Srbija",
        longitude: 19.8335,
        latitude: 45.2671
      },
      rating: 4.7
    },
    {
      id: "123112",
      city: "Beograd",
      name: "Moj Auto",
      description: "Najudobnija vozila",
      address: {
        state: "Republika Srbija",
        street: "Ulica",
        city: "Beograd",
        country: "Srbija",
        longitude: 19.9335,
        latitude: 45.2671
      },
      rating: 7.5
    }
  ],
  vehicles: [
    {
      id: "6fd5fc58-467c-430f-8cb5-18f5eabef791",
      brand: "Mercedes"
    }
  ],
  offices: [],
  rentACarVehicles: [],
  rentACarVehiclesOnDiscount: [],
  rentACarOffices: [],
  rentACarDetails: {},
  vehicleSearchInformation: {
    pickUpDate: "",
    dropOffDate: "",
    pickUpLocation: "",
    dropOffLocation: ""
  },
  vehicleDetails: {
    id: "",
    brand: "",
    model: "",
    yearOfProduction: "",
    numberOfSeats: ""
  },
  rentACarVehiclesIncome: [{ vehicle: "", income: 0 }],
  rentACarVehiclesBusyness: [{ vehicle: "", busyness: 0 }],
  rentACarAvailableVehicles: []
};

const rentACarReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_RENT_A_CARS]: computationFunctions.putRentACars,
  [PUT_RENT_A_CAR_VEHICLES]: computationFunctions.putRentACarVehicles,
  [PUT_RENT_A_CAR_VEHICLES_ON_DISCOUNT]:
    computationFunctions.putRentACarVehiclesOnDiscount,
  [PUT_RENT_A_CAR_DETAILS]: computationFunctions.putRentACarDetails,
  [PUT_RENT_A_CAR_OFFICES]: computationFunctions.putRentACarOffices,
  [PUT_RENT_A_CAR_LOCATION_INFORMATION]:
    computationFunctions.putRentACarLocationInformation,
  [PUT_RENT_A_CAR_INCOME]: computationFunctions.putRentACarVehiclesIncome,
  [PUT_RENT_A_CAR_BUSYNESS]: computationFunctions.putRentACarVehiclesBusyness,
  [PUT_AVAILABLE_RENT_A_CAR_VEHICLES]:
    computationFunctions.putAvailableRentACarVehicles,
  [PUT_VEHICLES]: computationFunctions.putVehicles,
  [PUT_OFFICES]: computationFunctions.putOffices,
  [PUT_VEHICLE_DETAILS]: computationFunctions.putVehicleDetails,
  [PUT_VEHICLE_SEARCH_INFORMATION]:
    computationFunctions.putVehicleSearchInformation
};

export default rentACarReducer;
