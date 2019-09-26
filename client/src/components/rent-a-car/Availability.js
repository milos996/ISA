import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import MaterialTable from "material-table";
import { makeStyles } from "@material-ui/core/styles";
import {
  KeyboardDatePicker,
  MuiPickersUtilsProvider
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import dateFormat from "dateformat";
import TextField from "@material-ui/core/TextField";
import CheckBox from "@material-ui/core/Checkbox";
import Button from "@material-ui/core/Button";
import {
  selectRentACarAvailableVehicles,
  selectRentACarDetails
} from "../../store/rent-a-car/selectors";
import { showAvailableRentACarVehicles } from "../../store/rent-a-car/actions";

import { Container } from "@material-ui/core";

export default function VehiclesAvailability({ closeModal }) {
  const classes = useStyles();
  const dispatch = useDispatch();

  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState();
  const [available, setAvailable] = useState();

  const availableVehicles = useSelector(selectRentACarAvailableVehicles);
  const selectedRentACar = useSelector(selectRentACarDetails);

  function handleShow() {
    dispatch(
      showAvailableRentACarVehicles({
        id: selectedRentACar.id,
        startDate,
        endDate,
        available
      })
    );
  }

  function handleCheck(event) {
    setAvailable(event.target.checked);
  }

  const columns = [
    { title: "Brand", field: "brand" },
    { title: "Model", field: "model" },
    { title: "Year of production", field: "yearOfProduction" },
    { title: "Number of seats", field: "numberOfSeats" }
  ];

  return (
    <Container className={classes.vertical}>
      <Container className={classes.horizontal}>
        <MuiPickersUtilsProvider utils={DateFnsUtils} className={classes.date}>
          <KeyboardDatePicker
            disableToolbar
            variant="inline"
            format="dd/MM/yyyy"
            margin="normal"
            label="Start date"
            value={startDate}
            onChange={date => setStartDate(dateFormat(date, "yyyy-mm-dd"))}
            KeyboardButtonProps={{
              "aria-label": "change date"
            }}
          />
        </MuiPickersUtilsProvider>
        <MuiPickersUtilsProvider utils={DateFnsUtils} className={classes.date}>
          <KeyboardDatePicker
            disableToolbar
            minDate={!!startDate ? startDate : new Date()}
            variant="inline"
            format="dd/MM/yyyy"
            margin="normal"
            label="End date"
            value={endDate}
            onChange={date => setEndDate(dateFormat(date, "yyyy-mm-dd"))}
            KeyboardButtonProps={{
              "aria-label": "change date"
            }}
          />
        </MuiPickersUtilsProvider>
        <CheckBox
          className={classes.check}
          onChange={handleCheck}
          className={classes.check}
          color="#008080"
        ></CheckBox>
        <Button
          variant="contained"
          className={classes.button}
          onClick={handleShow}
        >
          Show
        </Button>
      </Container>
      <MaterialTable
        className={classes.table}
        title={available ? "Available " : "Unvailable"}
        columns={columns}
        data={availableVehicles}
      />
    </Container>
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
  vertical: {
    display: "flex",
    flexDirection: "column",
    marginBottom: 25
  },
  horizontal: {
    display: "flex",
    flexDirection: "row",
    marginBottom: 15
  },
  button: {
    background: "#008080",
    marginBottom: 25,
    marginLeft: 25,
    marginTop: 20,
    height: 35,
    color: "#FFFFFF"
  },
  check: {
    color: "#008080",
    "&:hover": {
      backgroundColor: "transparent"
    },
    marginLeft: 15
  },
  date: {
    marginRight: 10
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    flex: 1,
    width: 150
  }
}));
