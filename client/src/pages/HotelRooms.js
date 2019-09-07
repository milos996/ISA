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
import { fetchHotelRooms } from "../store/hotel/actions";
import { selectHotelRooms } from "../store/hotel/selectors";
import room from "../assets/room.png";
import { formatRoomDetails } from "../util/format";
import { fetchHotelService, reserveRooms } from "../store/hotel/actions";
import SelectServices from "../components/hotel/SelectServices";
import { addRemoveItemFromList } from "../util/hotel";
import { getDiffBetweenTwoDates } from "../util/date";

export default function HotelRoomsPage({ match, history, location }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const rooms = useSelector(selectHotelRooms);
  const [date, setDate] = useState(
    location.state.startDate && location.state.startDate
  );
  const [numberOfNights, setNumberOfNights] = useState(
    getDiffBetweenTwoDates(location.state.startDate, location.state.endDate)
  );
  const [numberOfPeople, setNumberOfPeople] = useState(null);
  const [price, setPrice] = useState({
    min: null,
    max: null
  });
  const [selectedAdditionalServices, setAdditionalServices] = useState([]);
  const [isModalVisible, setModalVisibility] = useState(false);
  const [selectedRooms, setSelectedRooms] = useState([]);

  function handleDateChange(date) {
    setDate(date);
  }

  function handleSearchButton() {
    if (!date || !numberOfNights || !numberOfPeople) {
      alert(
        "You must pick a date, number of nights you want to stay and number of people per room!"
      );
      return;
    }

    dispatch(
      fetchHotelRooms({
        hotelId: match.params.id,
        date,
        numberOfNights,
        numberOfPeople,
        price
      })
    );
  }

  function getPriceForReservations() {
    return (
      selectedRooms.reduce((reducer, item) => {
        return reducer + item.price * numberOfNights;
      }, 0) +
      selectedAdditionalServices.reduce(
        (serviceReducer, additionalService) =>
          serviceReducer + additionalService.price,
        0
      )
    );
  }

  function handleSelectService(service, isSelected) {
    setAdditionalServices(currState =>
      addRemoveItemFromList(currState, service, isSelected)
    );
  }

  function handleReserveButton() {
    dispatch(
      reserveRooms({
        rooms: selectedRooms,
        additionalService: selectedAdditionalServices,
        date,
        numberOfNights
      })
    );
  }

  useEffect(() => {
    dispatch(fetchHotelService({ hotelId: match.params.id }));
  }, [match.params.id]);

  useEffect(() => {
    if (!!date && !!numberOfNights) {
      dispatch(
        fetchHotelRooms({
          hotelId: match.params.id,
          date,
          numberOfNights,
          numberOfPeople,
          price
        })
      );
    }
  }, []);

  return (
    <div>
      <Modal open={isModalVisible}>
        <div className="modal-container-2">
          <SelectServices
            setServices={handleSelectService}
            selectedAdditionalServices={selectedAdditionalServices}
            hotelId={match.params.id}
          />
          <Button onClick={e => setModalVisibility(false)}>Close</Button>
        </div>
      </Modal>
      <div className="horizontal-items p-sm">
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardDatePicker
            disableToolbar
            minDate={new Date()}
            variant="inline"
            format="dd/MM/yyyy"
            margin="normal"
            label="Start date"
            value={date}
            onChange={handleDateChange}
            KeyboardButtonProps={{
              "aria-label": "change date"
            }}
          />
        </MuiPickersUtilsProvider>
        <TextField
          required
          onChange={({ currentTarget }) =>
            setNumberOfNights(currentTarget.value)
          }
          label="Number of nights"
          defaultValue={numberOfNights}
          className={classes.textField}
          margin="normal"
          type="number"
        />
        <TextField
          required
          label="Number of people"
          defaultValue={numberOfPeople}
          className={classes.textField}
          onChange={({ currentTarget }) =>
            setNumberOfPeople(currentTarget.value)
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
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={() => setModalVisibility(true)}
      >
        AdditionalServices
      </Button>
      {!!selectedRooms.length && (
        <Button
          variant="contained"
          color="primary"
          className={classes.button}
          onClick={handleReserveButton}
        >
          Reserve - {getPriceForReservations()} $
        </Button>
      )}
      <div className="horizontal-items a-i-fs f-ww">
        {rooms.map(val => (
          <NavigationCards
            selected={
              selectedRooms.findIndex(
                selectedRoom => selectedRoom.id === val.id
              ) !== -1
            }
            key={val.id}
            image={room}
            title={`${val.floor} - ${val.number}`}
            description={formatRoomDetails(val, numberOfNights)}
            cardClick={() => {
              setSelectedRooms(currState =>
                addRemoveItemFromList(currState, val)
              );
            }}
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
