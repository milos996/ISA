import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import "date-fns";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import {
  selectDestinations,
  selectAirlineAirplanes
} from "../../store/airline/selectors";
import {
  fetchAirlineDestinations,
  saveFlight
} from "../../store/airline/actions";

export default function CreateFlight({ closeModal, airlineId }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const airlines_destinations = useSelector(selectDestinations);
  const airplanes = useSelector(selectAirlineAirplanes);
  const [newFlight, setNewFlightDetails] = useState({
    departureTime: "",
    arrivalTime: "",
    duration: "",
    length: 0,
    price: 0,
    airlineDestination: {
      destination: {},
      airline: {}
    },
    airplane: {}
  });

  const [
    selectedDestinationValue,
    setSelectedDestinationValue
  ] = React.useState({
    destination: {
      id: "",
      state: "",
      city: ""
    },
    airline: {}
  });

  const [selectedAirplaneValue, setSelectedAirplaneValue] = React.useState({
    id: "",
    mark: "",
    numberOfRows: 0,
    numberOfColumnsPerSegment: 0,
    numberOfSegments: 0,
    airline: {}
  });

  useEffect(() => {
    dispatch(fetchAirlineDestinations({ airlineId }));
  }, [airlineId]);

  function handleSaveButton() {
    dispatch(saveFlight({ newFlight }));
    closeModal();
  }

  function handleCloseButton() {
    closeModal();
  }

  function handleChangeDestination(event) {
    setSelectedDestinationValue(oldValue => ({
      ...oldValue,
      ...event.target.value
    }));

    setNewFlightDetails(currentTarget => ({
      ...currentTarget,
      airlineDestination: event.target.value
    }));
  }

  function handleChangeAirplane(event) {
    setSelectedAirplaneValue(oldValue => ({
      ...oldValue,
      ...event.target.value
    }));

    setNewFlightDetails(currentTarget => ({
      ...currentTarget,
      airplane: event.target.value
    }));
  }

  return (
    <Container
      classes={{
        root: classes.root
      }}
      className="scroll"
    >
      <form className={classes.container} noValidate>
        <TextField
          id="datetime-local"
          label="Choose departure time"
          type="datetime-local"
          defaultValue="2017-05-24T10:30"
          className={classes.textField}
          InputLabelProps={{
            shrink: true
          }}
          onChange={({ currentTarget }) => {
            setNewFlightDetails({
              ...newFlight,
              departureTime: currentTarget.value
            });
          }}
        />
      </form>
      <form className={classes.container} noValidate>
        <TextField
          id="datetime-local"
          label="Choose arrival time"
          type="datetime-local"
          defaultValue="2017-05-24T10:30"
          className={classes.textField}
          InputLabelProps={{
            shrink: true
          }}
          onChange={({ currentTarget }) => {
            setNewFlightDetails({
              ...newFlight,
              arrivalTime: currentTarget.value
            });
          }}
        />
      </form>

      <FormControl className={classes.formControl}>
        <InputLabel htmlFor="destination-simple">Destination</InputLabel>
        <Select
          value={selectedDestinationValue}
          onChange={handleChangeDestination}
        >
          {airlines_destinations.map(airline_destination => (
            <MenuItem
              value={airline_destination}
              key={airline_destination.destination.id}
            >
              {airline_destination.destination.city}
            </MenuItem>
          ))}
        </Select>
      </FormControl>

      <FormControl className={classes.formControl}>
        <InputLabel htmlFor="airplane-simple">Airplane</InputLabel>
        <Select
          value={selectedAirplaneValue.mark}
          onChange={handleChangeAirplane}
        >
          {airplanes.map(airplane => (
            <MenuItem value={airplane} key={airplane.id}>
              {airplane.mark}
            </MenuItem>
          ))}
        </Select>
      </FormControl>

      <TextField
        label="Price"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setNewFlightDetails(currState => ({
            ...currState,
            price: Number(currentTarget.value)
          }));
        }}
      />

      <TextField
        label="Duration"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setNewFlightDetails(currState => ({
            ...currState,
            duration: Number(currentTarget.value)
          }));
        }}
      />

      <TextField
        label="Length"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setNewFlightDetails(currState => ({
            ...currState,
            length: Number(currentTarget.value)
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
      <Button onClick={handleCloseButton}>Close</Button>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column"
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
