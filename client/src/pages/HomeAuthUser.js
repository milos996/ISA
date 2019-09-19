import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import ExpansionPanel from "@material-ui/core/ExpansionPanel";
import ExpansionPanelDetails from "@material-ui/core/ExpansionPanelDetails";
import ExpansionPanelSummary from "@material-ui/core/ExpansionPanelSummary";
import Typography from "@material-ui/core/Typography";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import VehiclesReservation from "../components/rent-a-car/VehiclesReservationHistory";
import FlightsReservation from "../components/airline/FlightsReservationHistory";
import HotelsRservation from "../components/hotel/HotelsReservationHistory";
import {
  fetchUserHotelsReservation,
  fetchUserVehiclesReservation,
  fetchUserFlightsReservation
} from "../store/user/actions";

export default function HomeAuthUserPage() {
  const email = window.localStorage.getItem("email");
  const dispatch = useDispatch();
  const classes = useStyles();
  const [expanded, setExpanded] = useState(false);

  const handleChange = panel => (event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  };

  useEffect(() => {
    dispatch(fetchUserHotelsReservation());
    dispatch(fetchUserVehiclesReservation());
    dispatch(fetchUserFlightsReservation());
  });

  return (
    <div className={classes.root}>
      <ExpansionPanel
        expanded={expanded === "vehiclesReservation"}
        onChange={handleChange("vehiclesReservation")}
      >
        <ExpansionPanelSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="vehiclesReservation-bh-content"
          id="vehiclesReservation-bh-header"
        >
          <Typography className={classes.heading}>
            Vehicles reservation
          </Typography>
        </ExpansionPanelSummary>
        <ExpansionPanelDetails>
          <div>
            <VehiclesReservation></VehiclesReservation>
          </div>
        </ExpansionPanelDetails>
      </ExpansionPanel>
      <ExpansionPanel
        expanded={expanded === "hotelsReservation"}
        onChange={handleChange("hotelsReservation")}
      >
        <ExpansionPanelSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="hotelsReservation-bh-content"
          id="hotelsReservation-bh-header"
        >
          <Typography className={classes.heading}>
            Hotels reservation
          </Typography>
        </ExpansionPanelSummary>
        <ExpansionPanelDetails>
          <HotelsRservation></HotelsRservation>
        </ExpansionPanelDetails>
      </ExpansionPanel>
      <ExpansionPanel
        expanded={expanded === "flightsReservation"}
        onChange={handleChange("flightsReservation")}
      >
        <ExpansionPanelSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="flightsReservation-bh-content"
          id="flightsReservation-bh-header"
        >
          <Typography className={classes.heading}>
            Flights reservation
          </Typography>
        </ExpansionPanelSummary>
        <ExpansionPanelDetails>
          <FlightsReservation></FlightsReservation>
        </ExpansionPanelDetails>
      </ExpansionPanel>
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    marginTop: "2%",
    marginRight: 40,
    marginLeft: 30
  },
  heading: {
    fontSize: theme.typography.pxToRem(15),
    flexBasis: "33.33%",
    flexShrink: 0
  },
  secondaryHeading: {
    fontSize: theme.typography.pxToRem(15),
    color: theme.palette.text.secondary
  }
}));
