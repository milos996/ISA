import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import Container from "@material-ui/core/Container";
import { Link } from "react-router-dom";

export default function SearchResults({ searchResults }) {
  const classes = useStyles();
  const dispatch = useDispatch();

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      {searchResults.map(result => (
        <Card className={classes.card} key={result.id}>
          <CardActionArea>
            <CardContent>
              <Typography
                align="center"
                gutterBottom
                variant="h5"
                component="h2"
              >
                {result.departureTime} - {result.arrivalTime}
              </Typography>

              <Typography
                align="center"
                variant="body1"
                color="textSecondary"
                component="p"
              >
                {result.airplane.airline.address.city} -
                {result.airlineDestination.destination.city}
              </Typography>

              <Typography
                align="center"
                variant="body1"
                color="textSecondary"
                component="p"
              >
                Time duration: {result.duration}
              </Typography>
              <Typography
                align="center"
                variant="body1"
                color="textSecondary"
                component="p"
              >
                {result.airplane.airline.name}
              </Typography>
              <Typography
                align="left"
                variant="h5"
                color="primary"
                component="p"
              >
                {result.price}€
              </Typography>
            </CardContent>
          </CardActionArea>
          <CardActions>
            <Link to={"/ticket-reservation/" + result.id + "/choose-seat"}>
              <Button size="medium" color="primary">
                Select
              </Button>
            </Link>
          </CardActions>
        </Card>
      ))}
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  card: {
    maxWidth: "100%"
  },
  media: {
    height: 140
  },
  root: {
    display: "flex",
    flexDirection: "column",
    padding: "50px 0px 0px 0px"
  }
}));
