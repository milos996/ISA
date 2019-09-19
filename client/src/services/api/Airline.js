import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  SAVE_AIRLINE: "/airlines",
  FETCH_AIRLINE: "/airlines/%s",
  //FETCH_RATING: "/airlines/averageRating/%s",
  RATE_AIRLINE: "/airlines/rating",
  FETCH_RATING: "/airlines/airline/%s/average-rating",
  FETCH_AIRLINES: "/airlines/all",
  SORT_AIRLINES: "/airlines/sort?by=%s"
};

class AirlineService extends HttpBaseClient {
  saveAirline = details => {
    this.getApiClient().put(ENDPOINTS.SAVE_AIRLINE, details);

    return this.getApiClient().put(ENDPOINTS.SAVE_AIRLINE, details);
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

  fetchAirlines = () => {
    return this.getApiClient().get(ENDPOINTS.FETCH_AIRLINES);
  };

  sortAirlines = payload => {
    return this.getApiClient().get(format(ENDPOINTS.SORT_AIRLINES, payload.by));
  };
}

export default new AirlineService();
