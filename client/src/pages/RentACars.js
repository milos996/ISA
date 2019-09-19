import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import NavigationCards from "../components/UI/NavigationCards";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import ButtonGroup from "@material-ui/core/ButtonGroup";
import DateFnsUtils from "@date-io/date-fns";
import {
  KeyboardDatePicker,
  MuiPickersUtilsProvider
} from "@material-ui/pickers";
import Grid from "@material-ui/core/Grid";
import dateFormat from "dateformat";
import Modal from "@material-ui/core/Modal";
import CreateRentACar from "../components/rent-a-car/CreateRentACar";
import EditRentACar from "../components/rent-a-car/EditRentACar";
import RentACarIncome from "../components/rent-a-car/Income";
import RentACarBusyness from "../components/rent-a-car/Busyness";
import vehicle from "../assets/car.png";
import ISAMap from "../components/hotel/ISAMap";
import {
  fetchRentACars,
  searchRentACars,
  sortRentACars,
  fetchRentACarDetails,
  fetchVehicles,
  fetchRentACarLocationInformation
} from "../store/rent-a-car/actions";
import {
  selectRentACars,
  selectRentACarDetails
} from "../store/rent-a-car/selectors";
import { userDataSelector } from "../store/user/selectors";

export default function RentACarPage({ history, location }) {
  const user = useSelector(userDataSelector);
  const dispatch = useDispatch();
  const classes = useStyles();
  const rentACars = useSelector(selectRentACars);
  const [modalStyle] = React.useState(getModalStyle);
  const [createModalVisibility, setCreateModalVisibility] = useState(false);
  const [updateModalVisibility, setUpdateModalVisibility] = useState(false);
  const [showIncomeModalVisibility, setShowIncomeModalVisibility] = useState(
    false
  );
  const [
    showBusynessModalVisibility,
    setShowBusynessModalVisibility
  ] = useState(false);

  const selected = useSelector(selectRentACarDetails);

  const [name, setName] = useState(null);
  const [city, setCity] = useState(null);
  const [state, setState] = useState(null);
  const [pickUpDate, setPickUpDate] = useState(location.state.startDate);
  const [dropOffDate, setDropOffDate] = useState(location.state.endDate);

  const [currentLocation, setCurrentLocation] = useState(null);

  function closeModal() {
    setUpdateModalVisibility(false);
    setCreateModalVisibility(false);
    setShowIncomeModalVisibility(false);
    setShowBusynessModalVisibility(false);
  }

  function handleUpdate() {
    if (selected.name === undefined) alert("Please select rent a car first");
    else setUpdateModalVisibility(true);
  }

  function handleMouseEnter(id) {
    const selected = rentACars.find(val => val.id === id);
    dispatch(fetchRentACarDetails(selected));
    setCurrentLocation(selected.address);
  }

  function handleSearch() {
    dispatch(
      searchRentACars({
        name,
        state,
        city,
        pickUpDate,
        dropOffDate
      })
    );
  }

  function handleShowIncome() {
    if (selected.name === undefined) alert("Please select rent a car first");
    else setShowIncomeModalVisibility(true);
  }

  function handleShowBusyness() {
    if (selected.name === undefined) alert("Please select rent a car first");
    else setShowBusynessModalVisibility(true);
  }

  function handleSortByName() {
    dispatch(sortRentACars({ by: "name" }));
  }

  function handleSortByAddress() {
    dispatch(sortRentACars({ by: "address" }));
  }

  function handleSortByRating() {
    dispatch(sortRentACars({ by: "rating" }));
  }

  useEffect(() => {
    dispatch(fetchRentACars());
  }, []);

  useEffect(() => {
    dispatch(fetchVehicles());
  }, []);

  return (
    <div>
      <Modal open={createModalVisibility}>
        <div style={modalStyle} className={classes.paper}>
          <CreateRentACar closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Modal open={updateModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <EditRentACar closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Modal open={showIncomeModalVisibility}>
        <div style={modalStyle} className={classes.paper}>
          <RentACarIncome closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Modal open={showBusynessModalVisibility}>
        <div style={modalStyle} className={classes.paper}>
          <RentACarBusyness closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <TextField
            required
            label="Name"
            defaultValue={name}
            className={classes.textField}
            margin="normal"
            type="text"
            onChange={({ currentTarget }) => setName(currentTarget.value)}
          />
          <TextField
            required
            label="City"
            defaultValue={city}
            className={classes.textField}
            margin="normal"
            type="text"
            onChange={({ currentTarget }) => setCity(currentTarget.value)}
          />
          <TextField
            required
            label="State"
            defaultValue={state}
            className={classes.textField}
            margin="normal"
            type="text"
            onChange={({ currentTarget }) => setState(currentTarget.value)}
          />
          <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <KeyboardDatePicker
              disableToolbar
              minDate={new Date()}
              variant="inline"
              format="dd/MM/yyyy"
              margin="normal"
              label="Start date"
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
              minDate={!!pickUpDate ? pickUpDate : new Date()}
              variant="inline"
              format="dd/MM/yyyy"
              margin="normal"
              label="End date"
              value={dropOffDate}
              onChange={date => setDropOffDate(dateFormat(date, "yyyy-mm-dd"))}
              KeyboardButtonProps={{
                "aria-label": "change date"
              }}
            />
          </MuiPickersUtilsProvider>
          <Button
            variant="contained"
            color="primary"
            className={classes.button}
            onClick={handleSearch}
          >
            Search
          </Button>
          <ButtonGroup
            size="small"
            aria-label="small outlined button group"
            className={classes.button}
          >
            <Button disabled>SORT BY</Button>
            <Button onClick={handleSortByName}>NAME</Button>
            <Button onClick={handleSortByAddress}>ADDRESS</Button>
            <Button onClick={handleSortByRating}>RATING</Button>
          </ButtonGroup>

          {user.role === "RENT_A_CAR_ADMIN" ? (
            <div>
              <Button
                variant="contained"
                color="primary"
                className={classes.button}
                onClick={() => setCreateModalVisibility(true)}
              >
                Create
              </Button>
            </div>
          ) : null}
        </Grid>

        <Grid item xs={3} sm={3}>
          {rentACars.map((val, index) => (
            <div>
              <NavigationCards
                onMouseEnter={handleMouseEnter}
                id={val.id}
                key={val.id}
                image={vehicle}
                title={val.name}
                description={val.description + ", Rating:" + val.rating}
                tooltip={val.description}
                cardClick={() => {
                  dispatch(
                    fetchRentACarLocationInformation(rentACars[index].address)
                  );
                  history.push({
                    pathname: `/rent-a-cars/${val.id}/vehicles`,
                    state: {
                      pickUpDate: pickUpDate,
                      dropOffDate: dropOffDate,
                      airplaneTicketId: location.state.airplaneTicketId
                    }
                  });
                }}
              />

              {user.role === "RENT_A_CAR_ADMIN" ? (
                <div>
                  <Button
                    variant="contained"
                    color="primary"
                    className={classes.button}
                    onClick={handleUpdate}
                  >
                    Update
                  </Button>
                  <Button
                    variant="contained"
                    color="primary"
                    className={classes.button}
                    onClick={handleShowIncome}
                  >
                    Income
                  </Button>
                  <Button
                    variant="contained"
                    color="primary"
                    className={classes.button}
                    onClick={handleShowBusyness}
                  >
                    Busyness
                  </Button>
                </div>
              ) : null}
            </div>
          ))}
        </Grid>
        <Grid item xs={12} sm={9}>
          {currentLocation && (
            <ISAMap address={currentLocation} hasClick={false} />
          )}
        </Grid>
      </Grid>
    </div>
  );
}

function getModalStyle() {
  const top = 50;
  const left = 50;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`
  };
}

const useStyles = makeStyles(theme => ({
  paper: {
    position: "absolute",
    width: "50%",
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #008080",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
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
    margin: theme.spacing(1),
    background: "#008080"
  }
}));
