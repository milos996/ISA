import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import HomeComponent from "./pages/Home";
import LoginComponent from "./pages/Login";
import HotelProfile from "./pages/HotelProfile";
import PrivateRoute from "./components/UI/PrivateRoute";
import "./styles/App.css";

const App = () => {
  return (
    <Router>
      <PrivateRoute exact path="/" component={HomeComponent} />
      <Route exact path="/login" component={LoginComponent} />
      <Route path="/user/:id/hotel/:hotelId" component={HotelProfile} />
    </Router>
  );
};

export default App;
