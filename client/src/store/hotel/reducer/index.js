import {
  PUT_HOTEL_DETAILS,
  PUT_CHANGE_HOTEL_SERVICES,
  PUT_ADD_NEW_SERVICE,
  PUT_HOTEL_SERVICES,
  PUT_HOTEL_LOCATION_INFORMATION,
  PUT_DELETE_ROOM_WITH_ID,
  PUT_HOTELS,
  PUT_HOTEL_ROOMS
} from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  data: {
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
    }
  },
  report: {},
  services: {},
  hotels: [
    {
      id: 12,
      name: "hotel1",
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla molestie nisi erat, hendrerit molestie felis fermentum non.",
      address: {
        street: "Ulica",
        city: "Wien",
        country: "Austria",
        long: 119.0,
        lat: 40
      },
      mark: 6.7
    },
    {
      id: 13,
      name: "hotel nbla",
      description:
        " Pellentesque venenatis nec tellus rhoncus tempor. Donec imperdiet tortor dapibus vestibulum condimentum. Cras tristique magna eros, quis sollicitudin risus rutrum at. Sed laoreet semper ex. Nullam ligula felis, mattis in ante at, euismod luctus risus. Curabitur tristique rhoncus orci, sed faucibus velit auctor at. Nullam risus ex, venenatis id massa sit amet, finibus vehicula orci. Sed consectetur, purus eu posuere pulvinar,",
      address: {
        street: "Ulica",
        city: "Nis",
        country: "Srbija",
        long: 119.0,
        lat: 40
      },
      mark: 6.7
    },
    {
      id: 121525,
      name: "hotel blaa",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ",
      address: {
        street: "Ulica",
        city: "Novi Sad",
        country: "Srbija",
        long: 19.8335,
        lat: 45.2671
      },
      mark: 6.7
    },
    {
      id: 121233,
      name: "asdasd hotel",
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla molestie nisi erat, hendrerit molestie felis fermentum non. Sed nec euismod massa, non volutpat elit. Aliquam non accumsan quam. Pellentesque venenatis nec tellus rhoncus tempor. Donec imperdiet tortor dapibus vestibulum condimentum. Cras tristique magna eros, quis sollicitudin risus rutrum at. Sed laoreet semper ex. Nullam ligula felis, mattis in ante at, euismod luctus risus. Curabitur tristique rhoncus orci, sed faucibus velit auctor at. Nullam risus ex, venenatis id massa sit amet, finibus vehicula orci. Sed consectetur, purus eu posuere pulvinar, magna turpis imperdiet risus, eget sodales felis risus non orci. Sed sodales venenatis arcu, eu dictum nulla varius in. Morbi nec accumsan orci. Vivamus facilisis orci sed felis auctor porttitor. Mauris semper vulputate congue.",
      address: {
        street: "Ulica",
        city: "Beograd",
        country: "Srbija",
        long: 20.48,
        lat: 44.7866
      },
      mark: 6.7
    },
    {
      id: 1523,
      name: "hotel nbla",
      description:
        " Pellentesque venenatis nec tellus rhoncus tempor. Donec imperdiet tortor dapibus vestibulum condimentum. Cras tristique magna eros, quis sollicitudin risus rutrum at. Sed laoreet semper ex. Nullam ligula felis, mattis in ante at, euismod luctus risus. Curabitur tristique rhoncus orci, sed faucibus velit auctor at. Nullam risus ex, venenatis id massa sit amet, finibus vehicula orci. Sed consectetur, purus eu posuere pulvinar,",
      address: {
        street: "Ulica",
        city: "Nis",
        country: "Srbija",
        long: 21.8958,
        lat: 43.3209
      },
      mark: 6.7
    },
    {
      id: 1512,
      name: "hotel blaa",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ",
      address: {
        street: "Ulica",
        city: "Novi Sad",
        country: "Srbija",
        long: 19.8335,
        lat: 45.2671
      },
      mark: 6.7
    },
    {
      id: 512,
      name: "asdasd hotel",
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla molestie nisi erat, hendrerit molestie felis fermentum non. Sed nec euismod massa, non volutpat elit. Aliquam non accumsan quam. Pellentesque venenatis nec tellus rhoncus tempor. Donec imperdiet tortor dapibus vestibulum condimentum. Cras tristique magna eros, quis sollicitudin risus rutrum at. Sed laoreet semper ex. Nullam ligula felis, mattis in ante at, euismod luctus risus. Curabitur tristique rhoncus orci, sed faucibus velit auctor at. Nullam risus ex, venenatis id massa sit amet, finibus vehicula orci. Sed consectetur, purus eu posuere pulvinar, magna turpis imperdiet risus, eget sodales felis risus non orci. Sed sodales venenatis arcu, eu dictum nulla varius in. Morbi nec accumsan orci. Vivamus facilisis orci sed felis auctor porttitor. Mauris semper vulputate congue.",
      address: {
        street: "Ulica",
        city: "Beograd",
        country: "Srbija",
        long: 19.8335,
        lat: 45.2671
      },
      mark: 6.7
    }
  ],
  rooms: []
};

const hotelReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_HOTEL_DETAILS]: computationFunctions.putHotelDetails,
  [PUT_CHANGE_HOTEL_SERVICES]: computationFunctions.changeHotelServices,
  [PUT_ADD_NEW_SERVICE]: computationFunctions.addNewService,
  [PUT_HOTEL_SERVICES]: computationFunctions.setServices,
  [PUT_HOTEL_LOCATION_INFORMATION]:
    computationFunctions.putHotelLocationInformation,
  [PUT_DELETE_ROOM_WITH_ID]: computationFunctions.deleteRoomWithId,
  [PUT_HOTELS]: computationFunctions.putHotels,
  [PUT_HOTEL_ROOMS]: computationFunctions.putHotelRooms
};

export default hotelReducer;
