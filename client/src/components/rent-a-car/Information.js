import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";
import Modal from "@material-ui/core/Modal";
import img from "../../assets/vehicle.png";
import {
  fetchRentACarDetails,
  fetchRentACarLocationInformation
} from "../../store/rent-a-car/actions";
import {
  selectRentACarDetails,
  selectRentACarLocationInformation
} from "../../store/rent-a-car/selectors";
import ISAMap from "../hotel/ISAMap";
import RentACarOffices from "./Offices";
import Background from "../../assets/background.jpg";

export default function RentACarInformation({ rentACarId }) {
  const [modalStyle] = React.useState(getModalStyle);
  const classes = useStyles();
  const dispatch = useDispatch();
  const rentACar = useSelector(selectRentACarDetails);
  const rentACarLocation = useSelector(selectRentACarLocationInformation);

  const [officesModalVisibility, setOfficesModalVisibility] = useState(false);

  useEffect(() => {
    dispatch(
      fetchRentACarDetails(rentACarId),
      fetchRentACarLocationInformation(rentACar.address)
    );
  }, []);

  function closeModal() {
    setOfficesModalVisibility(false);
  }

  return (
    <Container className={classes.vertical} maxWidth="xl">
      <Modal open={officesModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <RentACarOffices rentACarId={rentACarId} closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Container className={classes.horizontal} maxWidth="xl">
        <img className={classes.img} src={img} alt="Vehicle" />
        {rentACar.address && (
          <ISAMap
            address={rentACar.address}
            hasClick={false}
            className={classes.item}
          />
        )}
      </Container>
    </Container>
  );
}

function getModalStyle() {
  const top = 50;
  const left = 50;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`
  };
}

const useStyles = makeStyles(theme => ({
  paper: {
    position: "absolute",
    width: 400,
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #000",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  vertical: {
    display: "flex",
    flexDirection: "column",
    backgroundImage: `url(${Background})`
  },
  horizontal: {
    display: "flex",
    flexDirection: "row",
    marginTop: 40
  },
  item: {
    marginRight: 25
  },
  img: {
    marginLeft: 25
  }
}));
