import axios from "axios";
const ENDPOINTS = {
  GET_COUNTRY_BASED_ON_LAT_LNG:
    "http://api.geonames.org/findNearbyPlaceNameJSON"
};

class LocationService {
  getLocationBasedOnLatLong = data => {
    return axios.get(
      `${ENDPOINTS.GET_COUNTRY_BASED_ON_LAT_LNG}?lat=${data.lat}&lng=${data.lng}&username=milos996`
    );
  };
}

const locationService = new LocationService();
export default locationService;
