import React, { useState } from "react";
import { useDispatch } from "react-redux";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { createRentACarVehicle } from "./../../store/rent-a-car/actions";

export default function CreateVehicle({ rentACarId, closeModal }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [vehicle] = useState({});

  function handleSaveButton() {
    dispatch(createRentACarVehicle({ vehicle, rentACarId }));
    closeModal();
  }

  return (
    <Container
      classes={{
        root: classes.serviceRow
      }}
    >
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleSaveButton}
      >
        Save
      </Button>
      <TextField
        label="Brand"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          vehicle.brand = currentTarget.value;
        }}
      />
      <TextField
        label="Model"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          vehicle.model = currentTarget.value;
        }}
      />
      <TextField
        label="Type"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          vehicle.type = currentTarget.value;
        }}
      />
      <TextField
        label="Year of production"
        className={classes.textField}
        margin="normal"
        type="number"
        onChange={({ currentTarget }) => {
          vehicle.yearOfProduction = currentTarget.value;
        }}
      />
      <TextField
        label="Number of seats"
        className={classes.textField}
        margin="normal"
        type="number"
        onChange={({ currentTarget }) => {
          vehicle.numberOfSeats = currentTarget.value;
        }}
      />
      <TextField
        label="Price per day"
        className={classes.textField}
        margin="normal"
        type="number"
        onChange={({ currentTarget }) => {
          vehicle.pricePerDay = currentTarget.value;
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
