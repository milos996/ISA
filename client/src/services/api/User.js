import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  SAVE_USER: "/users",
  FETCH_FRIENDS: "/users/listOfFriends/%s",
  SAVE_PASSWORD: "/users/password/update/",
  REMOVE_FRIEND: "/users/friendship/mineId=%s&friendsId=%s",
  FRIENDSHIP: "/users/friendshipRequest",
  SEARCH: "/users/search/mineId=%s&userName=%s",
  USERS_WITHOUT_ENTITY: "/users/no/entity",
  FETCH: "/users/%s",
  FRIENDSHIP_REQUESTS: "/users/friendship/requests/%s",
  UPDATE_FRIENDSHIP_REQUEST: "/users/friendship/update"
};

class UserService extends HttpBaseClient {
  saveUser = userData => {
    return this.getApiClient().put(ENDPOINTS.SAVE_USER, userData);
  };

  fetchFriends = userId => {
    return this.getApiClient().get(format(ENDPOINTS.FETCH_FRIENDS, userId));
  };
  //didn't finish on backend
  savePassword = password => {
    return this.getApiClient().put(ENDPOINTS.SAVE_PASSWORD, { password });
  };

  sendFriendshipRequest = (senderUserId, invitedUserId) => {
    return this.getApiClient().post(ENDPOINTS.FRIENDSHIP, {
      senderUserId,
      invitedUserId
    });
  };

  removeFriend = (mineId, friendsId) => {
    return this.getApiClient().delete(
      format(ENDPOINTS.REMOVE_FRIEND, mineId, friendsId)
    );
  };

  searchByName = (mineId, userName) => {
    return this.getApiClient().get(format(ENDPOINTS.SEARCH, mineId, userName));
  };
  fetchUsersWithoutEntity = () => {
    return this.getApiClient().get(ENDPOINTS.USERS_WITHOUT_ENTITY);
  };

  fetchUser = userId => {
    return this.getApiClient().get(format(ENDPOINTS.FETCH, userId));
  };

  fetchFriendshipRequests = userId => {
    return this.getApiClient().get(
      format(ENDPOINTS.FRIENDSHIP_REQUESTS, userId)
    );
  };

  updateFriendshipRequest = friendship => {
    this.getApiClient().put(ENDPOINTS.UPDATE_FRIENDSHIP_REQUEST, friendship);
  };
}
export default new UserService();
