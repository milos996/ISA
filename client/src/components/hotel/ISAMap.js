import React, { useState, useEffect } from "react";
import { Map, TileLayer, Marker, Popup } from "react-leaflet";
import Container from "@material-ui/core/Container";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch } from "react-redux";
import { getHotelLocationOnLatLng } from "../../store/hotel/actions";

const ISAMap = ({ address, setStreet = () => {}, hasClick = true }) => {
  const mapRef = React.createRef();

  const classes = useStyles();
  const dispatch = useDispatch();

  const [location, setLocation] = useState({
    latlng: {
      lat: 45.275,
      lng: 19.841
    }
  });

  function setLatLng({ latlng }) {
    dispatch(getHotelLocationOnLatLng(latlng));
    setLocation({ latlng });
  }

  useEffect(() => {
    if (mapRef !== null) {
      mapRef.current.leafletElement.locate();
    }
  }, []);

  useEffect(() => {
    if (!hasClick) {
      setLocation({
        latlng: {
          lat: address.latitude,
          lng: address.longitude
        }
      });
    }
  }, [address]);

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <Container
        classes={{
          root: classes.inputContainer
        }}
      >
        <TextField
          label="State"
          className={classes.textField}
          margin="normal"
          value={address.state}
          InputProps={{
            readOnly: true
          }}
        />
        <TextField
          label="City"
          className={classes.textField}
          margin="normal"
          value={address.city}
          InputProps={{
            readOnly: true
          }}
        />
        <TextField
          label="Street"
          className={classes.textField}
          margin="normal"
          defaultValue={address.street}
          onChange={({ currentTarget }) => setStreet(currentTarget.value)}
          InputProps={{
            readOnly: !hasClick
          }}
        />
      </Container>
      <Map
        style={{ height: 500 }}
        center={location.latlng}
        length={4}
        onClick={e => {
          if (hasClick) {
            setLatLng(e);
          }
        }}
        ref={mapRef}
        zoom={10}
      >
        <TileLayer
          attribution='&amp;copy <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Marker position={location.latlng}>
          <Popup>Selected Location</Popup>
        </Marker>
      </Map>
    </Container>
  );
};

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column",
    width: "70%"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "100%"
  },
  inputContainer: {
    padding: "0px 0px 0px 0px",
    display: "flex",
    flexDirection: "row"
  }
}));

export default ISAMap;
