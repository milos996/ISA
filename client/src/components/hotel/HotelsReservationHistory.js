import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import MaterialTable from "material-table";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";

import { selectHotelsReservation } from "../../store/user/selectors";
import { selectVehicles } from "../../store/rent-a-car/selectors";
import HotelRating from "./HotelRating";
import RoomRating from "./RoomRating";

export default function HotelsReservation() {
  const email = window.localStorage.getItem("email");
  const dispatch = useDispatch();
  const hotels = useSelector(selectHotelsReservation);
  const classes = useStyles();
  const [modalStyle] = React.useState(getModalStyle);

  const test = useSelector(selectVehicles);
  const [RRModalVisibility, setRRModalVisibility] = useState(false);
  const [RHModalVisibility, setRHModalVisibility] = useState(false);
  const [selectedReservationId, setSelectedReservationId] = useState();

  function closeModal() {
    setRRModalVisibility(false);
    setRHModalVisibility(false);
  }

  function handleCancel(reservationId) {}

  const columns = [
    { title: "Reservation ID", field: "reservationId" },
    { title: "Hotel", field: "brand" },
    { title: "Address", field: "address" },
    { title: "Room", field: "room" },
    { title: "From date", field: "fromDate" },
    { title: "Till date", field: "tillDate" },
    { title: "Price", field: "price" },
    { title: "Hotel rating", field: "hotelRating" }
  ];

  const actions = [
    {
      icon: "cancel",
      tooltip: "Cancel reservation",
      onClick: (event, rowData) => {
        handleCancel(rowData.reservationId);
        alert(
          "Cancel request for reservation '" +
            rowData.reservationId +
            "' has been sent!"
        );
      }
    },
    {
      icon: "apartment",
      tooltip: "Rate hotel",
      onClick: (event, rowData) => {
        setSelectedReservationId(rowData.reservationId);
        setRHModalVisibility(true);
      }
    },
    {
      icon: "hotel",
      tooltip: "Rate room",
      onClick: (event, rowData) => {
        setSelectedReservationId(rowData.reservationId);
        setRRModalVisibility(true);
      }
    }
  ];

  return (
    <div>
      <Modal open={RRModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <RoomRating reservationId={selectedReservationId} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Modal open={RHModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <HotelRating reservationId={selectedReservationId} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <MaterialTable
        title="History"
        columns={columns}
        data={hotels}
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
