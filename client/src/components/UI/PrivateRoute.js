import React from "react";
import { Route, Redirect } from "react-router-dom";
import { useSelector } from "react-redux";
import {
  userDataSelector,
  userTokenSelector
} from "./../../store/user/selectors";

const PrivateRoute = ({ component: Component, accessRole = null, ...rest }) => {
  const userData = useSelector(userDataSelector);
  const userToken = useSelector(userTokenSelector);

  function hasRightRole() {
    if (!accessRole) {
      return true;
    }

    return accessRole === userData.role;
  }

  return (
    <Route
      {...rest}
      render={props =>
        userData && userToken && hasRightRole() ? (
          <Component {...props} />
        ) : (
          <Redirect
            to={{
              pathname: "/page-not-found",
              state: { from: props.location }
            }}
          />
        )
      }
    />
  );
};

export default PrivateRoute;
