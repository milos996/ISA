import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { selectFlights } from "../../store/airline/selectors";
import { TableCell } from "@material-ui/core";
import { TableHead } from "@material-ui/core";
import { TableRow } from "@material-ui/core";
import { Table } from "@material-ui/core";
import { TableBody } from "@material-ui/core";
import { Container } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import Modal from "@material-ui/core/Modal";
import { makeStyles } from "@material-ui/core/styles";
import { fetchAirlineFlights } from "../../store/airline/actions";
import { MODAL_CONTENT } from "../../constants/airline";
import CreateFlight from "./CreateFlight";
import { userDataSelector } from "../../store/user/selectors";

export default function Flights({ airlineId }) {
  const dispatch = useDispatch();
  const classes = useStyles();
  const flights = useSelector(selectFlights);
  const user = useSelector(userDataSelector);

  const [columns, setColumns] = useState([
    { title: "Departure date", field: "departure_date" },
    { title: "Return date", field: "arrival_date" },
    { title: "Duration", field: "duration" },
    { title: "Length(km)", field: "length" },
    { title: "Price($)", field: "price" },
    { title: "Destination", field: "destination" }
  ]);

  const [modalContent, setModalContent] = useState({
    isVisible: false,
    value: ""
  });

  useEffect(() => {
    dispatch(fetchAirlineFlights({ airlineId }));
  }, [airlineId]);

  function closeCreateFlightModal() {
    setModalContent(false);
  }

  return (
    <div id="mainWraper" className="main-wrapper">
      <Modal open={modalContent.isVisible}>
        <div className="modal-container">
          {modalContent.value === MODAL_CONTENT.CREATE_FLIGHT && (
            <CreateFlight
              closeModal={closeCreateFlightModal}
              airlineId={airlineId}
            />
          )}
        </div>
      </Modal>

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
              </TableRow>
            ))}
          </TableBody>
        </Table>
        {user.role === "AIRLINE_ADMIN" && (
          <Button
            variant="contained"
            color="primary"
            className={classes.button}
            onClick={() => {
              setModalContent({
                isVisible: true,
                value: "create_flight"
              });
            }}
          >
            Create new flight
          </Button>
        )}
      </Container>
    </div>
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
    minWidth: 650
  }
}));
