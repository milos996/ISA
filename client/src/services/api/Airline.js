import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  SAVE_AIRLINE: "/airlines",
  FETCH_AIRLINE: "/airlines/%s",
  RATE_AIRLINE: "/airlines/rating",
  FETCH_RATING: "/airlines/airline/%s/average-rating",
  FETCH_AIRLINES: "/airlines/all",
  SORT_AIRLINES: "/airlines/sort?by=%s",
  INCOME: "/airlines/%s/income?startDate=%s&endDate=%s",
  FETCH_AIRLINE_ADMIN: "/airlines/airline-admin",
  SOLD_TICKETS: "/airlines/%s/sold-tickets?startDate=%s&endDate=%s"
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

  showIncome = payload => {
    return this.getApiClient().get(
      format(ENDPOINTS.INCOME, payload.id, payload.startDate, payload.endDate)
    );
  };

  fetchAirlineAdmin = () => {
    return this.getApiClient().get(ENDPOINTS.FETCH_AIRLINE_ADMIN);
  };

  showSoldTickets = payload => {
    return this.getApiClient().get(
      format(
        ENDPOINTS.SOLD_TICKETS,
        payload.id,
        payload.startDate,
        payload.endDate
      )
    );
  };
}

export default new AirlineService();
