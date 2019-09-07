import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
    SAVE_AIRLINE: "/airlines",
    FETCH_AIRLINE: "/airlines/%s",
    FETCH_RATING: "/airlines/averageRating/%s"
}

class AirlineService extends HttpBaseClient{

    saveAirline = details => {
        this.getApiClient().put(ENDPOINTS.SAVE_AIRLINE, details);
    }
  
    fetchAirlineDetails = airlineId => {
        return this.getApiClient().get(format(ENDPOINTS.FETCH_AIRLINE, airlineId));
    }

    getRating = airlineId => {
        return this.getApiClient().get(format(ENDPOINTS.FETCH_RATING, airlineId));
    }

}

export default new AirlineService();
