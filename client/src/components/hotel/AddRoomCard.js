import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch } from "react-redux";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { addNewRoom } from "../../store/hotel/actions";

export default function AddRoomCard({ hotelId }) {
  const classes = useStyles();
  const dispatch = useDispatch();

  const [room, setRoom] = useState({
    floor: null,
    number: null,
    summerPrice: null,
    winterPrice: null,
    autumnPrice: null,
    springPrice: null,
    numberOfPeople: null
  });

  return (
    <Card className={classes.card}>
      <CardContent className={classes.cardContent}>
        <TextField
          label="Floor*"
          defaultValue={room.floor}
          className={classes.textField}
          type="number"
          margin="normal"
          onChange={({ currentTarget }) => {
            setRoom(currState => ({
              ...currState,
              floor: currentTarget.value
            }));
          }}
        />
        <TextField
          label="Number*"
          defaultValue={room.number}
          className={classes.textField}
          type="number"
          margin="normal"
          onChange={({ currentTarget }) => {
            setRoom(currState => ({
              ...currState,
              number: currentTarget.value
            }));
          }}
        />
        <TextField
          label="Number of people*"
          defaultValue={room.numberOfPeople}
          className={classes.textField}
          type="number"
          margin="normal"
          onChange={({ currentTarget }) => {
            setRoom(currState => ({
              ...currState,
              numberOfPeople: currentTarget.value
            }));
          }}
        />
        <TextField
          label="Summer price*"
          defaultValue={room.summerPrice}
          className={classes.textField}
          type="number"
          margin="normal"
          onChange={({ currentTarget }) => {
            setRoom(currState => ({
              ...currState,
              summerPrice: currentTarget.value
            }));
          }}
        />

        <TextField
          label="Winter price"
          defaultValue={room.winterPrice}
          className={classes.textField}
          margin="normal"
          type="number"
          onChange={({ currentTarget }) => {
            setRoom(currState => ({
              ...currState,
              winterPrice: currentTarget.value
            }));
          }}
        />
        <TextField
          label="Autumn price"
          defaultValue={room.autumnPrice}
          className={classes.textField}
          margin="normal"
          type="number"
          onChange={({ currentTarget }) => {
            setRoom(currState => ({
              ...currState,
              autumnPrice: currentTarget.value
            }));
          }}
        />
        <TextField
          label="Spring price"
          defaultValue={room.springPrice}
          className={classes.textField}
          margin="normal"
          type="number"
          onChange={({ currentTarget }) => {
            setRoom(currState => ({
              ...currState,
              springPrice: currentTarget.value
            }));
          }}
        />
      </CardContent>
      <CardActions>
        <Button
          size="small"
          onClick={() => {
            if (
              !room.floor ||
              !room.number ||
              !room.numberOfPeople ||
              !room.summerPrice
            ) {
              alert("Please fill all the necessary fields!");
              return;
            }

            dispatch(
              addNewRoom({
                ...room,
                hotelId
              })
            );
          }}
        >
          Add
        </Button>
      </CardActions>
    </Card>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    flex: 1
  },
  card: {
    width: 199,
    height: 300,
    marginBottom: 15,
    marginRight: 10,
    padding: 5,
    paddingBottom: 10,
    overflow: "scroll"
  }
}));
