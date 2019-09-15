import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  SAVE_USER: "/users",
  FETCH_FRIENDS: "/users/listOfFriends/%s",
  SAVE_PASSWORD: "/users/password/update/",
  FETCH_BY_NAME: "/users/find/%s",
  REMOVE_FRIEND: "/users/friendship",
  FRIENDSHIP: "/users/friedship/%s",
  SEARCH: "/users/search/%s",
  USERS_WITHOUT_ENTITY: "/users/no/entity"
};

class UserService extends HttpBaseClient {
  saveUser = userData => {
    return this.getApiClient().put(ENDPOINTS.SAVE_USER, userData);
  };

  fetchFriends = userId => {
    return this.getApiClient.get(format(ENDPOINTS.FETCH_FRIENDS, userId));
  };
  //didn't finish on backend
  savePassword = password => {
    return this.getApiClient().put(ENDPOINTS.SAVE_PASSWORD, { password });
  };

  fetchByName = name => {
    return this.getApiClient().get(format(ENDPOINTS.FETCH_BY_NAME), name);
  };

  sendFriendshipRequest = invitedUserId => {
    return this.getApiClient().post(
      format(ENDPOINTS.FRIENDSHIP, invitedUserId)
    );
  };

  removeFriend = friendsId => {
    return this.getApiClient().delete(format(ENDPOINTS.FRIENDSHIP, friendsId));
  };

  searchByName = userName => {
    return this.getApiClient().get(format(ENDPOINTS.SEARCH, userName));
  };
  fetchUsersWithoutEntity = () => {
    return this.getApiClient().get(ENDPOINTS.USERS_WITHOUT_ENTITY);
  };
}
export default new UserService();
