import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  SAVE_AIRLINE: "/airlines",
  FETCH_AIRLINE: "/airlines/%s",
  FETCH_RATING: "/airlines/averageRating/%s",
  RATE_AIRLINE: "/airlines/rating"
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

  rateAirline = rateData => {
    return this.getApiClient().post(ENDPOINTS.RATE_AIRLINE, rateData);
  };
}

export default new AirlineService();
