export function putAirlineDetails(state, payload){ 
    return {
        ...state,
        data: {
            ...state.data,
            ...payload
        }
    }
}

export function putAirlineDestinations(state, payload){
    return{
        ...state,
        destinations:{
            ...state.destinations,
            ...payload
        }
    }
}

export function putAirlineFlights(state, payload){
    return {
        ...state,
        flights:{
            ...state.flights,
            ...payload
        }
    }
}

export function putTicketsForFastReservation(state, payload){
    return {
        ...state,
        ticketsForFastReservation:{
            ...state.ticketsForFastReservation,
            ...payload
        }
    }
}

export function putAirlineAirplanes(state, payload){
    return {
        ...state,
        airplanes: {
            ...state.airplanes,
            ...payload
        }
    }
}

export function putAirlineRating(state, payload){
    return{
        ...state,
        rating: {
            ...state.rating,
            ...payload
        }
    }
}
