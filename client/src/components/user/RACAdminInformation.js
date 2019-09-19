import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import Avatar from "@material-ui/core/Avatar";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import AccountBoxRoundedIcon from "@material-ui/icons/AccountBoxRounded";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import Typography from "@material-ui/core/Typography";
import Modal from "@material-ui/core/Modal";
import Button from "@material-ui/core/Button";

import { userDataSelector } from "../../store/user/selectors";
import RACAdminUpdate from "./RACAdminProfileUpdate";
import ChangePassword from "../../pages/PasswordChangePage";

export default function RACAdminInformation({ closeModal }) {
  const classes = useStyles();
  const [modalStyle] = React.useState(getModalStyle);
  const RACAdminDetails = useSelector(userDataSelector);
  const [updateModalVisibility, setUpdateModalVisibility] = useState(false);
  const [
    changePasswordModalVisibility,
    setChangePasswordModalVisibility
  ] = useState(false);

  function handleUpdate() {
    setUpdateModalVisibility(true);
  }

  function handleChangePassword() {
    setChangePasswordModalVisibility(true);
  }

  function closeModal() {
    setUpdateModalVisibility(false);
    setChangePasswordModalVisibility(false);
  }

  return (
    <Container component="main" maxWidth="xs">
      <Modal open={updateModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <RACAdminUpdate />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Modal open={changePasswordModalVisibility}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <ChangePassword />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <AccountBoxRoundedIcon />
        </Avatar>
        <Grid container spacing={4}>
          <Grid item xs={6}>
            <Button
              fullWidth
              variant="contained"
              color="primary"
              variant="outlined"
              className={classes.submit}
              onClick={handleUpdate}
            >
              Update <br />
              profile
            </Button>
          </Grid>
          <Grid item xs={6}>
            <Button
              fullWidth
              variant="contained"
              color="primary"
              variant="outlined"
              className={classes.submit}
              onClick={handleChangePassword}
            >
              Change password
            </Button>
          </Grid>
        </Grid>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                label="First name"
                className={classes.textField}
                margin="normal"
                variant="filled"
                defaultValue={RACAdminDetails.firstname}
                inputProps={{
                  readOnly: true
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Last name"
                className={classes.textField}
                margin="normal"
                variant="filled"
                defaultValue={RACAdminDetails.lastname}
                inputProps={{
                  readOnly: true
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Email"
                className={classes.textField}
                margin="normal"
                variant="filled"
                defaultValue={RACAdminDetails.email}
                inputProps={{
                  readOnly: true
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Phone number"
                className={classes.textField}
                margin="normal"
                variant="filled"
                defaultValue={RACAdminDetails.phone}
                inputProps={{
                  readOnly: true
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="City"
                className={classes.textField}
                margin="normal"
                variant="filled"
                defaultValue={RACAdminDetails.city}
                inputProps={{
                  readOnly: true
                }}
              />
            </Grid>
          </Grid>
        </form>
      </div>
    </Container>
  );
}
function getModalStyle() {
  const top = 50.5;
  const left = 50.5;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`
  };
}

const useStyles = makeStyles(theme => ({
  "@global": {
    body: {
      backgroundColor: theme.palette.common.white
    }
  },
  paper: {
    position: "absolute",
    width: 400,
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #5bc0de",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  root: {
    display: "flex",
    flexDirection: "column"
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(3)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  }
}));
