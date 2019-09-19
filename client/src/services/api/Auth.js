import HttpBaseClient from "../HttpBaseClient";
import { history } from "../../index";

const ENDPOINTS = {
  AUTH_REGISTRATION: "/auth/registration",
  AUTH_LOGIN: "/auth/login",
  RENT_A_CAR_ADMIN_PASSWORD_CHANGE: "/auth/password/update"
};

class AuthService extends HttpBaseClient {
  login = async credentials => {
    const { data } = await this.getApiClient().post(
      ENDPOINTS.AUTH_LOGIN,
      credentials
    );

    await localStorage.setItem("token", data.token);
    await localStorage.setItem("role", data.role);
    await localStorage.setItem("email", data.email);
    await localStorage.setItem("userID", data.id);

    this.attachHeaders({
      Authorization: `Bearer ${data.token}`
    });

    if (data.role === "RENT_A_CAR_ADMIN") {
      localStorage.setItem("firstLogin", data.rentACarAdminFirstLogin);
      if (data.rentACarAdminFirstLogin == true)
        history.push("/rent-a-car-admin/update-password");
      else history.push("/");
    } else {
      history.push("/");
    }

    return { data };
  };

  registration = userData => {
    return this.getApiClient().post(ENDPOINTS.AUTH_REGISTRATION, userData);
  };

  changeRentACarAdminPassword = passwordChange => {
    return this.getApiClient().post(
      ENDPOINTS.RENT_A_CAR_ADMIN_PASSWORD_CHANGE,
      passwordChange
    );
  };
}

export default new AuthService();
