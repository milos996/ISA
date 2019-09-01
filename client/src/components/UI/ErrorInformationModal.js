import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import { selectError } from "./../../store/common/selectors";
import { putError } from "../../store/common/actions";

export default function ErrorInformationModal() {
  const classes = useStyles();
  const [open, setOpen] = useState(false);
  const dispatch = useDispatch();
  const error = useSelector(selectError);

  function handleOpen() {
    setOpen(true);
  }

  function handleClose() {
    dispatch(putError(null));
    setOpen(false);
  }

  useEffect(() => {
    handleOpen();

    setTimeout(() => {
      handleClose();
    }, 3000);
  }, [error]);

  return (
    <Modal
      aria-labelledby="transition-modal-title"
      aria-describedby="transition-modal-description"
      className={classes.modal}
      open={!!error}
      onClose={handleClose}
      closeAfterTransition
      BackdropComponent={Backdrop}
      BackdropProps={{
        timeout: 500
      }}
    >
      <Fade in={open}>
        <div className={classes.paper}>
          <h2 id="transition-modal-title">Error</h2>
          <p id="transition-modal-description">{error}</p>
        </div>
      </Fade>
    </Modal>
  );
}

const useStyles = makeStyles(theme => ({
  modal: {
    display: "flex",
    alignItems: "center",
    justifyContent: "center"
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #000",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  }
}));
