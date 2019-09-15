import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch } from "react-redux";
import TextField from "@material-ui/core/TextField";
import {
  putAirplaneDetails,
  saveAirplaneDetails
} from "../../store/airline/actions";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";

export default function SeatConfiguration({ airplane, closeModal }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [airplaneDetails, setAirplaneDetails] = useState({
    id: airplane.id,
    mark: airplane.mark,
    numberOfRows: airplane.numberOfRows,
    numberOfColumnsPerSegment: airplane.numberOfColumnsPerSegment,
    numberOfSegments: airplane.numberOfSegments,
    airline: airplane.airline
  });

  function handleSaveButton() {
    dispatch(saveAirplaneDetails(airplaneDetails));
    closeModal();
  }

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <TextField
        label="Number of segments"
        margin="normal"
        className={classes.textField}
        defaultValue={airplane.numberOfSegments}
        onChange={({ currentTarget }) => {
          setAirplaneDetails(currState => ({
            ...currState,
            numberOfSegments: Number(currentTarget.value)
          }));
        }}
      />

      <TextField
        label="Number of columns per segment"
        margin="normal"
        className={classes.textField}
        defaultValue={airplane.numberOfColumnsPerSegment}
        onChange={({ currentTarget }) => {
          setAirplaneDetails(currState => ({
            ...currState,
            numberOfColumnsPerSegment: Number(currentTarget.value)
          }));
        }}
      />

      <TextField
        label="Number of rows"
        margin="normal"
        className={classes.textField}
        defaultValue={airplane.numberOfRows}
        onChange={({ currentTarget }) => {
          setAirplaneDetails(currState => ({
            ...currState,
            numberOfRows: Number(currentTarget.value)
          }));
        }}
      />

      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleSaveButton}
      >
        Save
      </Button>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column",
    width: "100%"
  },
  inputs: {
    display: "flex",
    flexDirection: "column"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "80%"
  },
  inputContainer: {
    padding: "0px 0px 0px 0px"
  },
  modalContainer: {
    width: "60%",
    height: "80%",
    display: "flex",
    flexDirection: "column",
    background: "#cce8ff",
    padding: "0px 0px 0px 0px",
    border: "0px none",
    justifyContent: "flex-end"
  },
  button: {
    margin: theme.spacing(1),
    width: "30%",
    marginLeft: "auto"
  }
}));
