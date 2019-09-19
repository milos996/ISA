import React, { useState } from "react";
import Airlines from "./Airlines";
import SearchFlights from "./SearchFlights";

export default function TicketReservation() {
  return (
    <div id="mainWraper" className="main-wrapper">
      <SearchFlights></SearchFlights>
      <Airlines></Airlines>
    </div>
  );
}
