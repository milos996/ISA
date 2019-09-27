import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  selectUserFriends,
  userDataSelector
} from "../../store/user/selectors";
import { fetchFriendsData, removeFriend } from "../../store/user/actions";
import Button from "@material-ui/core/Button";
import Container from "@material-ui/core/Container";
import makeStyles from "@material-ui/core/styles/makeStyles";

export default function Friends({ userId }) {
  const userFriends = useSelector(selectUserFriends);
  const user = useSelector(userDataSelector);
  const dispatch = useDispatch();
  const classes = useStyles();

  const [columns, setColumns] = useState([
    { title: "First name", field: "firstname" },
    { title: "Last name", field: "lastname" }
  ]);

  function handleRemoveBtn(friendsId) {
    dispatch(removeFriend({ friendsId }));
  }

  useEffect(() => {
    dispatch(fetchFriendsData());
  });

  return (
    <Container
      classes={{
        root: classes.modalContainer
      }}
    >
      <div>
        {userFriends.length != 0 && (
          <table>
            <tbody>
              <tr>
                <th>{columns[0].title}</th>
                <th>{columns[1].title}</th>
              </tr>
              {userFriends.map(friend => (
                <tr key={friend.id}>
                  <td>{friend.firstName}</td>
                  <td>{friend.lastName}</td>
                  <td>
                    <Button onClick={() => handleRemoveBtn(friend.id)}>
                      Remove friend
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </Container>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex",
    flexDirection: "column",
    paddingBottom: "20%"
  },
  modalContainer: {
    width: "80%",
    height: "50%",
    display: "flex",
    flexDirection: "column",
    background: "#cce8ff",
    padding: "20px 0px 0px 0px",
    border: "0px none",
    justifyContent: "flex-end",
    padding: "0% 0% 0% 0%",
    margin: "0% 10% 40% 10%"
  }
}));
