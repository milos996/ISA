import { HttpBaseClient } from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  RENT_A_CARS: "/rent-a-cars",
  RENT_A_CAR_DETAILS: "/rent-a-cars/%s"
};

class RentACarService extends HttpBaseClient {
  fetchRentACars = () => {
    return this.getApiClient().get(ENDPOINTS.RENT_A_CARS);
  };
}

export default new RentACarService();
