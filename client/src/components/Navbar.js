import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import Modal from "@material-ui/core/Modal";
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
import RegistrationPage from "../pages/Registration";
import LoginPage from "../pages/Login";
import Tooltip from "@material-ui/core/Tooltip";
import { selectAirlineAdmin } from "../store/airline/selectors";
import { fetchAirlineAdmin } from "../store/airline/actions";

const primary = teal[400];

export default function Navbar() {
  const classes = useStyles();
  const [modalStyle] = useState(getModalStyle);
  const userToken = useSelector(userTokenSelector);
  const userData = useSelector(userDataSelector);
  const dispatch = useDispatch();
  const user = useSelector(userDataSelector);

  const [
    registrationModalVisibility,
    setRegistrationModalVisibility
  ] = useState(false);

  const [loginModalVisibility, setLoginModalVisibility] = useState(false);

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

  function closeModal() {
    setRegistrationModalVisibility(false);
    setLoginModalVisibility(false);
  }

  useEffect(() => {
    dispatch(fetchAirlineAdmin());
  }, [user.id]);
  return (
    <div className={classes.root}>
      <Modal open={registrationModalVisibility} className={classes.modal}>
        <div style={modalStyle} className={classes.paper}>
          <RegistrationPage closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Modal open={loginModalVisibility} className={classes.modal}>
        <div style={modalStyle} className={classes.loginPaper}>
          <LoginPage closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
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
              <img src={require("../assets/umslogo.png")} height="48"></img>
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
              <Button
                color="inherit"
                onClick={() => setRegistrationModalVisibility(true)}
              >
                <PersonAddSharpIcon></PersonAddSharpIcon>
              </Button>
              <Button
                color="inherit"
                onClick={() => setLoginModalVisibility(true)}
              >
                <InputRoundedIcon></InputRoundedIcon>
              </Button>
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
  },
  paper: {
    position: "absolute",
    width: "40%",
    height: "90%",
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #5bc0de",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  loginPaper: {
    position: "absolute",
    width: "40%",
    height: "70%",
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #5bc0de",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  modal: {
    display: "flex",
    alignItems: "center",
    justifyContent: "center"
  }
}));

function getModalStyle() {
  const top = 50;
  const left = 50;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`
  };
}
const LightTooltip = withStyles(theme => ({
  tooltip: {
    backgroundColor: theme.palette.common.white,
    color: "rgba(0, 0, 0, 0.87)",
    boxShadow: theme.shadows[1],
    fontSize: 11
  }
}))(Tooltip);
