import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch } from "react-redux";
import Typography from "@material-ui/core/Typography";
import DeleteIcon from "@material-ui/icons/Delete";
import IconButton from "@material-ui/core/IconButton";
import { deleteRentACarOffice } from "../../store/rent-a-car/actions";
import { Grid } from "@material-ui/core";
import IsaDialog from "../UI/IsaDialog";

export default function RentACarOffice({ office }) {
  const role = window.localStorage.getItem("role");
  const classes = useStyles();
  const dispatch = useDispatch();
  const [isDialogForDeleteVisible, setDialogVisibility] = useState(false);

  function handleDeleteOffice() {
    dispatch(deleteRentACarOffice(office.id));
    setDialogVisibility(false);
  }

  return (
    <Grid container spacing={1}>
      <Grid item sm={2}>
        <Typography className={classes.pos} color="textSecondary">
          {office.location}
        </Typography>
      </Grid>
      {role === "RENT_A_CAR_ADMIN" ? (
        <Grid item sm={2}>
          <IconButton
            aria-label="delete"
            onClick={() => {
              setDialogVisibility(true);
            }}
          >
            <DeleteIcon fontSize="small" />
          </IconButton>
        </Grid>
      ) : null}
      <IsaDialog
        isVisible={isDialogForDeleteVisible}
        title={`Are you sure you want to remove office in '${office.location}' ?`}
        handleClose={() => {
          setDialogVisibility(false);
        }}
        callYesAction={handleDeleteOffice}
      />
    </Grid>
  );
}

const useStyles = makeStyles(theme => ({
  card: {
    width: 199,
    height: 300,
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
    marginLeft: 5,
    marginBottom: 12
  }
}));
