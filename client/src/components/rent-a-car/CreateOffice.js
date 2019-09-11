import React, { useState } from "react";
import { useDispatch } from "react-redux";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { createRentACarOffice } from "./../../store/rent-a-car/actions";

export default function CreateOffice({ rentACarId, closeModal }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [agencyLocation] = useState({});

  function handleSaveButton() {
    dispatch(createRentACarOffice({ agencyLocation, rentACarId }));
    closeModal();
  }

  return (
    <Container
      classes={{
        root: classes.row
      }}
    >
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleSaveButton}
      >
        Save
      </Button>
      <TextField
        label="City"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          agencyLocation.city = currentTarget.value;
        }}
      />
      <TextField
        label="State"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          agencyLocation.state = currentTarget.value;
        }}
      />
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
  paper: {
    position: "absolute",
    width: 400,
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #000",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  root: {
    position: "absolute",
    left: "20%",
    top: "5%",
    width: "60%",
    display: "flex",
    flexDirection: "column"
  },
  row: {
    display: "flex",
    flexDirection: "column",
    width: "100%"
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
