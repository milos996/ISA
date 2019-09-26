import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";
import {
  fetchAirlines,
  sortAirlines
} from "../../store/airplane_ticket/actions";
import { selectAirlines } from "../../store/airplane_ticket/selectors";
import Button from "@material-ui/core/Button";
import ButtonGroup from "@material-ui/core/ButtonGroup";
import Airline from "./Airline";

export default function Airlines({ history }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const airlines = useSelector(selectAirlines);
  const fetchOneTime = true;
  useEffect(() => {
    dispatch(fetchAirlines());
  }, [fetchOneTime]);

  function handleSortByName() {
    dispatch(sortAirlines({ by: "name" }));
  }

  function handleSortByHandLuggage() {
    dispatch(sortAirlines({ by: "handLuggage" }));
  }

  function handleSortBySuticasePrice() {
    dispatch(sortAirlines({ by: "suitcasePrice" }));
  }

  function handleSortByName() {
    dispatch(sortAirlines({ by: "name" }));
  }

  function handleSortByAddress() {
    dispatch(sortAirlines({ by: "address" }));
  }

  function handleSortByRating() {
    dispatch(sortAirlines({ by: "rating" }));
  }

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <h1>Airlines</h1>
      <ButtonGroup
        size="small"
        aria-label="small outlined button group"
        className={classes.button}
      >
        <Button disabled>SORT BY</Button>
        <Button onClick={handleSortByName}>NAME</Button>
        <Button onClick={handleSortByAddress}>ADDRESS</Button>
        <Button onClick={handleSortByHandLuggage}>HAND LUGGAGE PRICE</Button>
        <Button onClick={handleSortBySuticasePrice}>SUITCASE PRICE</Button>
        <Button onClick={handleSortByRating}>RATING</Button>
      </ButtonGroup>
      <div className="airlines-container">
        {Object.keys(airlines).map(airlineId => (
          <Airline
            key={airlineId}
            airline={airlines[airlineId]}
            history={history}
          />
        ))}
      </div>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column"
  },
  button: {
    margin: theme.spacing(1),
    marginBottom: "20px"
  }
}));
