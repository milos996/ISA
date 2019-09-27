import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import Badge from "@material-ui/core/Badge";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import StarBorderIcon from "@material-ui/icons/StarBorder";
import StarIcon from "@material-ui/icons/Star";
import AccessibilityIcon from "@material-ui/icons/Accessibility";
import { Container } from "@material-ui/core";
import {
  selectRentACarVehiclesOnDiscount,
  selectRentACarDetails
} from "../../store/rent-a-car/selectors";
import { userDataSelector } from "../../store/user/selectors";
import Rating from "react-rating";
import Background from "../../assets/background.jpg";
import CardBackground from "../../assets/card_discount_bckg.jpg";
import Brightness5Icon from "@material-ui/icons/Brightness5";

export default function DiscountedVehicles({ rentACarId, location }) {
  const discountedVehicles = useSelector(selectRentACarVehiclesOnDiscount);
  const rentACar = useSelector(selectRentACarDetails);
  const classes = useStyles();
  const user = useSelector(userDataSelector);

  return (
    <Container className={classes.vertical} maxWidth="xl">
      <Typography variant="h3" className={classes.header}>
        '{rentACar.name}'
      </Typography>
      {Object.keys(discountedVehicles).map(vehicleId => (
        <Container maxWidth="xl" className={classes.cards}>
          <Card className={classes.card} key={vehicleId}>
            <CardContent>
              <Typography variant="h5" component="h2">
                {discountedVehicles[vehicleId].brand}
              </Typography>
              <Typography color="textSecondary">
                {discountedVehicles[vehicleId].model}
              </Typography>
              <Badge
                badgeContent={discountedVehicles[vehicleId].discountRate}
                className={classes.badge}
              >
                <Brightness5Icon fontSize="large"></Brightness5Icon>
              </Badge>
              <Typography
                variant="body2"
                component="p"
                className={classes.content}
              >
                <strong>
                  {" "}
                  {discountedVehicles[vehicleId].type}{" "}
                  {discountedVehicles[vehicleId].brand}{" "}
                  {discountedVehicles[vehicleId].model}, from{" "}
                  {discountedVehicles[vehicleId].yearOfProduction}.
                </strong>
                <br />
                <div className={classes.content}>
                  <strong>
                    {discountedVehicles[vehicleId].pickUpDate} |{" "}
                    {discountedVehicles[vehicleId].dropOffDate}{" "}
                  </strong>
                </div>
                <div>
                  <strong>
                    ORIGINAL PRICE:
                    {discountedVehicles[vehicleId].originalPrice}$
                  </strong>
                  <br />
                  <strong>
                    DISCOUNTED PRICE: {discountedVehicles[vehicleId].discounted}
                    $
                  </strong>
                  .
                </div>
                <br />
                <Rating
                  readonly={true}
                  className={classes.rating}
                  initialRating={discountedVehicles[vehicleId].numberOfSeats}
                  stop={discountedVehicles[vehicleId].numberOfSeats}
                  emptySymbol={<AccessibilityIcon></AccessibilityIcon>}
                  fullSymbol={<AccessibilityIcon></AccessibilityIcon>}
                ></Rating>
                <br />
                <Rating
                  readonly={true}
                  className={classes.rating}
                  initialRating={discountedVehicles[vehicleId].rating}
                  stop={10}
                  emptySymbol={<StarBorderIcon></StarBorderIcon>}
                  fullSymbol={<StarIcon></StarIcon>}
                ></Rating>
              </Typography>
              <br />
            </CardContent>
            {user.role === "USER" ? (
              <CardActions className={classes.actions}>
                <Button className={classes.button} size="small">
                  <strong>RESERVE</strong>
                </Button>
              </CardActions>
            ) : null}
          </Card>
        </Container>
      ))}
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  horizontal: {
    textAlign: "center",
    backgroundImage: `url(${Background})`
  },
  vertical: {
    textAlign: "center",
    display: "flex",
    flexDirection: "column",
    backgroundImage: `url(${Background})`
  },
  header: {
    textAlign: "center",
    color: "#DAA520",
    fontFamily: "Corbel",
    marginTop: "2%",
    marginBottom: "2%"
  },
  cards: {
    textAlign: "center",
    display: "flex",
    flexDirection: "row",
    width: 340,
    backgroundImage: `url(${Background})`
  },
  card: {
    textAlign: "center",
    width: 280,
    marginBottom: 20,
    backgroundImage: `url(${CardBackground})`
  },
  content: {
    marginTop: 20
  },
  rating: {
    marginTop: 10
  },
  button: {
    marginLeft: "36.33%",
    marginRight: "36.33%"
  },
  badge: {
    marginTop: 15
  }
}));
