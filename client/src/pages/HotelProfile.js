import React from "react";
import HotelInformation from "../components/hotel/Information";
import HotelRooms from "../components/hotel/Rooms";

const HotelProfile = ({ match }) => {
  return (
    <div>
      <HotelInformation hotelId={match.params.id} />
      <HotelRooms hotelId={match.params.id} />
    </div>
  );
};

export default HotelProfile;
