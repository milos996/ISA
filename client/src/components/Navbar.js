import React from "react";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import teal from "@material-ui/core/colors/teal";
import { userTokenSelector } from "../store/user/selectors";
import { logoutUser } from "../store/user/actions";
import { history } from "../index";
const primary = teal[400];

export default function Navbar() {
  const classes = useStyles();
  const userToken = useSelector(userTokenSelector);
  const dispatch = useDispatch();
  const handleLogout = () => {
    dispatch(
      logoutUser({
        callback: () => {
          history.push("/");
        }
      })
    );
  };

  return (
    <div className={classes.root}>
      <AppBar
        position="static"
        color="primary"
        classes={{
          colorPrimary: classes.primaryColor
        }}
      >
        <Toolbar>
          <Typography variant="h6" className={classes.title}>
            <Link className="button" to="">
              UMS Ticket, reservations management
            </Link>
          </Typography>
          {userToken ? (
            <Button color="inherit" onClick={handleLogout}>
              Logout
            </Button>
          ) : (
            <div>
              <Link className="button" to="/register">
                <Button color="inherit">Register</Button>
              </Link>
              <Link className="button" to="/login">
                <Button color="inherit">Login</Button>
              </Link>
            </div>
          )}
        </Toolbar>
      </AppBar>
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1
  },
  menuButton: {
    marginRight: theme.spacing(2)
  },
  title: {
    flexGrow: 1
  },
  primaryColor: {
    background: primary
  }
}));
