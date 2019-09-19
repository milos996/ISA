import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import Rating from "react-rating";
import StarBorderIcon from "@material-ui/icons/StarBorder";
import StarIcon from "@material-ui/icons/Star";
import { Typography } from "@material-ui/core";
import { rateRoom } from "../../store/hotel/actions";

export default function RoomRating({ reservationId }) {
  const dispatch = useDispatch();

  function handleRating(value) {
    dispatch(rateRoom({ reservationId: reservationId, mark: value }));
  }

  return (
    <div>
      <Typography>Room rating</Typography>
      <Rating
        emptySymbol={<StarBorderIcon></StarBorderIcon>}
        fullSymbol={<StarIcon></StarIcon>}
        onClick={handleRating}
        stop={10}
      ></Rating>
    </div>
  );
}
