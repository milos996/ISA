import React, { useState, useEffect } from "react";
import { TextField } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Container from "@material-ui/core/Container";
import { sendFriendshipRequest, searchUsers } from "../../store/user/actions";
import { foundFriendsSelector } from "../../store/user/selectors";
import Button from "@material-ui/core/Button";
import { REQUEST_TYPE } from "../../constants/user";

export default function Search({ match, senderUserId, requestType }) {
  const dispatch = useDispatch();
  const classes = useStyles();
  const foundUsers = useSelector(foundFriendsSelector);

  const [search, setSearch] = useState({
    name: ""
  });
  function handleSearchButton() {
    dispatch(searchUsers(search));
  }

  function handleChange(value) {
    setSearch({ name: value });
  }
  function handleSendFriendshipButton(invitedUserId) {
    dispatch(
      sendFriendshipRequest({
        invitedUserId: invitedUserId
      })
    );
  }
  function handleSendInvitationButton() {}

  return (
    <Container
      classes={{
        root: classes.root
      }}
    >
      {match != undefined &&
        match.params.requestType === REQUEST_TYPE.GROUP_TRIP && (
          <h2>Invite friends</h2>
        )}
      <TextField
        label="Search by name"
        className={classes.textField}
        margin="normal"
        onChange={({ currentTarget }) => handleChange(currentTarget.value)}
      />

      <div>
        <table>
          <tbody>
            {foundUsers.map(user => (
              <tr key={user.id}>
                <td>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>
                  {requestType != undefined &&
                    requestType === REQUEST_TYPE.FRIENDSHIP && (
                      <Button
                        onClick={() => handleSendFriendshipButton(user.id)}
                      >
                        Send friendship request
                      </Button>
                    )}

                  {match != undefined &&
                    match.params.requestType === REQUEST_TYPE.GROUP_TRIP && (
                      <Button onClick={handleSendInvitationButton}>
                        Send invitation for group trip
                      </Button>
                    )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <Button
          variant="contained"
          color="primary"
          className={classes.button}
          onClick={handleSearchButton}
        >
          Search
        </Button>
      </div>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column",
    paddingBottom: "30%"
  },
  inputs: {
    display: "flex",
    flexDirection: "column"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "100%"
  },
  inputContainer: {
    padding: "0px 0px 0px 0px"
  },
  button: {
    margin: theme.spacing(1),
    width: "30%",
    marginLeft: "auto"
  }
}));
