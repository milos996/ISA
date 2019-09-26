import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";
import DateFnsUtils from "@date-io/date-fns";
import {
  KeyboardDatePicker,
  MuiPickersUtilsProvider
} from "@material-ui/pickers";
import dateFormat from "dateformat";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import Slider from "@material-ui/core/Slider";

import { discountVehicle } from "../../store/rent-a-car/actions";

export default function DiscountVehicle({ vehicle }) {
  const classes = useStyles();
  const dispatch = useDispatch();

  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());
  const [discountRate, setDiscountRate] = useState();

  function handleConfirm() {
    dispatch(
      discountVehicle({
        entityId: vehicle.id,
        startDate,
        endDate,
        rate: discountRate
      })
    );
  }

  const marks = [
    {
      value: 0,
      label: "0%"
    },
    {
      value: 20,
      label: "20%"
    },
    {
      value: 50,
      label: "50%"
    },
    {
      value: 100,
      label: "100%"
    }
  ];

  function valuetext(value) {
    setDiscountRate(value);
    return `${value}%`;
  }

  function valueLabelFormat(value) {
    return marks.findIndex(mark => mark.value === value) + 1;
  }

  return (
    <Container className={classes.vertical}>
      <strong>
        <Typography>
          Define discount for: {vehicle.brand} {vehicle.model}
        </Typography>{" "}
      </strong>
      <Container className={classes.horizontal}>
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardDatePicker
            required
            className={classes.marginRight}
            disableToolbar
            minDate={new Date()}
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
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardDatePicker
            required
            className={classes.marginLeft}
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
      </Container>
      <Slider
        className={classes.slider}
        defaultValue={0}
        getAriaValueText={valuetext}
        aria-labelledby="discrete-slider"
        valueLabelDisplay="auto"
        step={1}
        valueLabelDisplay="on"
        marks
        min={0}
        max={100}
      />
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleConfirm}
      >
        Confirm
      </Button>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  horizontal: {
    display: "flex",
    flexDirection: "row"
  },
  vertical: {
    display: "flex",
    flexDirection: "column"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200,
    marginLeft: 100
  },
  marginRight: {
    marginRight: 20
  },
  marginLeft: {
    marginLeft: 20
  },
  button: {
    margin: theme.spacing(1),
    background: "#008080",
    marginTop: 20,
    width: 200,
    marginLeft: 100
  },
  slider: {
    marginTop: 40,
    color: "#008080"
  }
}));
