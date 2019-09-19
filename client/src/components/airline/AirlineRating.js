import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import Rating from "react-rating";
import StarBorderIcon from "@material-ui/icons/StarBorder";
import StarIcon from "@material-ui/icons/Star";
import { Typography } from "@material-ui/core";
import { rateAirline } from "../../store/airline/actions";

export default function AirlineRating({ airplaneTicketId }) {
  const dispatch = useDispatch();

  function handleRating(value) {
    dispatch(
      rateAirline({
        reservationId: airplaneTicketId,
        mark: value
      })
    );
  }

  return (
    <div>
      <Typography>Airline rating</Typography>
      <Rating
        emptySymbol={<StarBorderIcon></StarBorderIcon>}
        fullSymbol={<StarIcon></StarIcon>}
        onClick={handleRating}
        stop={10}
      ></Rating>
    </div>
  );
}
