import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch } from "react-redux";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import DeleteIcon from "@material-ui/icons/Delete";
import IconButton from "@material-ui/core/IconButton";
import Modal from "@material-ui/core/Modal";
import EditRoom from "./EditRoom";
import IsaDialog from "../UI/IsaDialog";
import { deleteRoom } from "../../store/hotel/actions";

export default function HotelRoom({ room }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [isEditModalVisible, setEditModalVisibility] = useState(false);
  const [isDialogForDeleteVisible, setDialogVisibility] = useState(false);

  function handleDeleteRoom() {
    dispatch(deleteRoom({ roomId: room.id }));
    setDialogVisibility(false);
  }

  function closeModal() {
    setEditModalVisibility(false);
  }

  return (
    <Card className={classes.card}>
      <Modal open={isEditModalVisible}>
        <div className="modal-container-sm">
          <EditRoom room={room} closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <CardContent>
        <Typography variant="h5" component="h2">
          Floor {room.floor}
        </Typography>
        <Typography className={classes.pos} color="textSecondary">
          Number {room.number}
        </Typography>
        <Typography variant="body2" component="p">
          Number of people in the room is <strong>{room.numberOfPeople}</strong>
          . Price for summer is <strong>{room.priceSummer}$</strong>. <br />
          For more information about the room click on <strong>SEE MORE</strong>
          , also if you want to edit room <br />
          click on <strong>SEE MORE</strong>.
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small" onClick={() => setEditModalVisibility(true)}>
          See more
        </Button>
        <IconButton
          aria-label="delete"
          onClick={() => {
            setDialogVisibility(true);
          }}
        >
          <DeleteIcon fontSize="small" />
        </IconButton>
      </CardActions>
      <IsaDialog
        isVisible={isDialogForDeleteVisible}
        title={`Are you sure you want to delete room ${room.floor} - ${room.number} ?`}
        handleClose={() => {
          setDialogVisibility(false);
        }}
        callYesAction={handleDeleteRoom}
      />
    </Card>
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
