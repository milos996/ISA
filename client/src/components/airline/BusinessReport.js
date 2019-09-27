import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  selectFlights,
  selectAirlineRating,
  selectAirlineAdmin
} from "../../store/airline/selectors";
import { makeStyles } from "@material-ui/core/styles";
import {
  fetchAirlineRating,
  fetchAirlineFlights,
  fetchAirlineAdmin
} from "../../store/airline/actions";
import Container from "@material-ui/core/Container";
import TextField from "@material-ui/core/TextField";
import { TableCell } from "@material-ui/core";
import { TableHead } from "@material-ui/core";
import { TableRow } from "@material-ui/core";
import { Table } from "@material-ui/core";
import { TableBody } from "@material-ui/core";
import { userDataSelector } from "../../store/user/selectors";
import AirlineIncome from "./AirlineIncome";
import Modal from "@material-ui/core/Modal";
import Button from "@material-ui/core/Button";
import SoldTickets from "./SoldTickets";

export default function BuisinessReport({ airlineId }) {
  const dispatch = useDispatch();
  const classes = useStyles();
  const flights = useSelector(selectFlights);
  const airlineRating = useSelector(selectAirlineRating);
  const user = useSelector(userDataSelector);
  const [modalStyle] = React.useState(getModalStyle);
  const airlineAdmin = useSelector(selectAirlineAdmin);

  const [showIncomeModalVisibility, setShowIncomeModalVisibility] = useState(
    false
  );
  const [
    showSoldTicketsModalVisibility,
    setShowSoldTicketsModalVisibility
  ] = useState(false);
  const [columns, setColumns] = useState([
    { title: "Departure time", field: "departure_time" },
    { title: "Arrival time", field: "arrival_time" },
    { title: "Duration", field: "duration" },
    { title: "Length", field: "length" },
    { title: "Price($)", field: "price" },
    { title: "Destination", field: "destination" },
    { title: "Average rating", field: "average_rating" }
  ]);
  useEffect(() => {
    dispatch(fetchAirlineAdmin());
  }, []);
  useEffect(() => {
    dispatch(fetchAirlineRating(airlineId));
  }, [airlineId]);

  useEffect(() => {
    dispatch(fetchAirlineFlights({ airlineId }));
  }, [airlineId]);

  function closeModal() {
    setShowIncomeModalVisibility(false);
    setShowSoldTicketsModalVisibility(false);
  }
  function handleShowIncome() {
    setShowIncomeModalVisibility(true);
  }

  function handleShowSoldTickets() {
    setShowSoldTicketsModalVisibility(true);
  }
  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <Modal open={showIncomeModalVisibility}>
        <div style={modalStyle} className={classes.paper}>
          <AirlineIncome closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>

      <Modal open={showSoldTicketsModalVisibility}>
        <div style={modalStyle} className={classes.paper}>
          <SoldTickets closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <h1>Business report</h1>

      <TextField
        label="Airline rating"
        margin="normal"
        value={airlineRating.avgRating}
      />

      {user.role === "AIRLINE_ADMIN" &&
      airlineAdmin.airline.id === airlineId ? (
        <div>
          <Button
            variant="contained"
            color="primary"
            className={classes.button}
            onClick={handleShowIncome}
          >
            Income
          </Button>
          <Button
            variant="contained"
            color="primary"
            className={classes.button}
            onClick={handleShowSoldTickets}
          >
            Sold tickets
          </Button>
        </div>
      ) : null}

      <h2>Flights</h2>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            {columns.map(column => (
              <TableCell key={column.field} align="left">
                {column.title}
              </TableCell>
            ))}
          </TableRow>
        </TableHead>
        <TableBody>
          {flights.map(flight => (
            <TableRow key={flight.id}>
              <TableCell align="left">{flight.departureTime}</TableCell>
              <TableCell align="left">{flight.arrivalTime}</TableCell>
              <TableCell align="left">{flight.duration}</TableCell>
              <TableCell align="left">{flight.length}</TableCell>
              <TableCell align="left">{flight.price}</TableCell>
              <TableCell align="left">
                {flight.airlineDestination.destination.city}
              </TableCell>
              <TableCell align="left">
                {flight.avgRating === "NaN" ? "/" : flight.avgRating}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
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
  root: {
    width: "100%",
    marginTop: theme.spacing(3),
    overflowX: "auto",
    height: "100%"
  },
  paper: {
    position: "absolute",
    width: "50%",
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #008080",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  margin: {
    margin: theme.spacing(1)
  },
  button: {
    margin: theme.spacing(1)
  },
  table: {
    minWidth: 650,
    margin: "20px 20px 50px 20px"
  }
}));
