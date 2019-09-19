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
  const { [id]: serviceWithId } = state.services;

  if (shouldDelete) {
    return {
      ...state,
      services: {
        ...state.services,
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
      ...state.services,
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
  const index = state.rooms.findIndex(val => val.id === roomId);

  return {
    ...state,
    rooms: [
      ...state.rooms.slice(0, index),
      ...state.rooms.slice(index + 1, state.rooms.length)
    ]
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

export function putRoomDetailsChange(state, { id, data }) {
  const index = state.rooms.findIndex(val => val.id === id);

  return {
    ...state,
    rooms: [
      ...state.rooms.slice(0, index),
      { ...data, id },
      ...state.rooms.slice(index + 1, state.rooms.length)
    ]
  };
}
