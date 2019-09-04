import { PUT_USER_DATA, PUT_USER_TOKEN, PUT_FRIENDS_DATA, PUT_FOUND_USERS_DATA } from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  data: {
    id: "123",
    firstname: "Uros",
    lastname: "Kojovic",
    email:  "uros@gmail.com",
    password: "123",
    state: "Serbia",
    city: "Cacak",
    phone: "060000111"
  },
  friends: 
    [{
    id: "1",
    firstname: "Dejan",
    lastname: "Dejanovic",
    email:  "dejan@gmail.com",
  },
  {
    id: "2",
    firstname: "Bojan",
    lastname: "Bojanovic",
    email:  "bojan@gmail.com",
  }],
  token: null,
  service: null,
  foundUsers: [

    {
      id: "1",
      firstname: "Dejan",
      lastname: "Dejanovic",
      email:  "dejan@gmail.com",
    },
    {
      id: "2",
      firstname: "Dejan",
      lastname: "Bojanovic",
      email:  "bojan@gmail.com",
    }
  ]
}

const userReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_USER_DATA]: computationFunctions.putUserData,
  [PUT_USER_TOKEN]:  computationFunctions.putUserToken,
  [PUT_FRIENDS_DATA]: computationFunctions.putFriendsData,
  [PUT_FOUND_USERS_DATA]: computationFunctions.putFoundUsersData
};

export default userReducer;
