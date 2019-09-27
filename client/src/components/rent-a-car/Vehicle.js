import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useDispatch, useSelector } from 'react-redux';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import DeleteIcon from '@material-ui/icons/Delete';
import UpdateIcon from '@material-ui/icons/Update';
import IconButton from '@material-ui/core/IconButton';
import Modal from '@material-ui/core/Modal';
import Rating from 'react-rating';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import StarIcon from '@material-ui/icons/Star';
import AccessibilityIcon from '@material-ui/icons/Accessibility';
import IsaDialog from '../UI/IsaDialog';
import EditVehicle from './EditVehicle';
import DiscountVehicle from './DiscountVehicle';
import { deleteVehicle } from '../../store/rent-a-car/actions';
import { selectVehicleSearchInformation } from '../../store/rent-a-car/selectors';
import { createVehicleReservation } from './../../store/rent-a-car/actions';
import CardBackground from '../../assets/card_bckg.jpg';

export default function RentACarVehicle({ vehicle, airplaneTicket = null }) {
  const role = window.localStorage.getItem('role');
  const classes = useStyles();
  const dispatch = useDispatch();
  const [modalStyle] = React.useState(getModalStyle);
  const [isEditModalVisible, setEditModalVisibility] = useState(false);
  const [isDialogForDeleteVisible, setDialogVisibility] = useState(false);
  const [isDiscountModalVisible, setDiscountModalVisibility] = useState(false);

  const info = useSelector(selectVehicleSearchInformation);

  useEffect(() => {
    // if (airplaneTicket != undefined) {
    //   info.pickUpDate = location.state.pickUpDate;
    //   info.dropOffDate = location.state.dropOffDate;
    // }
  });

  function handleReserve() {
    if (
      info.pickUpDate === '' ||
      info.dropOffDate === '' ||
      info.pickUpLocation === '' ||
      info.dropOffLocation === ''
    ) {
      alert(
        "You need to search for the vehicle first!\nSelect 'pickup-dropoff location' and 'pickup-dropoff date'"
      );
      return;
    }

    const vehicleId = vehicle.id;

    dispatch(
      createVehicleReservation({
        airplaneTicketId: airplaneTicket,
        vehicleId,
        info
      })
    );
    closeModal();
  }

  function handleDeleteVehicle() {
    dispatch(deleteVehicle(vehicle.id));
    setDialogVisibility(false);
  }

  function closeModal() {
    setEditModalVisibility(false);
    setDiscountModalVisibility(false);
  }

  return (
    <Card className={classes.card}>
      <Modal open={isEditModalVisible}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <EditVehicle vehicle={vehicle} closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <Modal open={isDiscountModalVisible}>
        <div
          className="modal-container-sm"
          style={modalStyle}
          className={classes.paper}
        >
          <DiscountVehicle vehicle={vehicle} closeModal={closeModal} />
          <Button onClick={closeModal}>Close</Button>
        </div>
      </Modal>
      <CardContent>
        <Typography variant="h5" component="h2">
          {vehicle.brand}
        </Typography>
        <Typography color="textSecondary">{vehicle.model}</Typography>
        <Typography variant="body2" component="p">
          <strong>
            {' '}
            {vehicle.type} {vehicle.brand} {vehicle.model}, from{' '}
            {vehicle.yearOfProduction}.
          </strong>
          <div>
            <br /> Price for this vehicle:{' '}
            <strong>{vehicle.pricePerDay}$</strong>.
          </div>
          <br />
          <Rating
            readonly={true}
            className={classes.rating}
            initialRating={vehicle.numberOfSeats}
            stop={vehicle.numberOfSeats}
            fullSymbol={<AccessibilityIcon></AccessibilityIcon>}
            emptySymbol={<AccessibilityIcon></AccessibilityIcon>}
          ></Rating>
          <br />
          <Rating
            readonly={true}
            className={classes.rating}
            initialRating={vehicle.rating}
            stop={10}
            emptySymbol={<StarBorderIcon></StarBorderIcon>}
            fullSymbol={<StarIcon></StarIcon>}
          ></Rating>
        </Typography>
        <br />
        {role === 'RENT_A_CAR_ADMIN' ? (
          <Typography variant="body2" component="p">
            If you want to change this vehicle click on <strong>UPDATE</strong>
          </Typography>
        ) : null}
        {role === 'USER' ? (
          <Typography variant="body2" component="p">
            If you want to reserve this vehicle click on{' '}
            <strong>RESERVE</strong>
          </Typography>
        ) : null}
      </CardContent>
      <CardActions>
        {role === 'RENT_A_CAR_ADMIN' ? (
          <div>
            <IconButton
              aria-label="update"
              onClick={() => setEditModalVisibility(true)}
            >
              <UpdateIcon fontSize="small" />
            </IconButton>
            <Button
              size="small"
              onClick={() => setDiscountModalVisibility(true)}
            >
              DISCOUNT
            </Button>
            <IconButton
              aria-label="delete"
              onClick={() => {
                setDialogVisibility(true);
              }}
            >
              <DeleteIcon fontSize="small" />
            </IconButton>
          </div>
        ) : null}
        {role === 'USER' ? (
          <Button size="small" onClick={handleReserve}>
            Reserve
          </Button>
        ) : null}
      </CardActions>
      <IsaDialog
        isVisible={isDialogForDeleteVisible}
        title={`Are you sure you want to delete vehicle '${vehicle.brand}${vehicle.model}' ?`}
        handleClose={() => {
          setDialogVisibility(false);
        }}
        callYesAction={handleDeleteVehicle}
      />
    </Card>
  );
}

function getModalStyle() {
  const top = 50;
  const left = 50;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`
  };
}

const useStyles = makeStyles(theme => ({
  paper: {
    position: 'absolute',
    width: 450,
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3)
  },
  rating: {
    marginTop: 20
  },
  card: {
    width: 280,
    marginBottom: 25,
    marginRight: 25,
    padding: 15,
    paddingBottom: 15,
    backgroundImage: `url(${CardBackground})`,
    textAlign: 'center'
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)'
  },
  title: {
    fontSize: 14
  },
  pos: {
    marginLeft: 5,
    marginBottom: 12
  }
}));
