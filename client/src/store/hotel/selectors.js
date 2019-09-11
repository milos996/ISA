import { createSelector } from "reselect";

const reducer = "hotelReducer";

export const userDataSelector = state => state[reducer].data;

export const selectServices = () =>
  createSelector(
    state => state[reducer].services,
    (_, selected) => selected,
    (services, selected) =>
      Object.keys(services)
        .filter(serviceId => services[serviceId].selected === selected)
        .reduce(
          (currReducer, serviceId) => ({
            ...currReducer,
            [serviceId]: services[serviceId]
          }),
          {}
        )
  );

export const selectHotelServices = state => {
  return state[reducer].services;
};

export const selectHotelDetails = state => {
  return state[reducer].data;
};

export const selectHotelRooms = state => {
  return state[reducer].rooms;
};

export const selectHotels = state => {
  return state[reducer].hotels;
};

export const selectHotelRoomsForEdit = state => {
  const rooms = {};
  state[reducer].rooms.forEach(room => {
    rooms[room.id] = {
      ...room
    };
  });

  return rooms;
};
