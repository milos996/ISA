import React from "react";
import { useSelector } from "react-redux";
import Container from "@material-ui/core/Container";
import { makeStyles } from "@material-ui/core/styles";
import NavigationCards from "../components/UI/NavigationCards";
import plane from "../assets/black-plane.png";
import building from "../assets/skyline.png";
import car from "../assets/car.png";
import { userTokenSelector } from "../store/user/selectors";

export default function HomePage({ history }) {
  const classes = useStyles();
  const userToken = useSelector(userTokenSelector);

  return (
    <Container classes={{ root: classes.root }}>
      <NavigationCards
        image={plane}
        title="Airlines"
        description="Check airlines information and their destination flights."
        cardClick={() => {
          history.push("/ticket-reservation");
        }}
      />
      {!userToken && (
        <NavigationCards
          image={building}
          title="Hotels"
          description="Check hotels information and their rooms and prices."
          cardClick={() => {
            history.push("/hotel-reservation");
          }}
        />
      )}
      <NavigationCards
        image={car}
        title="Rent A Cars"
        description="Check rent a car companies and their services."
        cardClick={() => {
          history.push("/rent-a-cars");
        }}
      />
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "row"
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
