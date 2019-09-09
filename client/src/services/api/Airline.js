import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  SAVE_AIRLINE: "/airlines",
  FETCH_AIRLINE: "/airlines/%s",
  FETCH_RATING: "/airlines/averageRating/%s",
  FETCH_AIRLINES: "/airlines/all"
};

class AirlineService extends HttpBaseClient {
  saveAirline = details => {
    this.getApiClient().put(ENDPOINTS.SAVE_AIRLINE, details);
  };

  fetchAirlineDetails = airlineId => {
    return this.getApiClient().get(format(ENDPOINTS.FETCH_AIRLINE, airlineId));
  };

  getRating = airlineId => {
    return this.getApiClient().get(format(ENDPOINTS.FETCH_RATING, airlineId));
  };

  //not exist on backend yet
  fetchAirlines = () => {
    return this.getApiClient().get(ENDPOINTS.FETCH_AIRLINES);
  };
}

export default new AirlineService();
