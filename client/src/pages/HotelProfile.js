import React from "react";
import HotelInformation from "../components/hotel/Information";
import HotelRooms from "../components/hotel/Rooms";

export default function HotelProfilePage({ match }) {
  return (
    <div>
      <HotelInformation hotelId={match.params.id} />
      <HotelRooms hotelId={match.params.id} />
    </div>
  );
}
