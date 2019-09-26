import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";
import Box from "@material-ui/core/Box";
import Icon from "@material-ui/core/Icon";
import Modal from "@material-ui/core/Modal";
import { Grid } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import { selectRentACarOffices } from "../../store/rent-a-car/selectors";
import RentACarOffice from "./Office";
import { fetchRentACarOffices } from "../../store/rent-a-car/actions";
import CreateOffice from "./CreateOffice";
import Background from "../../assets/background.jpg";

export default function RentACarOffices({ rentACarId, closeModal }) {
  const role = window.localStorage.getItem("role");
  const offices = useSelector(selectRentACarOffices);
  const classes = useStyles();
  const [modalStyle] = React.useState(getModalStyle);
  const dispatch = useDispatch();
  const [createModalVisibility, setCreateModalVisibility] = useState(false);

  useEffect(() => {
    dispatch(
      fetchRentACarOffices({
        rentACarId
      })
    );
  }, [rentACarId]);

  function closeModal() {
    setCreateModalVisibility(false);
  }

  return (
    <Container maxWidth="xl" className={classes.horizontal}>
      <Modal open={createModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <CreateOffice rentACarId={rentACarId} closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>

      <Grid container spacing={1}>
        <Grid item xs={2}>
          <h2>Agency locations</h2>
        </Grid>
        <Grid item xs={1} className={classes.button}>
          {role === "RENT_A_CAR_ADMIN" ? (
            <Icon onClick={() => setCreateModalVisibility(true)}>
              add_circle
            </Icon>
          ) : null}
        </Grid>
      </Grid>
      <Box display="row" p={1} className={classes.horizontal}>
        {Object.keys(offices).map(officeId => (
          <RentACarOffice key={officeId} office={offices[officeId]} />
        ))}
      </Box>
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
    border: "2px solid #5bc0de",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  root: {
    display: "flex",
    flexDirection: "column"
  },
  horizontal: {
    display: "flex",
    flexDirection: "column",
    backgroundImage: `url(${Background})`
  },
  button: {
    marginTop: 15
  }
}));
