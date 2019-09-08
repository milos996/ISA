import axios from "axios";
import { API_BASE } from "../constants/api";

class HttpBaseClient {
  constructor() {
    this.client = axios.create({
      baseURL: API_BASE.URL
    });
    // this.setInterceptor();
  }

  setInterceptor() {
    this.client.interceptors.request.use(async config => {
      const token = await JSON.parse(localStorage.getItem("token"));

      if (!!token) {
        Object.assign(config.headers, {
          Authorization: `Bearer ${token}`
        });
      }

      return config;
    });
  }

  attachHeaders(headers) {
    Object.assign(this.client.defaults.headers, headers);
  }

  detachHeader(headerKey) {
    delete this.client.defaults.headers[headerKey];
  }

  getApiClient() {
    return this.client;
  }
}

export default HttpBaseClient;
