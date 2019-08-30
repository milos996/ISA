import React, { useMemo, useEffect } from "react";
import Container from "@material-ui/core/Container";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import { useSelector, useDispatch } from "react-redux";
import IconButton from "@material-ui/core/IconButton";
import DeleteIcon from "@material-ui/icons/Delete";
import Button from "@material-ui/core/Button";
import MenuItem from "@material-ui/core/MenuItem";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import { selectServices } from "../../store/hotel/selectors";
import {
  putChangeHotelServices,
  putAddNewService,
  saveServices,
  fetchHotelService
} from "../../store/hotel/actions";

export default function Services({ closeModal, hotelId }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const servicesSelector = useMemo(selectServices, []);
  const hotelServices = useSelector(state => servicesSelector(state, true));
  const unSelectedServices = useSelector(state =>
    servicesSelector(state, false)
  );

  useEffect(() => {
    dispatch(
      fetchHotelService({
        hotelId
      })
    );
  }, [hotelId]);

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      <h1>SERVICES</h1>
      <Container className={classes.listScroll}>
        {Object.keys(hotelServices).map(serviceId => (
          <Container
            classes={{
              root: classes.serviceRow
            }}
            key={serviceId}
          >
            <TextField
              label="Service"
              defaultValue={hotelServices[serviceId].name}
              className={classes.textField}
              margin="normal"
              InputProps={{
                readOnly: true
              }}
            />
            <TextField
              error={
                isNaN(hotelServices[serviceId].price) ||
                hotelServices[serviceId].price < 0
              }
              defaultValue={hotelServices[serviceId].price}
              onChange={e =>
                dispatch(
                  putChangeHotelServices({
                    id: serviceId,
                    price: parseInt(e.target.value)
                  })
                )
              }
              label="Price"
              className={classes.textField}
              margin="normal"
            />
            <IconButton
              aria-label="delete"
              className={classes.margin}
              onClick={() =>
                dispatch(
                  putChangeHotelServices({
                    id: serviceId,
                    price: null,
                    shouldDelete: true
                  })
                )
              }
            >
              <DeleteIcon fontSize="small" />
            </IconButton>
          </Container>
        ))}
      </Container>
      <FormControl className={classes.formControl}>
        <InputLabel htmlFor="new-service">New Service</InputLabel>
        <Select
          value={""}
          onChange={select => {
            dispatch(putAddNewService(select.target.value));
          }}
          inputProps={{
            name: "new-service",
            id: "new-service"
          }}
        >
          {Object.keys(unSelectedServices).map(key => (
            <MenuItem key={key} value={key}>
              {unSelectedServices[key].name}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={() => {
          dispatch(
            saveServices({
              services: hotelServices,
              callback: () => {
                closeModal();
              }
            })
          );
        }}
      >
        Save Services
      </Button>
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
    flexDirection: "row",
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
    margin: theme.spacing(1)
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
