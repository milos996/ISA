import React from "react";
import ReactDOM from "react-dom";
import { Router } from "react-router-dom";
import "./styles/index.css";
import App from "./App";
import { Provider } from "react-redux";
import * as serviceWorker from "./serviceWorker";
import store from "./store";
import ErrorInformationModal from "./components/UI/ErrorInformationModal";
import Navbar from "./components/Navbar";
import createHistory from "history/createBrowserHistory";

export const history = createHistory();

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Navbar />
      <App />
      <ErrorInformationModal />
    </Router>
  </Provider>,
  document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
