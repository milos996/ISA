import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import { updateFriendshipRequest } from "../../store/user/actions";

export default function FriendshipRequest({ friendship }) {
  const classes = useStyles();
  const dispatch = useDispatch();

  const [currentFriendship, setCurrentFriendship] = useState({
    id: friendship.id,
    invitationStatus: friendship.invitationStatus,
    invitedUser: {
      city: friendship.invitedUser.city,
      email: friendship.invitedUser.email,
      firstName: friendship.invitedUser.firstName,
      id: friendship.invitedUser.id,
      lastName: friendship.invitedUser.lastName,
      password: friendship.invitedUser.password,
      phoneNumber: friendship.invitedUser.phoneNumber,
      state: friendship.invitedUser.state
    },

    sender: {
      city: friendship.sender.city,
      email: friendship.sender.email,
      firstName: friendship.sender.firstName,
      id: friendship.sender.id,
      lastName: friendship.sender.lastName,
      password: friendship.sender.password,
      phoneNumber: friendship.sender.phoneNumber,
      state: friendship.sender.state
    }
  });

  function handleAcceptBtn() {
    currentFriendship.invitationStatus = "ACCEPTED";
    dispatch(updateFriendshipRequest(currentFriendship));
  }

  function handleRejectBtn() {
    currentFriendship.invitationStatus = "REJECTED";
    dispatch(updateFriendshipRequest(currentFriendship));
  }

  return (
    <Card className={classes.card}>
      <CardContent>
        <Typography
          className={classes.title}
          color="textSecondary"
          gutterBottom
        ></Typography>
        <Typography variant="h5" component="h2">
          {friendship.sender.firstName} {friendship.sender.lastName}
        </Typography>

        <Typography variant="h6" component="h2" color="textSecondary">
          {friendship.sender.email}
        </Typography>
        <Typography className={classes.pos} color="textSecondary"></Typography>
        <Typography variant="body2" component="p"></Typography>
      </CardContent>
      <CardActions>
        <Button size="small" onClick={() => handleAcceptBtn(friendship)}>
          Accept
        </Button>
        <Button size="small" onClick={() => handleRejectBtn()}>
          Reject
        </Button>
      </CardActions>
    </Card>
  );
}

const useStyles = makeStyles({
  card: {
    width: 290,
    height: 170,
    marginBottom: 15,
    marginRight: 10,
    padding: 5,
    paddingBottom: 10
  },
  bullet: {
    display: "inline-block",
    margin: "0 2px",
    transform: "scale(0.8)"
  },
  title: {
    fontSize: 14
  },
  pos: {
    marginBottom: 12
  }
});
