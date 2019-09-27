import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { makeStyles } from '@material-ui/core/styles';
import { fetchHotels } from '../store/hotel/actions';
import { selectHotels } from '../store/hotel/selectors';
import building from '../assets/skyline.png';
import NavigationCards from '../components/UI/NavigationCards';
import ISAMap from '../components/hotel/ISAMap';
import Button from '@material-ui/core/Button';
import ButtonGroup from '@material-ui/core/ButtonGroup';
import {
  KeyboardDatePicker,
  MuiPickersUtilsProvider
} from '@material-ui/pickers';
import TextField from '@material-ui/core/TextField';
import DateFnsUtils from '@date-io/date-fns';
import { searchHotelsBasedOnFilters, sortHotels } from '../store/hotel/actions';
import { userTokenSelector } from '../store/user/selectors';

export default function HotelsPage({ history, location }) {
  const dispatch = useDispatch();
  const classes = useStyles();
  const hotels = useSelector(selectHotels);
  const [currentLocation, setCurrentLocation] = useState(null);
  const [city, setCity] = useState(null);
  const [country, setCountry] = useState(null);
  const [hotelName, setHotelName] = useState(null);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const userToken = useSelector(userTokenSelector);

  function handleMouseEnter(id) {
    setCurrentLocation(hotels.find(val => val.id === id).address);
  }

  function handleSearch() {
    dispatch(
      searchHotelsBasedOnFilters({
        startDate,
        endDate,
        hotelName,
        city,
        country
      })
    );
  }

  function handleSortByName() {
    dispatch(sortHotels({ by: 'name' }));
  }

  function handleSortByAddress() {
    dispatch(sortHotels({ by: 'address' }));
  }

  function handleSortByRating() {
    dispatch(sortHotels({ by: 'rating' }));
  }

  useEffect(() => {
    dispatch(fetchHotels());
  }, []);

  return (
    <div className="horizontal-items a-i-fs f-ww">
      <div className="vertical-items a-i-fs f-ww">
        {userToken && !!location.state && (
          <div>
            <Button
              variant="contained"
              className={classes.button}
              onClick={() => {
                history.push('/');
              }}
            >
              Cancel Reservation
            </Button>

            <Button
              variant="contained"
              className={classes.button}
              onClick={() => {
                history.push({
                  pathname: `/rent-a-cars`,
                  state: {
                    airplaneTicketId: location.state
                      ? location.state.airplaneTicketId
                      : ''
                  }
                });
              }}
            >
              Reserve vehicle
            </Button>
          </div>
        )}
        <div className="vertical-items a-i-fs f-ww">
          <TextField
            required
            onChange={({ currentTarget }) => setCity(currentTarget.value)}
            label="City"
            defaultValue={city}
            className={classes.textField}
            margin="normal"
            type="text"
          />
          <TextField
            required
            onChange={({ currentTarget }) => setCountry(currentTarget.value)}
            label="Country"
            defaultValue={country}
            className={classes.textField}
            margin="normal"
            type="text"
          />
          <TextField
            required
            onChange={({ currentTarget }) => setHotelName(currentTarget.value)}
            label="Name"
            defaultValue={hotelName}
            className={classes.textField}
            margin="normal"
            type="text"
          />
          <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <KeyboardDatePicker
              disableToolbar
              minDate={new Date()}
              variant="inline"
              format="dd/MM/yyyy"
              margin="normal"
              label="Start date"
              value={startDate}
              onChange={date => setStartDate(date)}
              KeyboardButtonProps={{
                'aria-label': 'change date'
              }}
            />
          </MuiPickersUtilsProvider>
          <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <KeyboardDatePicker
              disableToolbar
              minDate={!!startDate ? startDate : new Date()}
              variant="inline"
              format="dd/MM/yyyy"
              margin="normal"
              label="End date"
              value={endDate}
              onChange={date => setEndDate(date)}
              KeyboardButtonProps={{
                'aria-label': 'change date'
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
        </div>
        {hotels.map(val => (
          <NavigationCards
            onMouseEnter={handleMouseEnter}
            id={val.id}
            key={val.id}
            image={building}
            title={val.name}
            description={val.description}
            tooltip={val.description}
            cardClick={() => {
              history.push({
                pathname: `/hotel-reservation/${val.id}/rooms`,
                state: {
                  startDate,
                  endDate,
                  airplaneTicketId: location.state
                    ? location.state.airplaneTicketId
                    : ''
                }
              });
            }}
          />
        ))}
      </div>

      {currentLocation && <ISAMap address={currentLocation} hasClick={false} />}
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap'
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
