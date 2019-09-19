import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  RENT_A_CARS: "/rent-a-cars",
  RENT_A_CAR_DETAILS: "/rent-a-cars/%s",
  RENT_A_CAR_VEHICLES: "/rent-a-cars/%s/vehicles",
  RENT_A_CAR_OFFICES: "/rent-a-car-locations/%s",
  RENT_A_CAR_RATING: "/rent-a-cars/rating/",
  RENT_A_CAR_INCOME: "/rent-a-cars/%s/income?startDate=%s&endDate=%s",
  RENT_A_CAR_BUSYNESS: "/rent-a-cars/%s/busyness?startDate=%s&endDate=%s",
  DELETE_RENT_A_CAR: "/rent-a-cars/delete/%s",
  OFFICES: "/rent-a-car-locations",
  DELETE_OFFICE: "/rent-a-car-locations/%s",
  SEARCH_RENT_A_CARS:
    "/rent-a-cars/search?&city=%s&state=%s&name=%s&pickUpDate=%s&dropOffDate=%s",
  SORT_RENT_A_CARS: "/rent-a-cars/sort?&by=%s",
  VEHICLES: "/vehicles",
  SEARCH_VEHICLES:
    "/vehicles/search?&pickUpDate=%s&dropOffDate=%s&pickUpLocation=%s&dropOffLocation=%s&type=%s&seats=%d&startRange=%d&endRange=%d&rentACarId=%s",
  DELETE_VEHICLE: "/vehicles/%s",
  VEHICLE_RESERVATIONS: "/vehicle-reservations",
  VEHICLE_RATING: "/vehicles/rating/",
  CANCEL_VEHICLE_RESERVATIONS: "/vehicle-reservations/%s",
  SORT_VEHICLES: "/vehicles/sort?by=%s&rentACarId=%s"
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

  showRentACarIncome = incomePeriod => {
    return this.getApiClient().get(
      format(
        ENDPOINTS.RENT_A_CAR_INCOME,
        incomePeriod.id,
        incomePeriod.startDate,
        incomePeriod.endDate
      )
    );
  };

  showRentACarBusyness = busynessPeriod => {
    return this.getApiClient().get(
      format(
        ENDPOINTS.RENT_A_CAR_BUSYNESS,
        busynessPeriod.id,
        busynessPeriod.startDate,
        busynessPeriod.endDate
      )
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

  sortRentACars = payload => {
    return this.getApiClient().get(
      format(ENDPOINTS.SORT_RENT_A_CARS, payload.by)
    );
  };

  fetchVehicles = () => {
    return this.getApiClient().get(ENDPOINTS.VEHICLES);
  };

  searchVehicles = payload => {
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

  sortVehicles = payload => {
    return this.getApiClient().get(
      format(ENDPOINTS.SORT_VEHICLES, payload.by, payload.rentACarId)
    );
  };

  createRentACarVehicle = payload => {
    return this.getApiClient().post(ENDPOINTS.VEHICLES, payload);
  };

  updateVehicle = payload => {
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

  cancelVehicleReservation = reservationId => {
    return this.getApiClient().delete(
      format(ENDPOINTS.CANCEL_VEHICLE_RESERVATIONS, reservationId)
    );
  };

  rateRentACar = rateData => {
    return this.getApiClient().post(ENDPOINTS.RENT_A_CAR_RATING, rateData);
  };

  rateVehicle = rateData => {
    return this.getApiClient().post(ENDPOINTS.VEHICLE_RATING, rateData);
  };
}

export default new RentACarService();
