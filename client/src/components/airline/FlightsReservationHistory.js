import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import MaterialTable from "material-table";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import { selectFlightsReservation } from "../../store/user/selectors";
import { fetchUserFlightsReservation } from "../../store/user/actions";
import { cancelFlight } from "../../store/airline/actions";
import { selectVehicles } from "../../store/rent-a-car/selectors";

import AirlineRating from "./AirlineRating";
import FlightRating from "./FlightRating";

export default function FlightsReservation() {
  const email = window.localStorage.getItem("email");
  const classes = useStyles();
  const [modalStyle] = React.useState(getModalStyle);
  const dispatch = useDispatch();
  const flights = useSelector(selectFlightsReservation);

  const [RFModalVisibility, setRFModalVisibility] = useState(false);
  const [RAModalVisibility, setRAModalVisibility] = useState(false);
  const test = useSelector(selectVehicles);
  const [selectedReservationId, setSelectedReservationId] = useState();

  function handleCancel(reservationId) {
    dispatch(cancelFlight(reservationId));
  }

  function closeModal() {
    setRAModalVisibility(false);
    setRFModalVisibility(false);
  }

  const columns = [
    { title: "Airline", field: "airline" },
    { title: "Departure time", field: "departureTime" },
    { title: "Return time", field: "returnTime" },
    { title: "Duration", field: "duration" },
    { title: "Price", field: "price" },
    { title: "Airline rating", field: "airlineRating" },
    { title: "Flight rating", field: "flightRating" }
  ];

  const actions = [
    {
      icon: "cancel",
      tooltip: "Cancel reservation",
      onClick: (event, rowData) => {
        handleCancel(rowData.ticketId);
        alert(
          "Cancel request for reservation '" +
            rowData.ticketId +
            "' has been sent!"
        );
      }
    },
    {
      icon: "flight",
      tooltip: "Rate airline",
      onClick: (event, rowData) => {
        setSelectedReservationId(rowData.ticketId);
        setRAModalVisibility(true);
      }
    },
    {
      icon: "payment",
      tooltip: "Rate flight",
      onClick: (event, rowData) => {
        setSelectedReservationId(rowData.ticketId);
        setRFModalVisibility(true);
      }
    }
  ];

  return (
    <div>
      <Modal open={RAModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <AirlineRating airplaneTicketId={selectedReservationId} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Modal open={RFModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <FlightRating airplaneTicketId={selectedReservationId} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <MaterialTable
        colorAction="#008080"
        title="History"
        columns={columns}
        data={flights}
        actions={actions}
      />
    </div>
  );
}

function getModalStyle() {
  const top = 50;
  const left = 50;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`
  };
}

const useStyles = makeStyles(theme => ({
  paper: {
    position: "absolute",
    width: 400,
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #000",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  root: {
    display: "flex",
    flexDirection: "column"
  }
}));
