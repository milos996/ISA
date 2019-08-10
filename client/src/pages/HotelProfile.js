import React from "react";
import { useDispatch } from "react-redux";
import { putHotelDetails } from "../store/hotel/actions";

const HotelProfile = ({ match }) => {
  console.log({ match });
  const dispatch = useDispatch();

  return (
    <div>
      <h1>Hotel Profile</h1>
      <button onClick={() => dispatch(putHotelDetails("cao"))}>
        put hotell
      </button>
      <button
        onClick={() =>
          dispatch({ type: "USER_SAGA_OPTION", payload: { obj: "cao" } })
        }
      >
        TAKE saga call
      </button>
    </div>
  );
};

export default HotelProfile;
