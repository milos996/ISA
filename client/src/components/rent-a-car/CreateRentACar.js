import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { Map, TileLayer, Marker, Popup } from "react-leaflet";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { createRentACar } from "./../../store/rent-a-car/actions";

export default function CreateVehicle({ closeModal }) {
  const mapRef = React.createRef();
  const classes = useStyles();
  const dispatch = useDispatch();
  const [name, setName] = useState();
  const [description, setDescription] = useState();
  const [street, setStreet] = useState();

  const [location, setLocation] = useState({
    latlng: {
      lat: 45.275,
      lng: 19.841
    }
  });

  function setLatLng({ latlng }) {
    setLocation({ latlng });
  }

  useEffect(() => {
    if (mapRef !== null) {
      mapRef.current.leafletElement.locate();
    }
  }, []);

  function handleSaveButton() {
    dispatch(createRentACar({ name, description, location, street }));
    closeModal();
  }

  return (
    <Container
      classes={{
        root: classes.serviceRow
      }}
    >
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={handleSaveButton}
      >
        Save
      </Button>
      <TextField
        label="Name"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setName(currentTarget.value);
        }}
      />
      <TextField
        label="Description"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setDescription(currentTarget.value);
        }}
      />
      <TextField
        label="Street"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setStreet(currentTarget.value);
        }}
      />
      <Map
        style={{ height: 500 }}
        center={location.latlng}
        length={4}
        onClick={e => {
          setLatLng(e);
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
}

const useStyles = makeStyles(theme => ({
  root: {
    position: "absolute",
    left: "20%",
    top: "5%",
    width: "60%",
    display: "flex",
    flexDirection: "column"
  },
  serviceRow: {
    display: "flex",
    flexDirection: "column",
    width: "100%"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    flex: 1
  },
  margin: {
    margin: theme.spacing(1)
  },
  button: {
    margin: theme.spacing(1),
    width: "30%",
    marginLeft: "auto"
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120
  },
  selectEmpty: {
    marginTop: theme.spacing(2)
  },
  listScroll: {
    maxHeight: "370px",
    overflow: "scroll"
  }
}));
