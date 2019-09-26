import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import MaterialTable from "material-table";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Button from "@material-ui/core/Button";
import {
  selectUserInvites,
  selectFlightsReservation
} from "../../store/user/selectors";
import { acceptInvite, declineInvite } from "../../store/user/actions";
import AccountBoxIcon from "@material-ui/icons/AccountBox";

export default function Invites({ userId }) {
  const classes = useStyles();
  const [modalStyle] = React.useState(getModalStyle);
  const dispatch = useDispatch();
  const invites = useSelector(selectFlightsReservation);

  function handleAccept(inviteId) {
    dispatch(acceptInvite({ inviteId: inviteId, userId: userId }));
  }

  function handleDecline(inviteId) {
    dispatch(declineInvite({ inviteId: inviteId, userId: userId }));
  }

  const actions = [
    {
      icon: "cancel",
      tooltip: "Decline invite",
      onClick: (event, rowData) => {
        handleDecline(rowData.inviteId);
        alert(
          "Decline request for invite '" + rowData.inviteId + "' has been sent!"
        );
      }
    },
    {
      icon: "check-circle",
      tooltip: "Accept invite",
      onClick: (event, rowData) => {
        handleAccept(rowData.inviteId);
      }
    }
  ];

  const columns = [
    { title: "Invite ID", field: "inviteId" },
    { title: "Sent by", field: "sentBy" },
    { title: "Destination", field: "destination" },
    { title: "From date", field: "pickUpDate" },
    { title: "Till date", field: "dropOffDate" },
    { title: "Price", field: "price" }
  ];

  return (
    <div className={classes.root}>
      <MaterialTable
        title="Invites"
        columns={columns}
        data={invites}
        actions={actions}
      />
    </div>
  );
}

function getModalStyle() {
  const top = 50;
  const left = 50;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`
  };
}

const useStyles = makeStyles(theme => ({
  paper: {
    position: "absolute",
    width: 400,
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #000",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  root: {
    display: "flex",
    flexDirection: "column"
  }
}));
