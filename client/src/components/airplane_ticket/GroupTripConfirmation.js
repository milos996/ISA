import React, { useState } from "react";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";

export default function GroupTripConfirmation() {
  const classes = useStyles();

  function handleConfirmButton() {}
  function handleRejectButton() {}
  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <h1>Group trip</h1>
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleConfirmButton}
      >
        Confirm
      </Button>
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleRejectButton}
      >
        Reject
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
  inputContainer: {
    padding: "0px 0px 0px 0px"
  },
  button: {
    margin: theme.spacing(1),
    width: "30%"
  }
}));
