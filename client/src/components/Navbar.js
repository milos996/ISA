import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import teal from "@material-ui/core/colors/teal";
import { userTokenSelector, userDataSelector } from "../store/user/selectors";
import { logoutUser } from "../store/user/actions";
import { history } from "../index";
import GroupIcon from "@material-ui/icons/Group";
import ContactMailIcon from "@material-ui/icons/ContactMail";
import AccountBoxIcon from "@material-ui/icons/AccountBox";
import PersonAddSharpIcon from "@material-ui/icons/PersonAddSharp";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import InputRoundedIcon from "@material-ui/icons/InputRounded";
const primary = teal[400];

export default function Navbar() {
  const classes = useStyles();
  const userToken = useSelector(userTokenSelector);
  const userData = useSelector(userDataSelector);
  const dispatch = useDispatch();
  const user = useSelector(userDataSelector);
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
            <div>
              <Link className="button" to={`/user/${user.id}/friends`}>
                <Button color="inherit">
                  <GroupIcon></GroupIcon>
                </Button>
              </Link>
              <Link className="button" to={`/user/${user.id}/invites`}>
                <Button color="inherit">
                  <ContactMailIcon></ContactMailIcon>
                </Button>
              </Link>
              <Link className="button" to={`/user/${user.id}`}>
                <Button color="inherit">
                  <AccountBoxIcon></AccountBoxIcon>
                </Button>
              </Link>
              <Button color="inherit" onClick={handleLogout}>
                <ExitToAppIcon></ExitToAppIcon>
              </Button>
            </div>
          ) : (
            <div>
              <Link className="button" to="/register" tooltip="Registration">
                <Button color="inherit">
                  <PersonAddSharpIcon></PersonAddSharpIcon>
                </Button>
              </Link>
              <Link className="button" to="/login">
                <Button color="inherit">
                  <InputRoundedIcon></InputRoundedIcon>
                </Button>
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
