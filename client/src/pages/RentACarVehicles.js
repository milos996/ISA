import "date-fns";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  KeyboardDatePicker,
  MuiPickersUtilsProvider
} from "@material-ui/pickers";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import DateFnsUtils from "@date-io/date-fns";
import Modal from "@material-ui/core/Modal";
import NavigationCards from "../components/UI/NavigationCards";
import { makeStyles } from "@material-ui/core/styles";
import dateFormat from "dateformat";
import { fetchRentACarVehicles } from "../store/rent-a-car/actions";
import vehicle from "../assets/car.png";
import {
  selectRentACarVehicles,
  selectRentACarLocationInformation
} from "../store/rent-a-car/selectors";

export default function RentACarVehiclesPage({ match, location }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const vehicles = useSelector(selectRentACarVehicles);
  const [pickUpDate, setPickUpDate] = useState(location.state.startDate);
  const [dropOffDate, setDropOffDate] = useState(location.state.endDate);
  const [seats, setSeats] = useState(null);
  const [yearOfProduction, setYearOfProduction] = useState(null);
  const [price, setPrice] = useState({
    min: null,
    max: null
  });
  const [isModalVisible, setModalVisibility] = useState(false);
  const [selectedVehicle, setSelectedVehicle] = useState();

  function handleSearchButton() {
    dispatch(fetchRentACarVehicles());
  }

  useEffect(() => {
    dispatch(
      fetchRentACarVehicles({
        rentACarId: match.params.id
      })
    );
  });

  return (
    <div>
      <Modal open={isModalVisible}>
        <div className="modal-container-2">
          <Button onClick={e => setModalVisibility(false)}>Close</Button>
        </div>
      </Modal>
      <div className="vertical-items p-sm">
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardDatePicker
            disableToolbar
            minDate={new Date()}
            variant="inline"
            format="dd/MM/yyyy"
            margin="normal"
            label="Pick up date"
            value={pickUpDate}
            onChange={date => setPickUpDate(dateFormat(date, "yyyy-mm-dd"))}
            KeyboardButtonProps={{
              "aria-label": "change date"
            }}
          />
        </MuiPickersUtilsProvider>
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardDatePicker
            disableToolbar
            minDate={new Date()}
            variant="inline"
            format="dd/MM/yyyy"
            margin="normal"
            label="Drop off date"
            value={dropOffDate}
            onChange={date => setDropOffDate(dateFormat(date, "yyyy-mm-dd"))}
            KeyboardButtonProps={{
              "aria-label": "change date"
            }}
          />
        </MuiPickersUtilsProvider>
        <TextField
          required
          onChange={({ currentTarget }) => setSeats(currentTarget.value)}
          label="Number of seats"
          defaultValue={seats}
          className={classes.textField}
          margin="normal"
          type="number"
        />
        <TextField
          required
          label="Year of production"
          defaultValue={yearOfProduction}
          className={classes.textField}
          onChange={({ currentTarget }) =>
            setYearOfProduction(currentTarget.value)
          }
          margin="normal"
          type="number"
        />
        <TextField
          required
          label="Price range from"
          defaultValue={price.min}
          className={classes.textField}
          onChange={({ currentTarget }) =>
            setPrice(currState => ({ ...currState, min: currentTarget.value }))
          }
          margin="normal"
          type="number"
        />
        <TextField
          required
          label="Price range to"
          defaultValue={price.max}
          className={classes.textField}
          onChange={({ currentTarget }) =>
            setPrice(currState => ({ ...currState, max: currentTarget.value }))
          }
          margin="normal"
          type="number"
        />
        <Button
          variant="contained"
          color="primary"
          className={classes.button}
          onClick={handleSearchButton}
        >
          Search
        </Button>
      </div>
      <div className="vertical-items a-i-fs f-ww">
        {vehicles.map(val => (
          <NavigationCards
            selected={
              selectedVehicle.findIndex(
                selectedVehicle => selectedVehicle.id === val.id
              ) !== -1
            }
            key={val.id}
            image={vehicle}
            title={`${val.brand} - ${val.model}`}
            description={"OP"}
          />
        ))}
      </div>
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  container: {
    display: "flex",
    flexWrap: "wrap"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200
  },
  dense: {
    marginTop: 19
  },
  menu: {
    width: 200
  },
  button: {
    margin: theme.spacing(1)
  }
}));
