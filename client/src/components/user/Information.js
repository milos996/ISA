import React, { useState, useEffect } from "react";
import {TextField} from "@material-ui/core";
import {useSelector, useDispatch} from "react-redux";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Container from "@material-ui/core/Container";
import Button from "@material-ui/core/Button";
import Modal from "@material-ui/core/Modal";
import Friends from "./Friends";
import {selectUserDetails} from "../../store/user/selectors"
import { saveUserData, putUserData, fetchUserData } from "../../store/user/actions";

export default function UserInformation({ userId }) {

    const dispatch = useDispatch();
    const classes = useStyles();
    const userDetails = useSelector(selectUserDetails);

    const [modalContent, setModalContent] = useState({
        isVisible: false,
        value: ""
    });

    function handleSaveButton(){
        dispatch(saveUserData(userDetails));
    }

    return (
        <div id="mainWrapper" className="main-wrapper">

        <h1>User profile</h1>

            <Modal open={modalContent.isVisible}>
                <div className="modal-container">
                   <Friends userId = { userId }/>

                    <Button
                        onClick={e =>
                            setModalContent({
                                isVisible: false
                            })
                        }
                    >
                        Close
                    </Button>
                </div>
            </Modal>

            <Button
                onClick={() =>
                    setModalContent({
                        isVisible: true,
                        value: "Friends"
                    })
                }
            >
                List of friends
            </Button>

        <Container>
           <TextField
               label = "Name"
               className={classes.textField}
               margin="normal"
               defaultValue={userDetails.firstname}
               onChange={({ currentTarget }) => {
                   dispatch(putUserData({ firstname: currentTarget.value }));
               }
            } 
           />

            <TextField
                label = "Lastname"
                className={classes.textField}
                margin="normal"
                defaultValue={userDetails.lastname}
                onChange={({ currentTarget }) => {
                    dispatch(putUserData({ lastname: currentTarget.value }));
                }
            }
            />

            <TextField
                label = "Email"
                className={classes.textField}
                margin="normal"
                defaultValue={userDetails.email}
                onChange={({ currentTarget }) => {
                    dispatch(putUserData({ email: currentTarget.value }));
                }
            }
            />

            <TextField
                label = "Password"
                className={classes.textField}
                margin="normal"
                defaultValue={userDetails.password}
                onChange={({ currentTarget }) => {
                    dispatch(putUserData({ password: currentTarget.value }));
                }
            }
            />

            <TextField
                label = "Phone number"
                className={classes.textField}
                margin="normal"
                defaultValue={userDetails.phone}
                onChange={({ currentTarget }) => {
                    dispatch(putUserData({ phone: currentTarget.value }));
                }
            }
            />

            <TextField
                label = "City"
                className={classes.textField}
                margin="normal"
                defaultValue={userDetails.city}
                onChange={({ currentTarget }) => {
                    dispatch(putUserData({ city: currentTarget.value }));
                }
            }
            />

            <TextField
                label = "State"
                className={classes.textField}
                margin="normal"
                defaultValue={userDetails.state}
                onChange={({ currentTarget }) => {
                    dispatch(putUserData({ state: currentTarget.value }));
                }
            }
            />

            <Button
                variant="contained"
                color="primary"
                className={classes.button}
                onClick={handleSaveButton}
            >
                Save
            </Button>

        </Container>
        </div>
    );
}

const useStyles = makeStyles(theme => ({
    root: {
        display: "flex",
        flexDirection: "column"
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
