import HttpBaseClient from "../HttpBaseClient";

const ENDPOINTS = {
  AUTH_REGISTRATION: "/auth/registration",
  AUTH_LOGIN: "/auth/login"
};

class AuthService extends HttpBaseClient {
  login = async credentials => {
    const { data } = await this.getApiClient().post(
      ENDPOINTS.AUTH_LOGIN,
      credentials
    );

    await window.localStorage.setItem("token", data.token);
    await window.localStorage.setItem("role", data.role);
    await window.localStorage.setItem("email", data.email);

    this.attachHeaders({
      Authorization: `Bearer ${data.token}`
    });

    return { data };
  };

  registration = userData => {
    return this.getApiClient().post(ENDPOINTS.AUTH_REGISTRATION, userData);
  };
}

export default new AuthService();
