import React from "react";
import Container from "@material-ui/core/Container";
import { makeStyles } from "@material-ui/core/styles";
import NavigationCards from "../components/UI/NavigationCards";
import plane from "../assets/black-plane.png";
import building from "../assets/skyline.png";
import car from "../assets/car.png";
import HomeAuth from "./HomeAuthUser";

export default function HomePage({ history }) {
  const classes = useStyles();
  const role = window.localStorage.getItem("role");
  return (
    <Container classes={{ root: classes.rootColumn }}>
      <Container classes={{ root: classes.root }}>
        <NavigationCards
          image={plane}
          title="Airlines"
          description="Check airlines information and their destination flights."
          cardClick={() => {
            history.push("/airlines");
          }}
        />
        <NavigationCards
          image={building}
          title="Hotels"
          description="Check hotels information and their rooms and prices."
          cardClick={() => {
            history.push("/hotel-reservation");
          }}
        />
        <NavigationCards
          image={car}
          title="Rent A Cars"
          description="Check rent a car companies and their services."
          cardClick={() => {
            history.push("/rent-a-cars");
          }}
        />
      </Container>
      {/* {role === "USER" ? <HomeAuth /> : null} */}
      <HomeAuth />
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "row"
  },
  rootColumn: {
    display: "flex",
    flexDirection: "column"
  },
  inputs: {
    display: "flex",
    flexDirection: "column"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "100%"
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
    width: "30%",
    marginLeft: "auto"
  }
}));
