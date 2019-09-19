import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch, useSelector } from "react-redux";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import DeleteIcon from "@material-ui/icons/Delete";
import IconButton from "@material-ui/core/IconButton";
import Modal from "@material-ui/core/Modal";
import IsaDialog from "../UI/IsaDialog";
import EditVehicle from "./EditVehicle";
import { deleteVehicle } from "../../store/rent-a-car/actions";
import { selectVehicleSearchInformation } from "../../store/rent-a-car/selectors";
import { createVehicleReservation } from "./../../store/rent-a-car/actions";

export default function RentACarVehicle({ vehicle }) {
  const role = window.localStorage.getItem("role");
  const classes = useStyles();
  const dispatch = useDispatch();
  const [modalStyle] = React.useState(getModalStyle);
  const [isEditModalVisible, setEditModalVisibility] = useState(false);
  const [isDialogForDeleteVisible, setDialogVisibility] = useState(false);

  const info = useSelector(selectVehicleSearchInformation);

  function handleReserve() {
    if (
      info.pickUpDate === "" ||
      info.dropOffDate === "" ||
      info.pickUpLocation === "" ||
      info.dropOffLocation === ""
    ) {
      alert(
        "You need to search for the vehicle first!\nSelect 'pickup-dropoff location' and 'pickup-dropoff date'"
      );
      return;
    }

    const vehicleId = vehicle.id;
    dispatch(createVehicleReservation({ vehicleId, info }));
    closeModal();
  }

  function handleDeleteVehicle() {
    dispatch(deleteVehicle(vehicle.id));
    setDialogVisibility(false);
  }

  function closeModal() {
    setEditModalVisibility(false);
  }

  return (
    <Card className={classes.card}>
      <Modal open={isEditModalVisible}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <EditVehicle vehicle={vehicle} closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <CardContent>
        <Typography variant="h5" component="h2">
          {vehicle.brand}
        </Typography>
        <Typography color="textSecondary">{vehicle.model}</Typography>
        <Typography variant="body2" component="p">
          <strong>
            {" "}
            {vehicle.type} {vehicle.brand} {vehicle.model}, from{" "}
            {vehicle.yearOfProduction}.
          </strong>
          <div>
            <br /> Price for this vehicle:{" "}
            <strong>{vehicle.pricePerDay}$</strong>.
          </div>
          <br />
          For {vehicle.numberOfSeats} people.
          <br /> Rating: <strong>{vehicle.rating}</strong>
        </Typography>
        <br />
        {role === "RENT_A_CAR_ADMIN" ? (
          <Typography variant="body2" component="p">
            For update and more information about this vehicle click on{" "}
            <strong>UPDATE</strong>
          </Typography>
        ) : (
          <Typography variant="body2" component="p">
            If you want to reserve this vehicle click on{" "}
            <strong>RESERVE</strong>
          </Typography>
        )}
      </CardContent>
      <CardActions>
        {role === "RENT_A_CAR_ADMIN" ? (
          <div>
            <Button size="small" onClick={() => setEditModalVisibility(true)}>
              UPDATE
            </Button>
            <IconButton
              aria-label="delete"
              onClick={() => {
                setDialogVisibility(true);
              }}
            >
              <DeleteIcon fontSize="small" />
            </IconButton>
          </div>
        ) : null}
        {role === "USER" ? (
          <Button size="small" onClick={handleReserve}>
            Reserve
          </Button>
        ) : null}
      </CardActions>
      <IsaDialog
        isVisible={isDialogForDeleteVisible}
        title={`Are you sure you want to delete vehicle '${vehicle.brand}${vehicle.model}' ?`}
        handleClose={() => {
          setDialogVisibility(false);
        }}
        callYesAction={handleDeleteVehicle}
      />
    </Card>
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
    width: 450,
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #000",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  card: {
    width: 250,
    height: 350,
    marginBottom: 25,
    marginRight: 25,
    padding: 15,
    paddingBottom: 15
  },
  bullet: {
    display: "inline-block",
    margin: "0 2px",
    transform: "scale(0.8)"
  },
  title: {
    fontSize: 14
  },
  pos: {
    marginLeft: 5,
    marginBottom: 12
  }
}));
