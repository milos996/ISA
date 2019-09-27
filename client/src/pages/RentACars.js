import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import ButtonGroup from "@material-ui/core/ButtonGroup";
import { Container } from "@material-ui/core";
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import CardMedia from "@material-ui/core/CardMedia";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import Rating from "react-rating";
import StarBorderIcon from "@material-ui/icons/StarBorder";
import StarIcon from "@material-ui/icons/Star";
import DateFnsUtils from "@date-io/date-fns";
import {
  KeyboardDatePicker,
  MuiPickersUtilsProvider
} from "@material-ui/pickers";
import dateFormat from "dateformat";
import Modal from "@material-ui/core/Modal";
import CreateRentACar from "../components/rent-a-car/CreateRentACar";
import EditRentACar from "../components/rent-a-car/EditRentACar";
import RentACarIncome from "../components/rent-a-car/Income";
import RentACarBusyness from "../components/rent-a-car/Busyness";
import RentACarAvailability from "../components/rent-a-car/Availability";
import vehicle from "../assets/vehicle.png";
import Background from "../assets/background.jpg";
import ISAMap from "../components/hotel/ISAMap";
import {
  fetchRentACars,
  searchRentACars,
  sortRentACars,
  fetchRentACarDetails,
  fetchRentACarOffices,
  fetchVehicles,
  fetchRentACarLocationInformation,
  fetchOffices
} from "../store/rent-a-car/actions";
import {
  selectRentACars,
  selectRentACarDetails,
  selectRentACarOffices,
  selectOffices
} from "../store/rent-a-car/selectors";
import { userDataSelector } from "../store/user/selectors";

