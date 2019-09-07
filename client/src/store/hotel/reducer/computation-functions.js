export function putHotelDetails(state, payload) {
  
  return {
    ...state,
    data: {
      ...state.data,
      ...payload
    }
  };
}

export function changeHotelServices(
  state,
  { id = null, price, shouldDelete = false }
) {
  const { [id]: serviceWithId, ...restState } = state.services;

  if (shouldDelete) {
    return {
      ...state,
      services: {
        ...restState,
        [id]: {
          ...serviceWithId,
          selected: false
        }
      }
    };
  }

  return {
    ...state,
    services: {
      ...restState,
      [id]: {
        ...serviceWithId,
        price
      }
    }
  };
}

export function addNewService(state, id) {
  const { [id]: serviceWithId, ...restServices } = state.services;

  return {
    ...state,
    services: {
      ...restServices,
      [id]: {
        ...serviceWithId,
        selected: true
      }
    }
  };
}

export function setServices(state, data) {
  let services = {};

  data.forEach(service => {
    services[service.id] = { ...service };
  });

  return {
    ...state,
    services
  };
}

export function putHotelLocationInformation(state, payload) {
  return {
    ...state,
    data: {
      ...state.data,
      address: {
        ...state.data.address,
        ...payload
      }
    }
  };
}

export function deleteRoomWithId(state, roomId) {
  const { [roomId]: deleteRoom, ...restRooms } = state.rooms;

  return {
    ...state,
    rooms: restRooms
  };
}

export function putHotels(state, hotels) {
  return {
    ...state,
    hotels
  };
}

export function putHotelRooms(state, rooms) {
  return {
    ...state,
    rooms
  };
}
