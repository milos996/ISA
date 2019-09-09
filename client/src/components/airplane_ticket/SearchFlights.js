import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import "date-fns";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { doSearch } from "../../store/airplane_ticket/actions";
import Button from "@material-ui/core/Button";
import { selectSearchResults } from "../../store/airplane_ticket/selectors";
import SearchResults from "./SearchResults";

export default function SearchFlights() {
  const classes = useStyles();
  const dispatch = useDispatch();
  const searchResults = useSelector(selectSearchResults);

  const [search, setSearch] = useState({
    fromDestination: "",
    toDestination: "",
    departureDate: "",
    arrivalDate: ""
  });
  function handleSaveButton() {
    dispatch(doSearch(search));
  }

  function handleChangeDepartureDate(value) {
    setSearch({
      ...search,
      departureDate: value
    });
  }

  function handleChangeArrivalDate(value) {
    setSearch({
      ...search,
      arrivalDate: value
    });
  }

  function handleChange(event) {
    event.persist();
    setSearch(oldValues => ({
      ...oldValues,
      [event.target.name]: event.target.value
    }));
  }

  return (
    <form className={classes.container} noValidate autoComplete="off">
      <TextField
        id="outlined-name"
        label="From"
        className={classes.textField}
        margin="normal"
        variant="outlined"
        onChange={handleChange}
        inputProps={{
          name: "fromDestination",
          id: "from-destination"
        }}
      />
      <TextField
        id="outlined-name"
        label="To"
        className={classes.textField}
        margin="normal"
        variant="outlined"
        onChange={handleChange}
        inputProps={{
          name: "toDestination",
          id: "to-destination"
        }}
      />

      <TextField
        id="date"
        label="Departure date"
        type="date"
        onChange={({ currentTarget }) =>
          handleChangeDepartureDate(currentTarget.value)
        }
        className={classes.textField}
        InputLabelProps={{
          shrink: true
        }}
      />

      <TextField
        id="date"
        label="Arrival date"
        type="date"
        onChange={({ currentTarget }) =>
          handleChangeArrivalDate(currentTarget.value)
        }
        className={classes.textField}
        InputLabelProps={{
          shrink: true
        }}
      />
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleSaveButton}
      >
        Search
      </Button>
      {searchResults != null && <SearchResults searchResults={searchResults}/>}
    </form>
  );
}

const useStyles = makeStyles(theme => ({
  container: {
    display: "flex",
    flexWrap: "wrap",
    height: "fit-content",
    justifyContent: "normal"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1)
  },
  dense: {
    marginTop: theme.spacing(2)
  },
  menu: {
    width: 200
  },
  button: {
    margin: theme.spacing(1),
    width: "30%",
    marginLeft: "auto"
  }
}));
