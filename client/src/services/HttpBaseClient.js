import axios from "axios";
import { API_BASE } from "../constants/api";

class HttpBaseClient {
  constructor() {
    this.client = axios.create({ baseURL: API_BASE.URL });
    this.setInterceptor();
  }

  setInterceptor() {}

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
