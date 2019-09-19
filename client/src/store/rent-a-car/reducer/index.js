import {
  PUT_RENT_A_CARS,
  PUT_RENT_A_CAR_DETAILS,
  PUT_RENT_A_CAR_VEHICLES,
  PUT_RENT_A_CAR_OFFICES,
  PUT_RENT_A_CAR_LOCATION_INFORMATION,
  PUT_VEHICLES,
  PUT_VEHICLE_SEARCH_INFORMATION,
  PUT_VEHICLE_DETAILS,
  PUT_CREATED_RENT_A_CAR_VEHICLE,
  PUT_DELETE_VEHICLE_WITH_ID,
  PUT_RENT_A_CAR_INCOME,
  PUT_RENT_A_CAR_BUSYNESS
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
      }
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
      }
    }
  ],
  vehicles: [
    {
      id: "6fd5fc58-467c-430f-8cb5-18f5eabef791",
      brand: "Mercedes"
    }
  ],
  rentACarVehicles: [
    {
      id: "6fd5fc58-467c-430f-8cb5-18f5eabef791",
      rentACarId: "acb66aa7-af3e-4752-a13a-95239b91fc3b"
    }
  ],
  rentACarOffices: {},
  rentACar: [
    {
      id: "12312",
      description: "ponvolasdjas",
      address: {
        street: "Ulica",
        city: "Beograd",
        country: "Srbija",
        long: 19.8335,
        lat: 45.2671
      }
    }
  ],
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
  rentACarVehiclesBusyness: [{ vehicle: "", busyness: 0 }]
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
  [PUT_RENT_A_CAR_DETAILS]: computationFunctions.putRentACarDetails,
  [PUT_RENT_A_CAR_OFFICES]: computationFunctions.putRentACarOffices,
  [PUT_RENT_A_CAR_LOCATION_INFORMATION]:
    computationFunctions.putRentACarLocationInformation,
  [PUT_VEHICLES]: computationFunctions.putVehicles,
  [PUT_VEHICLE_DETAILS]: computationFunctions.putVehicleDetails,
  [PUT_VEHICLE_SEARCH_INFORMATION]:
    computationFunctions.putVehicleSearchInformation,
  [PUT_CREATED_RENT_A_CAR_VEHICLE]:
    computationFunctions.putCreatedRentACarVehicle,
  [PUT_RENT_A_CAR_INCOME]: computationFunctions.putRentACarVehiclesIncome,
  [PUT_RENT_A_CAR_BUSYNESS]: computationFunctions.putRentACarVehiclesBusyness
};

export default rentACarReducer;
