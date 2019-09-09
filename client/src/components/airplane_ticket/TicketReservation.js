import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import Airlines from "./Airlines";
import SearchFlights from "./SearchFlights";

export default function TicketReservation() {
  const dispatch = useDispatch();

  return (
    <div id="mainWraper" className="main-wrapper">
      <SearchFlights></SearchFlights>
      <Airlines></Airlines>
    </div>
  );
}
