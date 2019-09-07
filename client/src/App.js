import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import HomeComponent from "./pages/Home";
import LoginComponent from "./pages/Login";
import HotelProfile from "./pages/HotelProfile";
import PrivateRoute from "./components/UI/PrivateRoute";
import ErrorInformationModal from "./components/UI/ErrorInformationModal";
import AirlineProfile from "./pages/AirlineProfile";

const App = () => {
  return (
    <Router>
      <PrivateRoute exact path="/" component={HomeComponent} />
      <Route exact path="/user/:id/hotel" component={HotelProfile} />
      <Route exact path="/login" component={LoginComponent} />
      <Route exact path="/airline/:id" component={AirlineProfile} />
      <ErrorInformationModal />
    </Router>
  );
};

export default App;
