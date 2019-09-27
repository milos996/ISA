import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles, withStyles } from "@material-ui/core/styles";
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
import AirlineIcon from "@material-ui/icons/AirplanemodeActive";
import PersonAddSharpIcon from "@material-ui/icons/PersonAddSharp";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import InputRoundedIcon from "@material-ui/icons/InputRounded";
import Tooltip from "@material-ui/core/Tooltip";
import { selectAirlineAdmin } from "../store/airline/selectors";
import { fetchAirlineAdmin } from "../store/airline/actions";
const primary = teal[400];

export default function Navbar() {
  const classes = useStyles();
  const userToken = useSelector(userTokenSelector);
  const userData = useSelector(userDataSelector);
  const dispatch = useDispatch();
  const user = useSelector(userDataSelector);
  const airlineAdmin = useSelector(selectAirlineAdmin);
  const handleLogout = () => {
    dispatch(
      logoutUser({
        callback: () => {
          history.push("/");
        }
      })
    );
  };
  useEffect(() => {
    dispatch(fetchAirlineAdmin());
  }, [user.id]);
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

          {userToken && user.role === "AIRLINE_ADMIN" && (
            <LightTooltip title="Airline profile">
              <Link
                className="button"
                to={`/airline/${airlineAdmin.airline.id}`}
              >
                <Button color="inherit">
                  <AirlineIcon></AirlineIcon>
                </Button>
              </Link>
            </LightTooltip>
          )}

          {userToken ? (
            <div>
              <LightTooltip title="Friends">
                <Link className="button" to={`/user/${user.id}/friends`}>
                  <Button color="inherit">
                    <GroupIcon></GroupIcon>
                  </Button>
                </Link>
              </LightTooltip>
              <LightTooltip title="Invites">
                <Link className="button" to={`/user/${user.id}/invites`}>
                  <Button color="inherit">
                    <ContactMailIcon></ContactMailIcon>
                  </Button>
                </Link>
              </LightTooltip>

              <LightTooltip title="Profile">
                <Link className="button" to={`/user/${user.id}`}>
                  <Button color="inherit">
                    <AccountBoxIcon></AccountBoxIcon>
                  </Button>
                </Link>
              </LightTooltip>

              <LightTooltip title="Logout">
                <Button color="inherit" onClick={handleLogout}>
                  <ExitToAppIcon></ExitToAppIcon>
                </Button>
              </LightTooltip>
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
const LightTooltip = withStyles(theme => ({
  tooltip: {
    backgroundColor: theme.palette.common.white,
    color: "rgba(0, 0, 0, 0.87)",
    boxShadow: theme.shadows[1],
    fontSize: 11
  }
}))(Tooltip);
