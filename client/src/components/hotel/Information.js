import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import Button from "@material-ui/core/Button";
import Container from "@material-ui/core/Container";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Modal from "@material-ui/core/Modal";
import img from "../../assets/building.png";
import {
  fetchHotelDetails,
  putHotelDetails,
  putHotelLocationInformation,
  saveHotelDetails
} from "../../store/hotel/actions";
import { MODAL_CONTENT } from "../../constants/hotel";
import { selectHotelDetails } from "../../store/hotel/selectors";
import Report from "./Report";
import Services from "./Services";
import ISAMap from "./ISAMap";

export default function HotelInformation({ hotelId }) {
  const dispatch = useDispatch();
  const classes = useStyles();
  const hotelDetails = useSelector(selectHotelDetails);

  const [modalContent, setModalContent] = useState({
    isVisible: false,
    value: ""
  });

  function setStreet(street) {
    dispatch(putHotelLocationInformation({ street }));
  }
  function handleSaveButton() {
    dispatch(saveHotelDetails(hotelDetails));
  }

  useEffect(() => {
    dispatch(fetchHotelDetails({ hotelId }));
  }, [hotelId]);

  if (!hotelDetails.id) {
    return <div>Loading....</div>;
  }

  return (
    <div id="mainWraper" className="main-wrapper">
      <Modal open={modalContent.isVisible}>
        <div className="modal-container">
          {modalContent.value === MODAL_CONTENT.SERVICES && (
            <Services
              closeModal={() =>
                setModalContent({
                  isVisible: false
                })
              }
              hotelId={hotelDetails.id}
            />
          )}

          {modalContent.value === MODAL_CONTENT.REPORT && <Report />}

          <Button
            onClick={e =>
              setModalContent({
                isVisible: false
              })
            }
          >
            Close
          </Button>
        </div>
      </Modal>
      <img className="bckg" src={img} alt="Building" />
      <Container className="vertical-items left-align mr-t-120-l-80">
        <Container>
          <Button
            onClick={() =>
              setModalContent({
                isVisible: true,
                value: MODAL_CONTENT.REPORT
              })
            }
          >
            Report
          </Button>
          <Button
            onClick={() =>
              setModalContent({
                isVisible: true,
                value: MODAL_CONTENT.SERVICES
              })
            }
          >
            Services
          </Button>
        </Container>
        <Container
          classes={{
            root: classes.inputs
          }}
        >
          <Container
            classes={{
              root: classes.root
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
              defaultValue={hotelDetails.name}
              onChange={({ currentTarget }) => {
                dispatch(putHotelDetails({ name: currentTarget.value }));
              }}
            />
            <TextField
              type="number"
              label="Description"
              className={classes.textField}
              margin="normal"
              multiline
              rowsMax="8"
              defaultValue={hotelDetails.description}
              onChange={({ currentTarget }) => {
                dispatch(putHotelDetails({ description: currentTarget.value }));
              }}
            />
          </Container>
          <ISAMap address={hotelDetails.address} setStreet={setStreet} />
        </Container>
      </Container>
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column"
  },
  inputs: {
    display: "flex",
    flexDirection: "column"
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
  },
  button: {
    margin: theme.spacing(1),
    width: "30%",
    marginLeft: "auto"
  }
}));
