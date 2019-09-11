import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Map, TileLayer, Marker, Popup } from "react-leaflet";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { saveRentACarDetails } from "./../../store/rent-a-car/actions";
import { selectRentACarDetails } from "./../../store/rent-a-car/selectors";

export default function EditRentACar({ closeModal }) {
  const mapRef = React.createRef();
  const classes = useStyles();
  const dispatch = useDispatch();
  const selectedRentACar = useSelector(selectRentACarDetails);
  const [name, setName] = useState(selectedRentACar.name);
  const [description, setDescription] = useState(selectedRentACar.description);

  const [location, setLocation] = useState({
    latlng: {
      lat: selectedRentACar.address.latitude,
      lng: selectedRentACar.address.longitude
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
    const id = selectedRentACar.id;
    dispatch(saveRentACarDetails({ id, name, description, location }));
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
        defaultValue={selectedRentACar.name}
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => {
          setName(currentTarget.value);
        }}
      />
      <TextField
        label="Description"
        className={classes.textField}
        defaultValue={selectedRentACar.description}
        margin="normal"
        onChange={({ currentTarget }) => {
          setDescription(currentTarget.value);
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
