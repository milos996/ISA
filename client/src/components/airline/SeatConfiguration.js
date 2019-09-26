import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch, useSelector } from "react-redux";
import TextField from "@material-ui/core/TextField";
import {
  saveAirplaneDetails,
  fetchAirlineAdmin
} from "../../store/airline/actions";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import { userDataSelector } from "../../store/user/selectors";
import { selectAirlineAdmin } from "../../store/airline/selectors";
export default function SeatConfiguration({ airplane, closeModal }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const user = useSelector(userDataSelector);
  const airlineAdmin = useSelector(selectAirlineAdmin);
  const [airplaneDetails, setAirplaneDetails] = useState({
    id: airplane.id,
    mark: airplane.mark,
    numberOfRows: airplane.numberOfRows,
    numberOfColumnsPerSegment: airplane.numberOfColumnsPerSegment,
    numberOfSegments: airplane.numberOfSegments,
    airline: airplane.airline
  });
  const isReadOnly =
    airplaneDetails.airline.id === airlineAdmin.airline.id &&
    user.role === "AIRLINE_ADMIN"
      ? false
      : true;

  useEffect(() => {
    dispatch(fetchAirlineAdmin());
  }, [airplaneDetails.airline.id]);

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
        InputProps={{
          readOnly: isReadOnly
        }}
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
        InputProps={{
          readOnly: isReadOnly
        }}
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
        InputProps={{
          readOnly: isReadOnly
        }}
        onChange={({ currentTarget }) => {
          setAirplaneDetails(currState => ({
            ...currState,
            numberOfRows: Number(currentTarget.value)
          }));
        }}
      />
      {user.role === "AIRLINE_ADMIN" &&
      airlineAdmin.airline.id === airplaneDetails.airline.id ? (
        <Button
          variant="contained"
          color="primary"
          className={classes.button}
          onClick={handleSaveButton}
        >
          Save
        </Button>
      ) : null}
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
