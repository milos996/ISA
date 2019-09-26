import React, { useState, useEffect } from "react";
import {TextField} from "@material-ui/core";
import {useSelector, useDispatch} from "react-redux";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Container from "@material-ui/core/Container";
import  { fetchUsersByName, sendFriendshipRequest } from "../../store/user/actions";
import { foundFriendsSelector } from "../../store/user/selectors";
import Button from "@material-ui/core/Button";

export default function Search({ senderUserId }){

    const dispatch = useDispatch();
    const classes = useStyles();
    const foundUsers = useSelector(foundFriendsSelector);
    
   function renderTableData() {

       return foundUsers.map((user) => {
           return(
           <tr key={user.id}>
            <td>{user.firstname}</td>
            <td>{user.lastname}</td>
            <td>
            <Button
                onClick = { () => dispatch(sendFriendshipRequest( { senderUserId: senderUserId, invitedUserId: user.id } )) }
            >
                Send friendship request
            </Button>
            </td>
          </tr>)    
          }) 
    }

    return (
        <Container
        classes={{
          root: classes.root
        }}
      >
       <h2>Search</h2>
       <TextField
           label = "Search by name"
           className={classes.textField}
           margin="normal"
           onChange={({ currentTarget }) => {
            dispatch(fetchUsersByName({ firstname: currentTarget.value }));
                }
            }
       />

       <div>
            <table>
               <tbody>
                    { renderTableData() }
               </tbody>
            </table>
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
    modalContainer: {
        width: "60%",
        height: "80%",
        display: "flex",
        flexDirection: "column",
        background: "#cce8ff",
        padding: "0px 0px 0px 0px",
        border: "0px none",
        justifyContent: "flex-end"
    },
    button: {
        margin: theme.spacing(1),
        width: "30%",
        marginLeft: "auto"
    }
}));