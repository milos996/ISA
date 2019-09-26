import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import Rating from "react-rating";
import StarBorderIcon from "@material-ui/icons/StarBorder";
import StarIcon from "@material-ui/icons/Star";
import { Typography } from "@material-ui/core";
import { rateHotel } from "../../store/hotel/actions";

export default function HotelRating({ reservationId }) {
  const dispatch = useDispatch();

  function handleRating(value) {
    dispatch(
      rateHotel({
        reservationId: reservationId,
        mark: value
      })
    );
  }

  return (
    <div>
      <Typography>Hotel rating</Typography>
      <Rating
        emptySymbol={<StarBorderIcon></StarBorderIcon>}
        fullSymbol={<StarIcon></StarIcon>}
        onClick={handleRating}
        stop={10}
      ></Rating>
    </div>
  );
}
