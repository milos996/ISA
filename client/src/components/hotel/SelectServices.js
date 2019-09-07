import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import FormLabel from "@material-ui/core/FormLabel";
import FormControl from "@material-ui/core/FormControl";
import FormGroup from "@material-ui/core/FormGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormHelperText from "@material-ui/core/FormHelperText";
import Checkbox from "@material-ui/core/Checkbox";
import { selectHotelServices } from "../../store/hotel/selectors";
import { fetchHotelService } from "../../store/hotel/actions";

export default function SelectServices({
  setServices,
  selectedAdditionalServices,
  hotelId
}) {
  const classes = useStyles();
  const hotelServices = useSelector(selectHotelServices);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchHotelService({ hotelId }));
  }, [hotelId]);

  return (
    <div>
      <FormControl component="fieldset" className={classes.formControl}>
        <FormLabel component="legend">Available services</FormLabel>
        <FormGroup>
          {Object.keys(hotelServices).map(serviceId => (
            <FormControlLabel
              key={serviceId}
              control={
                <Checkbox
                  onChange={({ target }) =>
                    setServices(hotelServices[serviceId], target.checked)
                  }
                  value={
                    selectedAdditionalServices.findIndex(
                      val => val.id === serviceId
                    ) !== -1
                  }
                />
              }
              label={`${hotelServices[serviceId].name} -  ${hotelServices[serviceId].price}$`}
            />
          ))}
        </FormGroup>
      </FormControl>
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex"
  },
  formControl: {
    margin: theme.spacing(3)
  }
}));
