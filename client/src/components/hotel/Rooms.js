import React from "react";
import { useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";
import { selectHotelRooms } from "../../store/hotel/selectors";
import HotelRoom from "./Room";

export default function HotelRooms() {
  const rooms = useSelector(selectHotelRooms);
  const classes = useStyles();

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <h2>Rooms</h2>
      <div className="rooms-container">
        {Object.keys(rooms).map(roomId => (
          <HotelRoom key={roomId} room={rooms[roomId]} />
        ))}
      </div>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column"
  }
}));
