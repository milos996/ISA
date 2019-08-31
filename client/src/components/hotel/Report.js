import React from "react";
import Container from "@material-ui/core/Container";
import { makeStyles } from "@material-ui/core/styles";

export default function Report() {
  const classes = useStyles();

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <h1>Report</h1>
      <p>reports</p>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    position: "absolute",
    left: "20%",
    top: "20%",
    width: "60%"
  }
}));
