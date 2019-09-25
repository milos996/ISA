import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import Modal from "@material-ui/core/Modal";
import CardActionArea from "@material-ui/core/CardActionArea";
import TicketsForFastReservation from "../airline/TicketsForFastReservation";
import { fetchAirlineDetails } from "../../store/airline/actions";

export default function Airline({ airline, history }) {
  const classes = useStyles();
  const [isModalVisible, setModalVisibility] = useState(false);
  const dispatch = useDispatch();

  return (
    <Card className={classes.card}>
      <Modal open={isModalVisible}>
        <div className="modal-container">
          {<TicketsForFastReservation airlineId={airline.id} />}

          <Button onClick={e => setModalVisibility(false)}>Close</Button>
        </div>
      </Modal>
      <CardActionArea>
        <CardContent
          onClick={() => {
            dispatch(fetchAirlineDetails({ airlineId: airline.id }));

            history.push({
              pathname: `/airline/${airline.id}`
            });
          }}
        >
          <Typography variant="h5" component="h2">
            {airline.name}
          </Typography>
          <Typography className={classes.pos} color="textSecondary">
            Country:{airline.address.state}
          </Typography>
          <Typography className={classes.pos} color="textSecondary">
            City:{airline.address.city}
          </Typography>
          <Typography className={classes.pos} color="textSecondary">
            Description:{airline.description}
          </Typography>
          <Typography className={classes.pos} color="textSecondary">
            Suitcase price:{airline.checkingInSuitcasePrice}€
          </Typography>
          <Typography className={classes.pos} color="textSecondary">
            Hand luggage price:{airline.handLuggagePrice}€
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" onClick={() => setModalVisibility(true)}>
          Tickets on discount
        </Button>
      </CardActions>
    </Card>
  );
}

const useStyles = makeStyles(theme => ({
  card: {
    width: 220,
    height: 370,
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
