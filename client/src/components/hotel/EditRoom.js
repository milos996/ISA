import React, { useState } from "react";
import { useDispatch } from "react-redux";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { saveRoomDetails } from "./../../store/hotel/actions";

export default function EditRoom({ room, closeModal }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [roomDetails, setRoomDetails] = useState(room);

  function handleSaveButton() {
    dispatch(saveRoomDetails({ roomDetails }));
    closeModal();
  }

  return (
    <Container
      classes={{
        root: classes.serviceRow
      }}
    >
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleSaveButton}
      >
        Save
      </Button>
      <TextField
        label="Number of people"
        defaultValue={roomDetails.numberOfPeople}
        className={classes.textField}
        type="number"
        margin="normal"
        onChange={({ currentTarget }) => {
          setRoomDetails(currState => ({
            ...currState,
            numberOfPeople: currentTarget.value
          }));
        }}
      />
      <TextField
        label="Summer price"
        defaultValue={roomDetails.priceSummer}
        className={classes.textField}
        margin="normal"
        type="number"
        onChange={({ currentTarget }) => {
          setRoomDetails(currState => ({
            ...currState,
            priceSummer: currentTarget.value
          }));
        }}
      />

      <TextField
        label="Winter price"
        defaultValue={roomDetails.priceWinter}
        className={classes.textField}
        margin="normal"
        type="number"
        onChange={({ currentTarget }) => {
          setRoomDetails(currState => ({
            ...currState,
            priceWinter: currentTarget.value
          }));
        }}
      />
      <TextField
        label="Autumn price"
        defaultValue={roomDetails.priceAutumn}
        className={classes.textField}
        margin="normal"
        type="number"
        onChange={({ currentTarget }) => {
          setRoomDetails(currState => ({
            ...currState,
            priceAutumn: currentTarget.value
          }));
        }}
      />
      <TextField
        label="Spring price"
        defaultValue={roomDetails.priceSpring}
        className={classes.textField}
        margin="normal"
        type="number"
        onChange={({ currentTarget }) => {
          setRoomDetails(currState => ({
            ...currState,
            priceSpring: currentTarget.value
          }));
        }}
      />
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    position: "absolute",
    left: "20%",
    top: "5%",
    width: "60%",
    display: "flex",
    flexDirection: "column"
  },
  serviceRow: {
    display: "flex",
    flexDirection: "column",
    width: "100%"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    flex: 1
  },
  margin: {
    margin: theme.spacing(1)
  },
  button: {
    margin: theme.spacing(1),
    width: "30%",
    marginLeft: "auto"
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120
  },
  selectEmpty: {
    marginTop: theme.spacing(2)
  },
  listScroll: {
    maxHeight: "370px",
    overflow: "scroll"
  }
}));
