import React from "react";
import { Route, Redirect, Switch } from "react-router-dom";
import HomePage from "./pages/Home";
import LoginPage from "./pages/Login";
import RegistrationPage from "./pages/Registration";
import HotelProfilePage from "./pages/HotelProfile";
import PrivateRoute from "./components/UI/PrivateRoute";
import AirlineProfile from "./pages/AirlineProfile";
import HotelsPage from "./pages/Hotels";
import HotelRoomsPage from "./pages/HotelRooms";
import UserProfile from "./pages/UserProfile";
import TicketReservation from "./components/airplane_ticket/TicketReservation";
import ChooseSeats from "./components/airplane_ticket/ChooseSeats";
import Search from "./components/user/Search";
import GroupTripConfirmation from "./components/airplane_ticket/GroupTripConfirmation";
import AdminPage from "./pages/AdminPage";
import RentACarPage from "./pages/RentACars";
import RentACarProfilePage from "./pages/RentACarProfile";

const App = () => {
  return (
    <Switch>
      <Route exact path="/" component={HomePage} />
      <Route exact path="/register" component={RegistrationPage} />
      <Route
        exact
        path="/user/:id/hotel"
        component={HotelProfilePage}
        role={"HOTEL_ADMIN"}
      />
      <Route exact path="/airlines" component={() => <h1>Airlines</h1>} />
      <Route exact path="/hotels" component={() => <h1>Hotels</h1>} />
      <Route exact path="/rent-a-cars" component={() => <h1>Rent a cars</h1>} />
      <Route exact path="/airline/:id" component={AirlineProfile} />
      <Route exact path="/login" component={LoginPage} />
      <Route exact path="/hotel-reservation" component={HotelsPage} />
      <Route exact path="/ticket-reservation" component={TicketReservation} />
      <Route
        exact
        path="/group-trip-confirmation-page"
        component={GroupTripConfirmation}
      />
      <Route
        exact
        path="/ticket-reservation/:id/choose-seat"
        component={ChooseSeats}
      />
      <Route
        exact
        path="/ticket-reservation/:id/choose-seat/search/:requestType"
        component={Search}
      />
      <Route exact path="/rent-a-cars" component={RentACarPage} />
      <Route
        exact
        path="/rent-a-cars/:id/vehicles"
        component={RentACarProfilePage}
      />
      <Route exact path="/airline/:id" component={AirlineProfile} />
      <Route
        exact
        path="/hotel-reservation/:id/rooms"
        component={HotelRoomsPage}
      />
      <Route exact path="/user/:id" component={UserProfile} />
      <Route exact path="/admin" component={AdminPage} />

      <Route
        exact
        path="/page-not-found"
        component={() => <h1>Page not found</h1>}
      />
      <Redirect from="*" to="/page-not-found" />
    </Switch>
  );
};

export default App;
