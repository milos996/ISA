import { PUT_AIRLINES } from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  data: {},
  airlines: [
    {
      id: "123",
      name: "AirSerbia",
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis congue fringilla nisi ac viverra. Sed risus enimerdiet. Quisque vulputate pulvinar ante eget sagittis.",
      address: {
        city: "Beograd",
        country: "Srbija",
        street: "Nemanjina",
        longitude: 44.27,
        latitude: 20.29
      },
      checkingInSuitcasePrice: 150.2,
      handLuggagePrice: 50.3
    },
    {
      id: "122",
      name: "AirCG",
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis congue fringilla nisi ac viverra. Sed risus enimerdiet. Quisque vulputate pulvinar ante eget sagittis.",
      address: {
        city: "Tivat",
        country: "Montenegro",
        street: "Nemanjina",
        longitude: 44.27,
        latitude: 20.29
      },
      checkingInSuitcasePrice: 150.2,
      handLuggagePrice: 50.3
    }
  ],

  searchResults: [
    {
      id: "009",
      departureTime: "02/05/2019",
      arrivalTime: "03/05/2019",
      duration: "10:02:00",
      length: 250.22,
      price: 125.2,
      airlineDestination: {
        destination: {
          id: "1",
          state: "Crna Gora",
          city: "Tivat"
        },
        airline: {}
      },
      airplane: {
        id: "01",
        mark: "A111",
        numberOfRows: 6,
        numberOfColumnsPerSegment: 4,
        numberOfSegments: 3,
        airline: {
          id: "123",
          name: "AirSerbia",
          description:
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis congue fringilla nisi ac viverra. Sed risus enim, bibendum vel vulputate gravida, aliquet sit amet arcu. Nullam elementum ullamcorper facilisis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris dignissim id purus vel gravida. Proin fringilla aliquam nunc, non iaculis risus pulvinar vel. Morbi finibus at velit rutrum fringilla. Etiam volutpat ac ante sed imperdiet. Quisque vulputate pulvinar ante eget sagittis.",
          address: {
            city: "Beograd",
            country: "Srbija",
            street: "Nemanjina",
            longitude: 44.27,
            latitude: 20.29
          },
          checkingInSuitcasePrice: 150.2,
          handLuggagePrice: 50.3
        }
      }
    }
  ]
};

const airplaneTicketReducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_AIRLINES]: computationFunctions.putAirlines
};

export default airplaneTicketReducer;
