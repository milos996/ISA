import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Grid from "@material-ui/core/Grid";
import img from "../../assets/car.png";
import { fetchRentACarDetails } from "../../store/rent-a-car/actions";
import {
  selectRentACarDetails,
  selectRentACarLocationInformation
} from "../../store/rent-a-car/selectors";
import ISAMap from "../hotel/ISAMap";
import RentACarOffices from "./Offices";

export default function RentACarInformation({ rentACarId }) {
  const [modalStyle] = React.useState(getModalStyle);
  const classes = useStyles();
  const dispatch = useDispatch();
  const rentACar = useSelector(selectRentACarDetails);
  const rentACarLocation = useSelector(selectRentACarLocationInformation);

  const [officesModalVisibility, setOfficesModalVisibility] = useState(false);

  useEffect(() => {
    dispatch(fetchRentACarDetails({ rentACarId }));
  }, [rentACarId]);

  function closeModal() {
    setOfficesModalVisibility(false);
  }

  return (
    <div className={classes.root}>
      <Grid container spacing={1}>
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
        <Grid item xs={4}>
          <Grid item xs={6}>
            <div class="container">
              <div class="row">
                <Typography variant="h5" component="h2" class="mt-4">
                  {rentACar.name}
                </Typography>
              </div>
            </div>
          </Grid>
          <Grid item xs={6}>
            <img height="420" width="420" src={img} alt="Vehicle" />
          </Grid>
        </Grid>
        <Grid item xs={8}>
          {rentACarLocation && (
            <ISAMap address={rentACarLocation} hasClick={false} />
          )}
        </Grid>
      </Grid>
    </div>
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
  root: {
    flexGrow: 1
  },
  paper: {
    padding: theme.spacing(2),
    textAlign: "center",
    color: theme.palette.text.secondary
  }
}));
