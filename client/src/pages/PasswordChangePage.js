import React, { useState } from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Link from "@material-ui/core/Link";
import Grid from "@material-ui/core/Grid";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import { useDispatch } from "react-redux";
import { changeRentACarAdminPassword } from "../store/user/actions";
import { history } from "../index";

const LoginPage = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [oldPassword, setOldPassword] = useState();
  const [newPassword, setNewPassword] = useState();

  const handleConfirm = () => {
    dispatch(
      changeRentACarAdminPassword({
        oldPassword,
        newPassword,
        callback: () => {
          history.push("/");
          alert("Success");
        }
      })
    );
  };

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Password update
        </Typography>
        <form className={classes.form} noValidate>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="oldPassword"
            label="Old password"
            name="oldPassword"
            type="password"
            autoComplete="current-password"
            autoFocus
            onChange={({ currentTarget }) => {
              setOldPassword(currentTarget.value);
            }}
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="newPassword"
            label="New password"
            id="newPassword"
            type="password"
            autoComplete="current-password"
            onChange={({ currentTarget }) => {
              setNewPassword(currentTarget.value);
            }}
          />
          <Button
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={handleConfirm}
          >
            Confirm
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
    marginTop: theme.spacing(1)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  }
}));

export default LoginPage;
