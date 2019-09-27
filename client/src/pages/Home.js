import React from "react";
import { useSelector } from "react-redux";
import Container from "@material-ui/core/Container";
import { makeStyles } from "@material-ui/core/styles";
import NavigationCards from "../components/UI/NavigationCards";
import plane from "../assets/black-plane.png";
import building from "../assets/skyline.png";
import car from "../assets/car.png";
import HomeAuth from "../pages/HomeAuthUser";
import { userDataSelector } from "../store/user/selectors";
import Background from "../assets/background.jpg";

export default function HomePage({ history }) {
  const classes = useStyles();
  const userData = useSelector(userDataSelector);

  var role = "";

  if (userData != null) {
    role = userData.role;
  }

  return (
    <Container classes={{ root: classes.vertical }} maxWidth="xl">
      <Container classes={{ root: classes.root }} maxWidth="xl">
        <NavigationCards
          className={classes.card}
          image={plane}
          title="Airlines"
          description="Check airlines information and their destination flights."
          cardClick={() => {
            history.push("/ticket-reservation");
          }}
        />
        <NavigationCards
          className={classes.card}
          image={building}
          title="Hotels"
          description="Check hotels information and their rooms and prices."
          cardClick={() => {
            history.push("/hotel-reservation");
          }}
        />
        <NavigationCards
          className={classes.card}
          image={car}
          title="Rent A Cars"
          description="Check rent a car companies and their services."
          cardClick={() => {
            history.push("/rent-a-cars");
          }}
        />
      </Container>
      {role === "USER" ? <HomeAuth /> : null}
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    textAlign: "center",
    display: "flex",
    flexDirection: "row",
    background: `url(${Background})`
  },
  vertical: {
    textAlign: "center",
    display: "flex",
    flexDirection: "column",
    background: `url(${Background})`
  },
  card: {
    textAlign: "center"
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
