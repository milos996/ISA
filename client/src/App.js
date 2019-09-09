import React from "react";
import { Route, Redirect, Switch } from "react-router-dom";
import HomePage from "./pages/Home";
import LoginPage from "./pages/Login";
import HotelProfilePage from "./pages/HotelProfile";
import PrivateRoute from "./components/UI/PrivateRoute";
import HotelsPage from "./pages/Hotels";
import HotelRoomsPage from "./pages/HotelRooms";
import UserProfile from "./pages/UserProfile";
import TicketReservation from "./components/airplane_ticket/TicketReservation";
import ChooseSeats from "./components/airplane_ticket/ChooseSeats";

const App = () => {
  return (
    <Switch>
      <Route exact path="/" component={HomePage} />
      <Route
        exact
        path="/register"
        component={() => <h1>This is register page</h1>}
      />
      <Route exact path="/user/:id/hotel" component={HotelProfilePage} />
      <Route exact path="/airlines" component={() => <h1>Airlines</h1>} />
      <Route exact path="/hotels" component={() => <h1>Hotels</h1>} />
      <Route exact path="/rent-a-cars" component={() => <h1>Rent a cars</h1>} />
      <Route exact path="/login" component={LoginPage} />
      <Route exact path="/hotel-reservation" component={HotelsPage} />
      <Route exact path="/ticket-reservation" component={TicketReservation} />
      <Route
        exact
        path="/ticket-reservation/choose-seat"
        component={ChooseSeats}
      />
      <Route
        exact
        path="/hotel-reservation/:id/rooms"
        component={HotelRoomsPage}
      />
      <Route
        exact
        path="/hotel-reservation/:id/rooms"
        component={HotelRoomsPage}
      />
      <Route exact path="/user/:id" component={UserProfile} />
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
