import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";
import { fetchAirlines } from "../../store/airplane_ticket/actions";
import { selectAirlines } from "../../store/airplane_ticket/selectors";
import Airline from "./Airline";

export default function Airlines() {
  const classes = useStyles();
  const dispatch = useDispatch();
  const airlines = useSelector(selectAirlines);

  useEffect(() => {
    dispatch(fetchAirlines());
  });
  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <h1>Airlines</h1>
      <div className="airlines-container">
        {Object.keys(airlines).map(airlineId => (
          <Airline key={airlineId} airline={airlines[airlineId]} />
        ))}
      </div>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column"
  }
}));
