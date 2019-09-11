import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { saveVehicleDetails } from "./../../store/rent-a-car/actions";

export default function EditVehicle({ vehicle, closeModal }) {
  const role = window.localStorage.getItem("role");
  const classes = useStyles();
  const dispatch = useDispatch();
  const [brand, setBrand] = useState();
  const [model, setModel] = useState();
  const [yearOfProduction, setYearOfProduction] = useState();
  const [type, setType] = useState();
  const [numberOfSeats, setNumberOfSeats] = useState();

  function handleSave() {
    var id = vehicle.id;
    dispatch(
      saveVehicleDetails({
        id,
        brand,
        model,
        yearOfProduction,
        type,
        numberOfSeats
      })
    );
    closeModal();
  }

  return (
    <Container
      classes={{
        root: classes.serviceRow
      }}
    >
      {role === "RENT_A_CAR_ADMIN" ? (
        <Button
          variant="contained"
          color="primary"
          className={classes.button}
          onClick={handleSave}
        >
          Save
        </Button>
      ) : null}
      <TextField
        label="Brand"
        defaultValue={vehicle.brand}
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setBrand(currentTarget.value);
        }}
      />
      <TextField
        label="Model"
        defaultValue={vehicle.model}
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setModel(currentTarget.value);
        }}
      />
      <TextField
        label="Year of production"
        defaultValue={vehicle.yearOfProduction}
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setYearOfProduction(currentTarget.value);
        }}
      />
      <TextField
        label="Type"
        defaultValue={vehicle.type}
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setType(currentTarget.value);
        }}
      />
      <TextField
        label="Number of seats"
        defaultValue={vehicle.numberOfSeats}
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setNumberOfSeats(currentTarget.value);
        }}
      />
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    position: "absolute",
    left: "20%",
    top: "5%",
    width: "60%",
    display: "flex",
    flexDirection: "column"
  },
  serviceRow: {
    display: "flex",
    flexDirection: "column",
    width: "100%"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    flex: 1
  },
  margin: {
    margin: theme.spacing(1)
  },
  button: {
    margin: theme.spacing(1),
    width: "30%",
    marginLeft: "auto"
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120
  },
  selectEmpty: {
    marginTop: theme.spacing(2)
  },
  listScroll: {
    maxHeight: "370px",
    overflow: "scroll"
  }
}));
