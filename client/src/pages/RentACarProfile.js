import React from "react";
import { useSelector } from "react-redux";
import RentACarVehicles from "../components/rent-a-car/Vehicles";
import RentACarInformation from "../components/rent-a-car/Information";
import Container from "@material-ui/core/Container";
import RentACarOffices from "../components/rent-a-car/Offices";
import { makeStyles } from "@material-ui/core/styles";

export default function RentACarProfilePage({ match }) {
  const classes = useStyles();

  return (
    <Container maxWidth="xl">
      <RentACarInformation rentACarId={match.params.id} />
      <RentACarOffices rentACarId={match.params.id} />
      <RentACarVehicles rentACarId={match.params.id} />
    </Container>
  );
}
const useStyles = makeStyles(theme => ({
  card: {
    width: 199,
    height: 300,
    marginBottom: 15,
    marginRight: 10,
    padding: 5,
    paddingBottom: 10
  },
  bullet: {
    display: "inline-block",
    margin: "0 2px",
    transform: "scale(0.8)"
  },
  title: {
    fontSize: 14
  },
  pos: {
    marginLeft: 5,
    marginBottom: 12
  }
}));
