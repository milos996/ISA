import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useSelector, useDispatch } from "react-redux";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import Paper from "@material-ui/core/Paper";
import Typography from "@material-ui/core/Typography";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import Button from "@material-ui/core/Button";
import { selectHotels } from "../store/hotel/selectors";
import { selectUsers } from "../store/user/selectors";
import EntityAddTabs from "../components/EntityAddTabs";

// import { postAdminForAirline } from "../store/airline/actions";
// import { postAdminForRentACar} from "../store/rent-a-car/actions"
import {
  postAdminForHotel,
  fetchHotelsThatDontHaveAdmin
} from "../store/hotel/actions";
import { fetchUsersThatDontHaveEntity } from "../store/user/actions";

const SELECT_TYPE = {
  RENT_A_CAR_SELECT: "rent_a_car_select",
  AIRLINE_SELECT: "airline_select",
  HOTEL_SELECT: "hotel_select"
};

const ENTITY_LABEL = {
  [SELECT_TYPE.RENT_A_CAR_SELECT]: "Rent a car",
  [SELECT_TYPE.AIRLINE_SELECT]: "Airline",
  [SELECT_TYPE.HOTEL_SELECT]: "Hotel"
};

export default function AdminPage() {
  const classes = useStyles();
  const dispatch = useDispatch();
  //  TODO Odkomentarisi kada se implementiraju airlines store i rent-a-car store
  //   const airlines = useSelector(selectAirlines);
  //   const rentACars = useSelector(selectRentACars);
  const hotels = useSelector(selectHotels);
  const users = useSelector(selectUsers);

  const [currentEntity, setCurrentEntity] = useState({
    content: null,
    saveAction: () => {},
    type: null
  });
  const [selectedUser, setSelectedUser] = useState(null);

  function handleHotelSelectChange({ target }) {
    setCurrentEntity({
      content: hotels.find(val => val.id === target.value),
      saveAction: postAdminForHotel,
      type: SELECT_TYPE.HOTEL_SELECT
    });
  }

  //   function handleAirlineSelectChange({ target }) {
  //     //   TODO stavi ovdje akciju postAdminForAirline kada budes imao store za airline

  //     setCurrentEntity({
  //       content: airlines.find(val => val.id === target.value),
  //       saveAction: () => {},
  //       type: SELECT_TYPE.AIRLINE_SELECT
  //     });
  //   }

  //   function handleRentACarSelectChange({ target }) {
  //     //   TODO stavi ovdje akciju postAdminForRentACar kada budes imao store za rent-a-car

  //     setCurrentEntity({
  //       content: rentACars.find(val => val.id === target.value),
  //       saveAction: () => {},
  //       type: SELECT_TYPE.RENT_A_CAR_SELECT
  //     });
  //   }

  function handlePostAdmin() {}

  useEffect(() => {
    dispatch(fetchUsersThatDontHaveEntity());
    dispatch(fetchHotelsThatDontHaveAdmin());
    // TODO Kreiraj akcije -> |
    // dispatch(fetchAirlinesThatDontHaveAdmin());
    // dispatch(fetchRentACarsThatDontHaveAdmin());
  }, []);

  return (
    <div className="horizontal-items">
      <div className="vertical-items w-50">
        <div className="horizontal-items f-w">
          {/* ODKOMENTARISI KADA BUDES IMAO AIRLINES */}
          {/* <FormControl  classes={{ root: classes.formControl }}>
          <InputLabel htmlFor="airline">AIRLINE</InputLabel>
          <Select
            value={!!currentEntity.content && currentEntity.content.id}
            inputProps={{
              id: "airline"
            }}
            onChange={handleAirlineSelectChange}
          >
            {airlines.map(airline => (
              <MenuItem value={airline.id}>{airline.name}</MenuItem>
            ))}
          </Select>
        </FormControl> */}
          <FormControl classes={{ root: classes.formControl }}>
            <InputLabel htmlFor="hotel">HOTEL</InputLabel>
            <Select
              value={!!currentEntity.content && currentEntity.content.id}
              onChange={handleHotelSelectChange}
              inputProps={{
                id: "hotel"
              }}
            >
              {hotels.map(hotel => (
                <MenuItem key={hotel.id} value={hotel.id}>
                  {hotel.name}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          {/* ODKOMENTARISI KADA BUDES IMAO RENT A CARS */}
          {/* <FormControl  classes={{ root: classes.formControl }}>
          <InputLabel htmlFor="rent-a-car">RENT A CAR</InputLabel>
          <Select
            value={!!currentEntity.content && currentEntity.content.id}
            onChange={handleRentACarSelectChange}
            inputProps={{
              id: "rent-a-car"
            }}
          >
            {rentACars.map(rentCar => (
              <MenuItem value={rentCar.id}>{rentCar.name}</MenuItem>
            ))}
          </Select>
        </FormControl> */}
        </div>
        <FormControl classes={{ root: classes.formControl }}>
          <InputLabel htmlFor="users">USER</InputLabel>
          <Select
            value={!!selectedUser && selectedUser.id}
            onChange={({ target }) => {
              setSelectedUser(users.find(val => val.id === target.value));
            }}
            inputProps={{
              id: "users"
            }}
          >
            {users.map(user => (
              <MenuItem key={user.id} value={user.id}>
                {user.email}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
        {!!currentEntity.content && !!selectedUser && (
          <div className="horizontal-items ">
            <Paper className={classes.paper}>
              <Typography variant="h5" component="h3">
                Selected {ENTITY_LABEL[currentEntity.type]}
              </Typography>
              <Typography component="p">
                {!!currentEntity.content && (
                  <span className="selected-text">
                    <strong>{currentEntity.content.name.toUpperCase()}</strong>
                  </span>
                )}
              </Typography>
            </Paper>
            <Paper className={classes.paper}>
              <Typography variant="h5" component="h3">
                Selected User
              </Typography>
              <Typography component="p">
                {!!selectedUser && (
                  <span className="selected-text">
                    <strong>{selectedUser.email.toUpperCase()}</strong>
                  </span>
                )}
              </Typography>
            </Paper>
            <Button
              variant="contained"
              color="primary"
              className={classes.button}
              onClick={handlePostAdmin}
            >
              Post
            </Button>
          </div>
        )}
      </div>
      <div className="vertical-items w-50">
        <EntityAddTabs />
      </div>
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  paper: {
    padding: theme.spacing(3, 2),
    marginRight: "12px"
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120
  },
  button: {
    margin: theme.spacing(1),
    height: "36px"
  }
}));
