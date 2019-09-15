import {
  PUT_USER_DATA,
  PUT_USER_TOKEN,
  PUT_FRIENDS_DATA,
  PUT_FOUND_USERS_DATA
} from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  data: {
    id: "",
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    state: "",
    city: "",
    phoneNumber: ""
  },
  friends: [
    {
      id: "1",
      firstname: "Dejan",
      lastname: "Dejanovic",
      email: "dejan@gmail.com"
    },
    {
      id: "2",
      firstname: "Bojan",
      lastname: "Bojanovic",
      email: "bojan@gmail.com"
    }
  ],
  token: null,
  service: null,
  hotel: {
    id: "123123",
    name: "Hotel1",
    description:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla molestie nisi erat, hendrerit molestie felis fermentum non. Sed nec euismod massa, non volutpat elit. Aliquam non accumsan quam. Pellentesque venenatis nec tellus rhoncus tempor. Donec imperdiet tortor dapibus vestibulum condimentum. Cras tristique magna eros, quis sollicitudin risus rutrum at. Sed laoreet semper ex. Nullam ligula felis, mattis in ante at, euismod luctus risus. Curabitur tristique rhoncus orci, sed faucibus velit auctor at. Nullam risus ex, venenatis id massa sit amet, finibus vehicula orci. Sed consectetur, purus eu posuere pulvinar, magna turpis imperdiet risus, eget sodales felis risus non orci. Sed sodales venenatis arcu, eu dictum nulla varius in. Morbi nec accumsan orci. Vivamus facilisis orci sed felis auctor porttitor. Mauris semper vulputate congue.",
    address: {
      street: "Ulica",
      city: "Novi Sad",
      country: "Srbija",
      long: 119.0,
      lat: 40
    },
    services: {
      122: {
        id: 122,
        name: "air-conditioning",
        price: null,
        selected: false
      },
      124: {
        id: 124,
        name: "air-conditioning",
        price: null,
        selected: true
      },
      125: {
        id: 125,
        name: "air-conditioning",
        price: null,
        selected: true
      },
      128: {
        id: 128,
        name: "air-conditioning",
        price: null,
        selected: true
      },
      123: {
        id: 123,
        name: "wi-fi",
        price: 0.9,
        selected: true
      }
    },
    report: {},
    rooms: {
      0: {
        id: 0,
        number: 2,
        floor: 1,
        priceSummer: 12312,
        priceWinter: 0,
        priceSpring: 0,
        priceAutumn: 0,
        numberOfPeople: 2
      },
      2: {
        id: 2,
        number: 3,
        floor: 1,
        priceSummer: 123123,
        priceWinter: 0,
        priceSpring: 0,
        priceAutumn: 0,
        numberOfPeople: 2
      },
      3: {
        id: 3,
        number: 4,
        floor: 1,
        priceSummer: 1211658458,
        priceWinter: 0,
        priceSpring: 0,
        priceAutumn: 0,
        numberOfPeople: 2
      },
      4: {
        id: 4,
        number: 2,
        floor: 2,
        priceSummer: 679569,
        priceWinter: 0,
        priceSpring: 0,
        priceAutumn: 0,
        numberOfPeople: 2
      },
      5: {
        id: 5,
        number: 3,
        floor: 2,
        priceSummer: 1,
        priceWinter: 0,
        priceSpring: 0,
        priceAutumn: 0,
        numberOfPeople: 2
      },
      123: {
        id: 123,
        number: 10,
        floor: 2,
        priceSummer: 2,
        priceWinter: 0,
        priceSpring: 0,
        priceAutumn: 0,
        numberOfPeople: 2
      }
    }
  },
  foundUsers: []
};

const userReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_USER_DATA]: computationFunctions.putUserData,
  [PUT_USER_TOKEN]: computationFunctions.putUserToken,
  [PUT_FRIENDS_DATA]: computationFunctions.putFriendsData,
  [PUT_FOUND_USERS_DATA]: computationFunctions.putFoundUsersData
};

export default userReducer;
