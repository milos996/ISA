import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const  ENDPOINTS = {

    SAVE_USER: "/users",
    FETCH_FRIENDS: "/users/listOfFriends/%s",
    SAVE_PASSWORD: "/users/%s",
    FETCH_BY_NAME: "/users/find/%s",
    SEND_FRIENDSHIP_REQUEST: "/users/friendshipRequest",
    REMOVE_FRIEND: "/users"
};

class UserService extends HttpBaseClient{

    saveUser = userData => {
        return this.getApiClient().put(ENDPOINTS.SAVE_USER, userData);
    }

    fetchFriends = userId => {
        return this.getApiClient.get(format(ENDPOINTS.FETCH_FRIENDS, userId));
    }

    //not exists on backend yet
    savePassword = (password, userId) => {
        return this.getApiClient().put(format(ENDPOINTS.SAVE_PASSWORD, userId), password);
    }

    fetchByName = name => {
        return this.getApiClient().get(format(ENDPOINTS.FETCH_BY_NAME), name);
    }

    sendFriendshipRequest = (senderUserId, invitedUserId) => {
        return this.getApiClient().post(ENDPOINTS.SEND_FRIENDSHIP_REQUEST, senderUserId, invitedUserId);
    }

    removeFriend = (userId, friendsId) => {
        return this.getApiClient().delete(ENDPOINTS.REMOVE_FRIEND, userId, friendsId);
    }
}
export default new UserService();