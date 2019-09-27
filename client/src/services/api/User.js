import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  SAVE_USER: "/users",
  FETCH_FRIENDS: "/users/listOfFriends/%s",
  SAVE_PASSWORD: "/auth/password/update",
  FETCH_BY_NAME: "/users/find/%s",
  USER_INVITES: "/users/%s/invites",
  USER_ACCEPT_INVITE: "/users/%s/invites/accept",
  USER_DECLINE_INVITE: "/users/%s/invites/decline",
  REMOVE_FRIEND: "/users/friendship/friendsId=%s",
  FRIENDSHIP: "/users/friendship-request",
  SEARCH: "/users/search/userName=%s",
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
  savePassword = request => {
    return this.getApiClient().put(ENDPOINTS.SAVE_PASSWORD, request);
  };

  sendFriendshipRequest = invitedUserId => {
    return this.getApiClient().post(ENDPOINTS.FRIENDSHIP, { invitedUserId });
  };

  removeFriend = friendsId => {
    return this.getApiClient().delete(
      format(ENDPOINTS.REMOVE_FRIEND, friendsId)
    );
  };

  searchByName = userName => {
    return this.getApiClient().get(format(ENDPOINTS.SEARCH, userName));
  };
  fetchUsersWithoutEntity = () => {
    return this.getApiClient().get(ENDPOINTS.USERS_WITHOUT_ENTITY);
  };

  fetchUserInvites = userID => {
    return this.getApiClient.get(format(ENDPOINTS.USER_INVITES, userID));
  };

  acceptInvite = payload => {
    return this.getApiClient.post(
      format(ENDPOINTS.USER_ACCEPT_INVITE, payload.userId),
      payload.inviteId
    );
  };

  declineInvite = payload => {
    console.log(payload);
    return this.getApiClient.post(
      format(ENDPOINTS.USER_DECLINE_INVITE, payload.userId),
      payload.inviteId
    );
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
