import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  RENT_A_CARS: "/rent-a-cars",
  RENT_A_CAR_DETAILS: "/rent-a-cars/%s",
  RENT_A_CAR_VEHICLES: "/rent-a-cars/%s/vehicles",
  RENT_A_CAR_OFFICES: "/rent-a-car-locations/%s",
  DELETE_RENT_A_CAR: "/rent-a-cars/delete/%s",
  OFFICES: "/rent-a-car-locations",
  DELETE_OFFICE: "/rent-a-car-locations/%s",
  SEARCH_RENT_A_CARS:
    "/rent-a-cars/search?&city=%s&state=%s&name=%s&pickUpDate=%s&dropOffDate=%s",
  VEHICLES: "/vehicles",
  SEARCH_VEHICLES:
    "/vehicles/search?&pickUpDate=%s&dropOffDate=%s&pickUpLocation=%s&dropOffLocation=%s&type=%s&seats=%d&startRange=%d&endRange=%d&rentACarId=%s",
  DELETE_VEHICLE: "/vehicles/%s",
  VEHICLE_RESERVATIONS: "vehicle-reservations"
};

class RentACarService extends HttpBaseClient {
  fetchRentACars = () => {
    return this.getApiClient().get(ENDPOINTS.RENT_A_CARS);
  };

  createRentACar = rentACar => {
    return this.getApiClient().post(ENDPOINTS.RENT_A_CARS, rentACar);
  };

  updateRentACar = rentACar => {
    return this.getApiClient().put(ENDPOINTS.RENT_A_CARS, rentACar);
  };

  deleteRentACar = rentACarId => {
    return this.getApiClient().delete(
      format(ENDPOINTS.DELETE_RENT_A_CAR, rentACarId)
    );
  };

  fetchRentACarDetails = rentACarId => {
    return this.getApiClient().get(
      format(ENDPOINTS.RENT_A_CAR_DETAILS, rentACarId)
    );
  };

  fetchRentACarVehicles = rentACarId => {
    return this.getApiClient().get(
      format(ENDPOINTS.RENT_A_CAR_VEHICLES, rentACarId)
    );
  };

  fetchRentACarOffices = rentACarId => {
    return this.getApiClient().get(
      format(ENDPOINTS.RENT_A_CAR_OFFICES, rentACarId)
    );
  };

  createRentACarOffice = office => {
    return this.getApiClient().post(ENDPOINTS.OFFICES, office);
  };

  deleteOffice = officeId => {
    return this.getApiClient().delete(
      format(ENDPOINTS.DELETE_OFFICE, officeId)
    );
  };

  searchRentACars = payload => {
    return this.getApiClient().get(
      format(
        ENDPOINTS.SEARCH_RENT_A_CARS,
        payload.city,
        payload.state,
        payload.name,
        payload.pickUpDate,
        payload.dropOffDate
      )
    );
  };

  fetchVehicles = () => {
    return this.getApiClient().get(ENDPOINTS.VEHICLES);
  };

  searchVehicles = payload => {
    console.log(payload);
    return this.getApiClient().get(
      format(
        ENDPOINTS.SEARCH_VEHICLES,
        payload.pickUpDate,
        payload.dropOffDate,
        payload.pickUpLocation,
        payload.dropOffLocation,
        payload.type,
        payload.seats,
        payload.startRange,
        payload.endRange,
        payload.rentACarId
      )
    );
  };

  createRentACarVehicle = payload => {
    return this.getApiClient().post(ENDPOINTS.VEHICLES, payload);
  };

  updateVehicle = payload => {
    console.log("UPDATE: " + payload);

    return this.getApiClient().put(ENDPOINTS.VEHICLES, payload);
  };

  deleteVehicle = vehicleId => {
    return this.getApiClient().delete(
      format(ENDPOINTS.DELETE_VEHICLE, vehicleId)
    );
  };

  reserveVehicle = reservation => {
    return this.getApiClient().post(
      ENDPOINTS.VEHICLE_RESERVATIONS,
      reservation
    );
  };
}

export default new RentACarService();
