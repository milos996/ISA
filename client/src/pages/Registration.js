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
import { registerUser } from "../store/user/actions";
import { history } from "../index";

const RegistrationComponent = () => {
  const classes = useStyles();
  const dispatch = useDispatch();

  const [email, setEmail] = useState();
  const [firstName, setFirstName] = useState();
  const [lastName, setLastName] = useState();
  const [password, setPassword] = useState();
  const [password2nd, setPassword2nd] = useState();
  const [phoneNumber, setPhoneNumber] = useState();
  const [city, setCity] = useState();
  const [state, setState] = useState();

  const handleRegistration = () => {
    dispatch(
      registerUser({
        firstName,
        lastName,
        email,
        password,
        password2nd,
        phoneNumber,
        city,
        state,
        callback: () => {
          history.push("/login");
        }
      })
    );
  };

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <AccountBoxRoundedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                autoComplete="fname"
                name="firstName"
                variant="outlined"
                required
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
                required
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
                required
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
            <Grid item xs={12} sm={6}>
              <TextField
                autoComplete="password"
                name="password"
                variant="outlined"
                required
                fullWidth
                id="password"
                label="Password"
                type="password"
                onChange={({ currentTarget }) => {
                  setPassword(currentTarget.value);
                }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="password2nd"
                label="Repeat password"
                name="password2nd"
                autoComplete="password2nd"
                type="password"
                onChange={({ currentTarget }) => {
                  setPassword2nd(currentTarget.value);
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
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
            <Grid item xs={12} sm={6}>
              <TextField
                autoComplete="city"
                name="city"
                variant="outlined"
                required
                fullWidth
                id="city"
                label="City"
                onChange={({ currentTarget }) => {
                  setCity(currentTarget.value);
                }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="state"
                label="State"
                name="state"
                autoComplete="state"
                onChange={({ currentTarget }) => {
                  setState(currentTarget.value);
                }}
              />
            </Grid>
          </Grid>
          <Button
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={handleRegistration}
          >
            Sign Up
          </Button>
          <Grid container justify="flex-end">
            <Grid item>
              <Link href="/login" variant="body2">
                Already have an account? Sign in
              </Link>
            </Grid>
          </Grid>
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
