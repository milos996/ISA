import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import Container from "@material-ui/core/Container";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";
import { saveNewPassword } from "../../store/user/actions";

export default function Password({ closeModal, userId }){

    const dispatch = useDispatch();
    const classes = useStyles();
    const [newPassword, setNewPassword] = useState();
    const [confirmedPassword, setConfirmedPassword] = useState();

    return (
        <Container classes={{
            root: classes.root
          }}>
            <TextField
                label = "New password"
                className={classes.textField}
                margin="normal"
                onChange={({ currentTarget }) => {
                    setNewPassword(currentTarget.value);
                }
             } 
            />
            <TextField
                label = "Confirm password"
                className={classes.textField}
                margin="normal"
                onChange={({ currentTarget }) => {
                    setConfirmedPassword(currentTarget.value);
                }
             } 
            />

    <Button
        variant="contained"
        color="primary"
        className={classes.button}
        onClick={() => {
          if(confirmedPassword === newPassword){
              dispatch(
                  saveNewPassword({
                    userId,   
                    password: newPassword,
                    callback: () => {
                        closeModal();
                    }
                })
              );
          }else{
              alert("They are not equal");
            }          
        }}
      >
        Save password
      </Button>
        </Container>
    )
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