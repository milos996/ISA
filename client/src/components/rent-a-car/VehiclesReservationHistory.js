import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import MaterialTable from "material-table";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Button from "@material-ui/core/Button";
import { selectVehiclesReservation } from "../../store/user/selectors";
import { cancelVehicleReservation } from "../../store/rent-a-car/actions";
import RentACarRating from "./RentACarRating";
import VehicleRating from "./VehicleRating";
import AccountBoxIcon from "@material-ui/icons/AccountBox";

export default function VehiclesReservation() {
  const email = window.localStorage.getItem("email");
  const classes = useStyles();
  const [modalStyle] = React.useState(getModalStyle);
  const dispatch = useDispatch();
  const vehicles = useSelector(selectVehiclesReservation);

  const acc = "../../assets/star-full.png";

  const [RRModalVisibility, setRRModalVisibility] = useState(false);
  const [RVModalVisibility, setRVModalVisibility] = useState(false);
  const [selectedReservationId, setSelectedReservationId] = useState();

  function handleCancel(reservationId) {
    dispatch(cancelVehicleReservation(reservationId));
  }

  function closeModal() {
    setRRModalVisibility(false);
    setRVModalVisibility(false);
  }

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
      icon: "commute",
      tooltip: "Rate rent a car",
      onClick: (event, rowData) => {
        setSelectedReservationId(rowData.reservationId);
        setRRModalVisibility(true);
      }
    },
    {
      icon: "motorcycle",
      tooltip: "Rate vehicle",
      onClick: (event, rowData) => {
        setSelectedReservationId(rowData.reservationId);
        setRVModalVisibility(true);
      }
    }
  ];

  const columns = [
    { title: "Vehicle", field: "vehicle" },
    { title: "Owner", field: "rentACar" },
    { title: "Address", field: "address" },
    { title: "From date", field: "pickUpDate" },
    { title: "Till date", field: "dropOffDate" },
    { title: "Price", field: "price" },
    { title: "Vehicle rating", field: "rating" }
  ];

  return (
    <div>
      <Modal open={RRModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <RentACarRating reservationId={selectedReservationId} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Modal open={RVModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <VehicleRating reservationId={selectedReservationId} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <MaterialTable
        title="History"
        columns={columns}
        data={vehicles}
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
