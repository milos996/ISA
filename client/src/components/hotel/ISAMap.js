import React, { useState, useEffect } from "react";
import { Map, TileLayer, Marker, Popup } from "react-leaflet";
import Container from "@material-ui/core/Container";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";

const ISAMap = ({ address }) => {
  const mapRef = React.createRef();

  const classes = useStyles();

  const [location, setLocation] = useState({
    latlng: {
      lat: 45.275,
      lng: 19.841
    }
  });

  useEffect(() => {
    if (mapRef !== null) {
      mapRef.current.leafletElement.locate();
    }
  }, [mapRef]);

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
        />
        <TextField label="City" className={classes.textField} margin="normal" />
        <TextField
          label="Street"
          className={classes.textField}
          margin="normal"
        />
      </Container>
      <Map
        style={{ height: 500 }}
        center={location.latlng}
        length={4}
        onClick={({ latlng }) => {
          setLocation({ latlng: latlng });
        }}
        onLocationfound={({ latlng }) => {
          console.log("call api");
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
    flexDirection: "column"
  },
  inputs: {
    display: "flex",
    flexDirection: "row"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "100%"
  },
  inputContainer: {
    padding: "0px 0px 0px 0px"
  },
  modalContainer: {
    width: "60%",
    height: "80%",
    display: "flex",
    flexDirection: "column",
    background: "#cce8ff",
    padding: "0px 0px 0px 0px",
    border: "0px none",
    justifyContent: "flex-end"
  }
}));

export default ISAMap;
