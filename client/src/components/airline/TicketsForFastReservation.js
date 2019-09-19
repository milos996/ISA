import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { selectTicketForFastReservation } from "../../store/airline/selectors";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";
import { TableCell } from "@material-ui/core";
import { TableHead } from "@material-ui/core";
import { TableRow } from "@material-ui/core";
import { Table } from "@material-ui/core";
import { TableBody } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {
  fetchTicketsForFastReservation,
  createFastTicketReservation
} from "../../store/airline/actions";

export default function TicketsForFastReservation({ airlineId }) {
  const dispatch = useDispatch();
  const classes = useStyles();
  const tickets = useSelector(selectTicketForFastReservation);
  const [columns, setColumns] = useState([
    { title: "Departure time", field: "departure_time" },
    { title: "Arrival time", field: "arrival_time" },
    { title: "Duration", field: "duration" },
    { title: "Length", field: "length" },
    { title: "Price on discount($)", field: "price_on_dicount" },
    { title: "Destination", field: "destination" }
  ]);

  useEffect(() => {
    dispatch(fetchTicketsForFastReservation({ airlineId }));
  }, [airlineId]);

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
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
          {tickets.map(ticket => (
            <TableRow key={ticket.id}>
              <TableCell align="left">{ticket.departureTime}</TableCell>
              <TableCell align="left">{ticket.arrivalTime}</TableCell>
              <TableCell align="left">{ticket.duration}</TableCell>
              <TableCell align="left">{ticket.length}</TableCell>
              <TableCell align="left">{ticket.price}</TableCell>
              <TableCell align="left">
                {ticket.airlineDestination.destination.city}
              </TableCell>
              <TableCell align="left">
                <Button
                  onClick={() =>
                    dispatch(createFastTicketReservation(ticket.id))
                  }
                >
                  Reserve
                </Button>
              </TableCell>
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
  button: {
    margin: theme.spacing(1)
  },
  table: {
    minWidth: 650
  }
}));
