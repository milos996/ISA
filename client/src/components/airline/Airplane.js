import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch } from "react-redux";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import Modal from "@material-ui/core/Modal";
import SeatConfiguration from "./SeatConfiguration";

export default function Airplane({ airplane }) {

    const classes = useStyles();
    const [isSeatConfigModalModalVisible, setSeatConfigModalVisibility] = useState(false);

    function closeModal() {
        setSeatConfigModalVisibility(false);
      }

    return (
        <Card className={classes.card}>
            <Modal open={isSeatConfigModalModalVisible}>
                <div className="modal-container-sm">
                    <SeatConfiguration airplane={ airplane} closeModal={closeModal} />
                    <Button onClick={closeModal}>Close</Button>
                </div>
            </Modal>
            <CardContent>
                <Typography variant="h5" component="h2">
                    Airplane mark: {airplane.mark}
                </Typography>
                <Typography className={classes.pos} color="textSecondary">
                    Airplane capacity: {airplane.numberOfRows * airplane.numberOfColumnsPerSegment * airplane.numberOfSegments} people
                </Typography>

            </CardContent>
            <CardActions>
                <Button size="small" onClick={() => setSeatConfigModalVisibility(true)}>
                    Seat configuration
                </Button>
            </CardActions>
        </Card>
    );
}

const useStyles = makeStyles(theme => ({
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
        marginLeft: 5,
        marginBottom: 12
    }
}));