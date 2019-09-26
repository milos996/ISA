import React, { useState, useEffect } from "react";
import AirlineInformation from "../components/airline/Information";
import Airplanes from "../components/airline/Airplanes";
import BusinessReport from "../components/airline/BusinessReport";

export default function AirlineProfile({ match }) {
  return (
    <div>
      <AirlineInformation airlineId={match.params.id} />
      <Airplanes airlineId={match.params.id} />
      <BusinessReport airlineId={match.params.id} />
    </div>
  );
}
