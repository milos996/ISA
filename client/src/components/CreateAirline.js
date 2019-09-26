import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
// import { selectAirlineDetails } from "../store/airline/selectors";

export default function CreateAirline() {
  const dispatch = useDispatch();
  const classes = useStyles();
  //   const airline = useSelector(selectAirlineDetails);

  const [data, setData] = useState({
    name: null,
    description: null,
    checkingInSuitcasePrice: null,
    handLuggagePrice: null,
    street: null
  });

  return (
    <div className="vertical-items">
      <TextField
        required
        onChange={({ currentTarget }) =>
          setData(currState => ({
            ...currState,
            name: currentTarget.value
          }))
        }
        label="Name"
        defaultValue={data.name}
        className={classes.textField}
        margin="normal"
        type="text"
      />
      <TextField
        required
        onChange={({ currentTarget }) =>
          setData(currState => ({
            ...currState,
            description: currentTarget.value
          }))
        }
        multiline
        rowsMax="8"
        label="Description"
        defaultValue={data.description}
        className={classes.textField}
        margin="normal"
        type="text"
      />
      <TextField
        required
        onChange={({ currentTarget }) =>
          setData(currState => ({
            ...currState,
            checkingInSuitcasePrice: currentTarget.value
          }))
        }
        label="Checking in suitcase price"
        defaultValue={data.checkingInSuitcasePrice}
        className={classes.textField}
        margin="normal"
        type="number"
      />
      <TextField
        required
        onChange={({ currentTarget }) =>
          setData(currState => ({
            ...currState,
            handLuggagePrice: currentTarget.value
          }))
        }
        label="Hand luggage price"
        defaultValue={data.handLuggagePrice}
        className={classes.textField}
        margin="normal"
        type="number"
      />
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  textField: {
    textField: {
      marginLeft: theme.spacing(1),
      marginRight: theme.spacing(1),
      width: 200
    }
  }
}));
