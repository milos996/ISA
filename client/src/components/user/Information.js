import React, { useState, useEffect } from "react";
import { TextField } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import Modal from "@material-ui/core/Modal";
import Friends from "./Friends";
import { userDataSelector } from "../../store/user/selectors";
import {
  saveUserData,
  putUserData,
  fetchUserData
} from "../../store/user/actions";
import { MODAL_CONTENT } from "../../constants/user";
import Password from "./Password";
import Search from "./Search";
import { Link } from "react-router-dom";
import { REQUEST_TYPE } from "../../constants/user";

export default function UserInformation({ userId }) {
  const dispatch = useDispatch();
  const classes = useStyles();
  const userDetails = useSelector(userDataSelector);

  const [modalContent, setModalContent] = useState({
    isVisible: false,
    value: ""
  });

  function handleSaveButton() {
    dispatch(saveUserData(userDetails));
  }

  return (
    <div id="mainWrapper" className="main-wrapper">
      <Modal open={modalContent.isVisible}>
        <div className="modal-container">
          {modalContent.value === MODAL_CONTENT.FRIENDS && (
            <Friends userId={userId} />
          )}
          {modalContent.value === MODAL_CONTENT.SEARCH_USERS && (
            <Search
              senderUserId={userId}
              requestType={REQUEST_TYPE.FRIENDSHIP}
            />
          )}

          {modalContent.value === MODAL_CONTENT.CHANGE_PASSWORD && (
            <Password
              closeModal={() =>
                setModalContent({
                  isVisible: false
                })
              }
              userId={userDetails.id}
            />
          )}

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
      <Container
        className="vertical-items left-align mr-t-120-l-80"
        classes={{
          root: classes.root
        }}
      >
        <h1>User profile</h1>
        <Container>
          <Button
            onClick={() =>
              setModalContent({
                isVisible: true,
                value: "search_users"
              })
            }
          >
            Find users
          </Button>

          <Button
            onClick={() =>
              setModalContent({
                isVisible: true,
                value: "friends"
              })
            }
          >
            List of friends
          </Button>
          <Button
            onClick={() =>
              setModalContent({
                isVisible: true,
                value: "change_password"
              })
            }
          >
            Change password
          </Button>
        </Container>
        <Container>
          <TextField
            label="Firstname"
            className={classes.textField}
            margin="normal"
            defaultValue={userDetails.firstname}
            onChange={({ currentTarget }) => {
              dispatch(putUserData({ firstname: currentTarget.value }));
            }}
          />
          <TextField
            label="Lastname"
            className={classes.textField}
            margin="normal"
            defaultValue={userDetails.lastname}
            onChange={({ currentTarget }) => {
              dispatch(putUserData({ lastname: currentTarget.value }));
            }}
          />
          <TextField
            label="Email"
            className={classes.textField}
            margin="normal"
            defaultValue={userDetails.email}
            onChange={({ currentTarget }) => {
              dispatch(putUserData({ email: currentTarget.value }));
            }}
          />
          <TextField
            label="Phone number"
            className={classes.textField}
            margin="normal"
            defaultValue={userDetails.phone}
            onChange={({ currentTarget }) => {
              dispatch(putUserData({ phone: currentTarget.value }));
            }}
          />
          <TextField
            label="City"
            className={classes.textField}
            margin="normal"
            defaultValue={userDetails.city}
            onChange={({ currentTarget }) => {
              dispatch(putUserData({ city: currentTarget.value }));
            }}
          />
          <TextField
            label="State"
            className={classes.textField}
            margin="normal"
            defaultValue={userDetails.state}
            onChange={({ currentTarget }) => {
              dispatch(putUserData({ state: currentTarget.value }));
            }}
          />
          <Button
            variant="contained"
            color="primary"
            className={classes.button}
            onClick={handleSaveButton}
          >
            Save
          </Button>
        </Container>
      </Container>
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column",
    marginTop: "0px"
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
  button: {
    margin: theme.spacing(1),
    width: "30%",
    marginLeft: "auto"
  }
}));