export default function RentACarPage({ history, location }) {
  const user = useSelector(userDataSelector);
  const dispatch = useDispatch();
  const classes = useStyles();
  const selected = useSelector(selectRentACarDetails);
  const rentACars = useSelector(selectRentACars);
  const offices = useSelector(selectOffices);

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
  const [
    showAvailabilityModalVisibility,
    setShowAvailabilityModalVisibility
  ] = useState(false);

  const [name, setName] = useState(null);
  const [city, setCity] = useState(null);
  const [state, setState] = useState(null);
  const [pickUpDate, setPickUpDate] = useState();
  const [dropOffDate, setDropOffDate] = useState();

  const [currentLocation, setCurrentLocation] = useState(null);

  useEffect(() => {
    if (location.state != undefined) {
      setPickUpDate(location.state.pickUpDate);
      setDropOffDate(location.state.dropOffDate);
    }
  });

  function closeModal() {
    setUpdateModalVisibility(false);
    setCreateModalVisibility(false);
    setShowIncomeModalVisibility(false);
    setShowBusynessModalVisibility(false);
    setShowAvailabilityModalVisibility(false);
  }

  function handleUpdate() {
    if (selected.name === undefined) alert("Please select rent a car first");
    else setUpdateModalVisibility(true);
  }

  function handleShowLocation(index) {
    dispatch(fetchRentACarDetails(rentACars[index].id));
    setCurrentLocation(rentACars[index].address);
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

  function handleShowIncome(val, index) {
    if (selected.name === undefined) alert("Please select rent a car first");
    else setShowIncomeModalVisibility(true);
  }

  function handleShowBusyness() {
    if (selected.name === undefined) alert("Please select rent a car first");
    else setShowBusynessModalVisibility(true);
  }

  function handleShowAvailability() {
    if (selected.name === undefined) alert("Please select rent a car first");
    else setShowAvailabilityModalVisibility(true);
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
    dispatch(fetchOffices());
  }, []);

  return (
    <Container className={classes.vertical} maxWidth="xl" maxHeight="xl">
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
      <Modal open={showAvailabilityModalVisibility}>
        <div style={modalStyle} className={classes.paper}>
          <RentACarAvailability closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Container className={classes.vertical}>
        <Container className={classes.horizontal}>
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
        </Container>
        <Container>
          <ButtonGroup size="small" aria-label="small outlined button group">
            <Button className={classes.buttonGroup} disabled>
              SORT BY
            </Button>
            <Button className={classes.buttonGroup} onClick={handleSortByName}>
              NAME
            </Button>
            <Button
              className={classes.buttonGroup}
              onClick={handleSortByAddress}
            >
              ADDRESS
            </Button>
            <Button
              className={classes.buttonGroup}
              onClick={handleSortByRating}
            >
              RATING
            </Button>
          </ButtonGroup>
          {user.role === "RENT_A_CAR_ADMIN" ? (
            <Button
              variant="contained"
              color="primary"
              className={classes.button}
              onClick={() => setCreateModalVisibility(true)}
            >
              Create
            </Button>
          ) : null}
        </Container>
      </Container>
      {rentACars.map((rac, index) => (
        <Container className={classes.horizontal}>
          <Card
            className={classes.card}
            key={index}
            tooltip={rac.description}
            onMouseEnter={() => {
              handleShowLocation(index);
            }}
          >
            <CardHeader title={rac.name}></CardHeader>
            <CardMedia
              className={classes.media}
              image={vehicle}
              onMouseEnter={() => {
                handleShowLocation(index);
              }}
              onClick={() => {
                dispatch(fetchRentACarLocationInformation(rac.address));
                history.push({
                  pathname: `/rent-a-cars/${rac.id}/vehicles`,
                  state: {
                    pickUpDate: pickUpDate,
                    dropOffDate: dropOffDate
                    //airplaneTicketId: location.state.airplaneTicketId
                  }
                });
              }}
            />
            <CardContent className={classes.content}>
              <Typography className={classes.description}>
                {rac.description}
              </Typography>
              <Typography>
                <Rating
                  readonly={true}
                  className={classes.rating}
                  initialRating={rac.rating}
                  stop={10}
                  fractions={100}
                  emptySymbol={<StarBorderIcon></StarBorderIcon>}
                  fullSymbol={<StarIcon></StarIcon>}
                ></Rating>
              </Typography>
              {offices.map((office, index) => (
                <Container className={classes.offices}>
                  {office.name === rac.name ? (
                    <Typography>{office.location}</Typography>
                  ) : null}
                </Container>
              ))}

              <br />
            </CardContent>
            {user.role === "RENT_A_CAR_ADMIN" ? (
              <CardActions>
                <Container className={classes.buttonGroupBckg}>
                  <ButtonGroup
                    size="small"
                    aria-label="small outlined button group"
                  >
                    <Button
                      variant="contained"
                      color="primary"
                      className={classes.buttonGroup}
                      onClick={handleUpdate}
                    >
                      Update
                    </Button>
                    <Button
                      variant="contained"
                      color="primary"
                      className={classes.buttonGroup}
                      onClick={handleShowIncome}
                    >
                      Income
                    </Button>
                    <Button
                      variant="contained"
                      color="primary"
                      className={classes.buttonGroup}
                      onClick={handleShowBusyness}
                    >
                      Busyness
                    </Button>
                    <Button
                      variant="contained"
                      color="primary"
                      className={classes.buttonGroup}
                      onClick={handleShowAvailability}
                    >
                      Availability
                    </Button>
                  </ButtonGroup>
                </Container>
              </CardActions>
            ) : null}
          </Card>
          <ISAMap
            className={classes.border}
            address={rentACars[index].address}
            hasClick={false}
          />
        </Container>
      ))}
    </Container>
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
  card: {
    marginTop: "9.5%",
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #008080",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(1, 1, 1),
    height: "70%"
  },
  media: {
    height: 128,
    backgroundSize: "contain"
  },
  content: {
    marginTop: "7%",
    textAlign: "center"
  },
  description: {
    marginLeft: 20,
    marginBottom: 10
  },
  rating: {},
  vertical: {
    display: "flex",
    flexDirection: "column",
    background: `url(${Background})`
  },
  horizontal: {
    display: "flex",
    flexDirection: "row",
    paddingBottom: 20,
    background: `url(${Background})`
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200
  },
  button: {
    background: "#008080",
    marginBottom: 25,
    marginLeft: 25,
    marginTop: 20,
    height: 35
  },
  buttonGroup: {
    background: "#008080",
    color: "#FFFFFF"
  },
  buttonGroupBckg: {
    background: "#FFFFFF",
    color: "#FFFFFF"
  },
  offices: {
    display: "flex",
    flexDirection: "column",
    textAlign: "center"
  }
}));
