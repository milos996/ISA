import React from "react";
import Invites from "../components/user/Invites";
import Container from "@material-ui/core/Container";
import { makeStyles } from "@material-ui/core/styles";

export default function InvitePage({ match }) {
  const classes = useStyles();
  return (
    <Container className={classes.root}>
      <Invites userId={match.params.id} />
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    marginTop: "3%"
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
