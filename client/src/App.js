import React from "react";
import { Route, Redirect, Switch } from "react-router-dom";
import HomeComponent from "./pages/Home";
import LoginComponent from "./pages/Login";
import HotelProfile from "./pages/HotelProfile";
import PrivateRoute from "./components/UI/PrivateRoute";

const App = () => {
  return (
    <Switch>
      <Route exact path="/" component={HomeComponent} />
      <Route
        exact
        path="/register"
        component={() => <h1>This is register page</h1>}
      />
      <PrivateRoute exact path="/user/:id/hotel" component={HotelProfile} />
      <Route exact path="/airlines" component={() => <h1>Airlines</h1>} />
      <Route exact path="/hotels" component={() => <h1>Hotels</h1>} />
      <Route exact path="/rent-a-cars" component={() => <h1>Rent a cars</h1>} />
      <Route exact path="/login" component={LoginComponent} />
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
