import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  selectFlights,
  selectAirlineRating
} from "../../store/airline/selectors";
import { makeStyles } from "@material-ui/core/styles";
import {
  fetchAirlineRating,
  fetchAirlineFlights
} from "../../store/airline/actions";
import Container from "@material-ui/core/Container";
import TextField from "@material-ui/core/TextField";
import { TableCell } from "@material-ui/core";
import { TableHead } from "@material-ui/core";
import { TableRow } from "@material-ui/core";
import { Table } from "@material-ui/core";
import { TableBody } from "@material-ui/core";

export default function BuisinessReport({ airlineId }) {
  const dispatch = useDispatch();
  const classes = useStyles();
  const flights = useSelector(selectFlights);
  const airlineRating = useSelector(selectAirlineRating);
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
    dispatch(fetchAirlineRating(airlineId));
  }, [airlineId]);

  useEffect(() => {
    dispatch(fetchAirlineFlights({ airlineId }));
  }, [airlineId]);

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <h1>Business report</h1>

      <TextField
        label="Airline rating"
        margin="normal"
        value={airlineRating.avgRating}
      />

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
              <TableCell align="left">{flight.avgRating}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: theme.spacing(3),
    overflowX: "auto",
    height: "100%"
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
