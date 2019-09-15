import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Modal from "@material-ui/core/Modal";
import {
  selectAirlineDetails,
  selectAirlineAirplanes
} from "../../store/airline/selectors";
import { fetchAirlineAirplanes } from "../../store/airline/actions";
import Airplane from "./Airplane";
import {
  putAirlineDetails,
  putAirlineLocationInformation,
  saveAirlineDetails,
  fetchAirlineDetails
} from "../../store/airline/actions";
import Container from "@material-ui/core/Container";
import ISAMap from "../hotel/ISAMap";
import Button from "@material-ui/core/Button";
import { MODAL_CONTENT } from "../../constants/airline";
import Destinations from "./Destinations";
import Flights from "./Flights";
import TicketsForFastReservation from "./TicketsForFastReservation";
export default function AirlineInformation({ airlineId }) {
  const dispatch = useDispatch();
  const classes = useStyles();
  const airlineDetails = useSelector(selectAirlineDetails);
  const airlineName = airlineDetails.name;

  const [modalContent, setModalContent] = useState({
    isVisible: false,
    value: ""
  });

  function setStreet(street) {
    dispatch(putAirlineLocationInformation({ street }));
  }

  function handleSaveButton() {
    dispatch(saveAirlineDetails(airlineDetails));
  }

  useEffect(() => {
    dispatch(fetchAirlineDetails({ airlineId }));
  }, [airlineId]);

  return (
    <div id="mainWraper" className="main-wrapper">
      <Modal open={modalContent.isVisible}>
        <div className="modal-container">
          {modalContent.value === MODAL_CONTENT.DESTINATIONS && (
            <Destinations airlineId={airlineId} />
          )}
          {modalContent.value === MODAL_CONTENT.FLIGHTS && (
            <Flights airlineId={airlineId} />
          )}
          {modalContent.value ===
            MODAL_CONTENT.TICKETS_FOR_FAST_RESERVATION && (
            <TicketsForFastReservation airlineId={airlineId} />
          )}

          <Button
            onClick={e =>
              setModalContent({
                isVisible: false
              })
            }
          >
            Close
          </Button>
        </div>
      </Modal>

      <Container
        className="vertical-items left-align mr-t-120-l-80"
        classes={{ root: classes.root }}
      >
        <Container
          classes={{
            root: classes.root
          }}
        >
          <Container>
            <h1>Airline {airlineName}</h1>
            <Button
              onClick={() =>
                setModalContent({
                  isVisible: true,
                  value: "destinations"
                })
              }
            >
              Destinations
            </Button>
            <Button
              onClick={() =>
                setModalContent({
                  isVisible: true,
                  value: "flights"
                })
              }
            >
              Flights
            </Button>
            <Button
              onClick={() =>
                setModalContent({
                  isVisible: true,
                  value: "tickets_for_fast_reservation"
                })
              }
            >
              Tickets for fast reservation
            </Button>
          </Container>
          <TextField
            label="Name"
            className={classes.textField}
            margin="normal"
            value={airlineDetails.name}
            onChange={({ currentTarget }) => {
              dispatch(
                putAirlineDetails({
                  ...airlineDetails,
                  name: currentTarget.value
                })
              );
            }}
          />

          <TextField
            label="Description"
            className={classes.textField}
            margin="normal"
            multiline
            rowsMax="8"
            value={airlineDetails.description}
            onChange={({ currentTarget }) => {
              dispatch(
                putAirlineDetails({
                  ...airlineDetails,
                  description: currentTarget.value
                })
              );
            }}
          />
          <TextField
            label="Suitcase price"
            className={classes.textField}
            margin="normal"
            value={airlineDetails.checkingInSuitcasePrice}
            onChange={({ currentTarget }) => {
              dispatch(
                putAirlineDetails({
                  ...airlineDetails,
                  checkingInSuitcasePrice: currentTarget.value
                })
              );
            }}
          />
          <TextField
            label="Hand luggage price"
            className={classes.textField}
            margin="normal"
            value={airlineDetails.handLuggagePrice}
            onChange={({ currentTarget }) => {
              dispatch(
                putAirlineDetails({
                  ...airlineDetails,
                  handLuggagePrice: currentTarget.value
                })
              );
            }}
          />
        </Container>

        <Button
          variant="contained"
          color="primary"
          className={classes.button}
          onClick={handleSaveButton}
        >
          Save
        </Button>
        <ISAMap address={airlineDetails.address} setStreet={setStreet} />
      </Container>
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column",
    margin: "auto"
  },
  inputs: {
    display: "flex",
    flexDirection: "column"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "100%"
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
