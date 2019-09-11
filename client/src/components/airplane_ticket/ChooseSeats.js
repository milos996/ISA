import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch } from "react-redux";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";

export default function ChooseSeats({ match }) {
  const classes = useStyles();
  const dispatch = useDispatch();

  const [choosenSeatCoordinates, setChoosenSeatCoordinates] = useState({
    segmentNum: 0,
    rowNum: 0,
    columnNum: 0
  });
  /*
  const [choosenSeats, setChoosenSeats] = useState([
    {
      segmentNum: 0,
      rowNum: 0,
      columnNum: 0
    }
  ]);*/
  const choosenSeats = [];
  function handleChooseButton() {
    /*
    setChoosenSeats(oldValues => ({
      ...oldValues,
      choosenSeatCoordinates
    }));*/
    choosenSeats.push(choosenSeatCoordinates);
    console.log(choosenSeatCoordinates);
    console.log({ choosenSeats });
  }
  function handleChangeSeatCoordinates(event) {
    event.persist();
    setChoosenSeatCoordinates(oldValues => ({
      ...oldValues,
      [event.target.name]: Number(event.target.value)
    }));
  }
  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <h2>Choose seat</h2>
      <TextField
        label="Segment number"
        margin="normal"
        type="number"
        className={classes.textField}
        onChange={handleChangeSeatCoordinates}
        inputProps={{
          name: "segmentNum",
          id: "segment-num"
        }}
      />

      <TextField
        label="Column number"
        margin="normal"
        type="number"
        className={classes.textField}
        onChange={handleChangeSeatCoordinates}
        inputProps={{
          name: "columnNum",
          id: "column-num"
        }}
      />

      <TextField
        label="Row number"
        margin="normal"
        type="number"
        className={classes.textField}
        onChange={handleChangeSeatCoordinates}
        inputProps={{
          name: "rowNum",
          id: "row-num"
        }}
      />

      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleChooseButton}
      >
        Choose
      </Button>
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleChooseButton}
      >
        Choose one more seat
      </Button>
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
    width: "25%"
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
    width: "30%"
  }
}));
