import * as computationFunctions from "./computation-functions";
import { 
    PUT_AIRLINE_DETAILS,
    PUT_AIRLINE_DESTINATIONS,
    PUT_AIRLINE_FLIGHTS,
    PUT_TICKETS_FOR_FAST_RESERVATION,
    PUT_AIRLINE_AIRPLANES
} from "../constants";

const initialState = {
    data: {
        id: "123",
        name: "AirSerbia",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis congue fringilla nisi ac viverra. Sed risus enim, bibendum vel vulputate gravida, aliquet sit amet arcu. Nullam elementum ullamcorper facilisis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris dignissim id purus vel gravida. Proin fringilla aliquam nunc, non iaculis risus pulvinar vel. Morbi finibus at velit rutrum fringilla. Etiam volutpat ac ante sed imperdiet. Quisque vulputate pulvinar ante eget sagittis.",
        address: {
            city: "Beograd",
            country: "Srbija",
            street: "Nemanjina",
            longitude: 44.27,
            latitude: 20.29
        },
        checkingInSuitcasePrice: 150.2,
        handLuggagePrice: 50.3,
    },
    destinations: [

        {
            destination: {
                id: "1",
                state: "Crna Gora",
                city: "Tivat"
            },
            airline: {}
        },
        {
            destination: {
                id: "2",
                state: "Spanija",
                city: "Madrid"
            },
            airline: {}
        }
    ],
    flights: [
        {
            flight:{
                id: "007",
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
                    airline:{}                
                },
                airplane: {}

            },
            rating: 0.0 
            
                       
        }
    ],
    ticketsForFastReservation: [
        {
            id: "005",
            departureTime: "02/05/2019",
            arrivalTime: "03/05/2019",
            duration: "10:02:00",
            length: 250.22,
            price: 100.0,
            airlineDestination: {
                destination: {
                    id: "1",
                    state: "Crna Gora",
                    city: "Tivat"
                },
                airline:{}                
            },
            airplane: {}              
        }
    ],
    airplanes: 
       [
        {
            id: "01",
            mark: "A111",
            numberOfRows: 6,
            numberOfColumnsPerSegment:4,
            numberOfSegments: 3,
            airline: {}
        },
        {
            id: "02",
            mark: "A112",
            numberOfRows: 4,
            numberOfColumnsPerSegment:3,
            numberOfSegments: 3,
            airline: {}
        },
        {
            id: "03",
            mark: "A112",
            numberOfRows: 4,
            numberOfColumnsPerSegment:3,
            numberOfSegments: 3,
            airline: {}
        }
    ],
    rating: 0.0
    
    
}

const airlineReducer = ( state = initialState, { type, payload} ) => {
    if(actionHandler.hasOwnProperty(type)){
        return actionHandler[type](state, payload);
    }
    return state;
};
const actionHandler = {
    [PUT_AIRLINE_DETAILS]: computationFunctions.putAirlineDetails,
    [PUT_AIRLINE_DESTINATIONS]: computationFunctions.putAirlineDestinations,
    [PUT_AIRLINE_FLIGHTS]: computationFunctions.putAirlineFlights,
    [PUT_TICKETS_FOR_FAST_RESERVATION]: computationFunctions.putTicketsForFastReservation,
    [PUT_AIRLINE_AIRPLANES]: computationFunctions.putAirlineAirplanes
};

export default airlineReducer;