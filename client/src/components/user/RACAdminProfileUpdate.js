import React, { useState } from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Link from "@material-ui/core/Link";
import Grid from "@material-ui/core/Grid";
import AccountBoxRoundedIcon from "@material-ui/icons/AccountBoxRounded";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import { useDispatch } from "react-redux";
//import { racAdminUpdateProfile } from "../../store/user/actions";
import { history } from "../../index";

const RegistrationComponent = () => {
  const classes = useStyles();
  const dispatch = useDispatch();

  const [email, setEmail] = useState();
  const [firstName, setFirstName] = useState();
  const [lastName, setLastName] = useState();
  const [phoneNumber, setPhoneNumber] = useState();
  const [city, setCity] = useState();

  const handleUpdate = () => {
    // dispatch(
    //   racAdminUpdateProfile({
    //     firstName,
    //     lastName,
    //     email,
    //     password,
    //     city,
    //     state,
    //     callback: () => {
    //       history.push("/");
    //     }
    //   })
    // );
  };

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <AccountBoxRoundedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Update
        </Typography>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                autoComplete="fname"
                name="firstName"
                variant="outlined"
                fullWidth
                id="firstName"
                label="First Name"
                autoFocus
                onChange={({ currentTarget }) => {
                  setFirstName(currentTarget.value);
                }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                fullWidth
                id="lastName"
                label="Last Name"
                name="lastName"
                autoComplete="lname"
                onChange={({ currentTarget }) => {
                  setLastName(currentTarget.value);
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                onChange={({ currentTarget }) => {
                  setEmail(currentTarget.value);
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                fullWidth
                name="phoneNumber"
                label="Phone number"
                type="phoneNumber"
                id="phoneNumber"
                autoComplete="phoneNumber"
                onChange={({ currentTarget }) => {
                  setPhoneNumber(currentTarget.value);
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                autoComplete="city"
                name="city"
                variant="outlined"
                fullWidth
                id="city"
                label="City"
                onChange={({ currentTarget }) => {
                  setCity(currentTarget.value);
                }}
              />
            </Grid>
          </Grid>
          <Button
            fullWidth
            color="primary"
            variant="contained"
            className={classes.submit}
            onClick={handleUpdate}
          >
            Update
          </Button>
        </form>
      </div>
    </Container>
  );
};

const useStyles = makeStyles(theme => ({
  "@global": {
    body: {
      backgroundColor: theme.palette.common.white
    }
  },
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
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

export default RegistrationComponent;
