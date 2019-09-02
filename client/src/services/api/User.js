import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const  ENDPOINTS = {

    SAVE_USER: "/users/update"
};

class UserService extends HttpBaseClient{

    saveUser = userData => {
        return this.getApiClient().put(ENDPOINTS.SAVE_USER, userData);
    }

    fetchUserData = userId => {
        //...
    }
}
export default new UserService();