import React from "react";
import RentACarVehicles from "../components/rent-a-car/Vehicles";
import RentACarInformation from "../components/rent-a-car/Information";
import RentACarDiscountedVehicles from "../components/rent-a-car/DiscountedVehicles";
import Container from "@material-ui/core/Container";
import RentACarOffices from "../components/rent-a-car/Offices";
import { makeStyles } from "@material-ui/core/styles";
import Background from "../assets/background.jpg";
import Divider from "@material-ui/core/Divider";

export default function RentACarProfilePage({ match }) {
  const classes = useStyles();

  return (
    <div className={classes.bckg}>
      <Container maxWidth="xl">
        <RentACarDiscountedVehicles
          rentACarId={match.params.id}
        ></RentACarDiscountedVehicles>
        <Divider variant="fullWidth" orientation="horizontal"></Divider>

        <RentACarInformation rentACarId={match.params.id} />
        <RentACarOffices rentACarId={match.params.id} />
        <RentACarVehicles rentACarId={match.params.id} />
      </Container>
    </div>
  );
}
const useStyles = makeStyles(theme => ({
  bckg: {
    backgroundImage: `url(${Background})`
  }
}));
