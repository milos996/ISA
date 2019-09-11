import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";
import { selectHotelRoomsForEdit } from "../../store/hotel/selectors";
import HotelRoom from "./Room";
import { fetchHotelRooms } from "../../store/hotel/actions";

export default function HotelRooms({ hotelId }) {
  const rooms = useSelector(selectHotelRoomsForEdit);
  const classes = useStyles();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(
      fetchHotelRooms({
        hotelId
      })
    );
  }, [hotelId]);

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
