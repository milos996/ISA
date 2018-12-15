import React, { Component } from 'react';
import { Navbar, NavItem, MenuItem, NavDropdown, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import navbar from '../styles/navbar';

class NavbarComponent extends Component {
  render() {
    return (
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <Link to={'/'} className="navbar-brand">
          CompanyUMS
        </Link>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdownMenuLink"
                role="button"
                dataToggle="dropdown"
                ariaHaspopup="true"
                ariaExpanded="false"
              >
                Usluge
              </a>
              <div
                className="dropdown-menu"
                ariaLabelledby="navbarDropdownMenuLink"
              >
                <a class="dropdown-item" href="#">
                  Action
                </a>
                <a class="dropdown-item" href="#">
                  Another action
                </a>
                <a class="dropdown-item" href="#">
                  Something else here
                </a>
              </div>
            </li>
          </ul>
          <Link to={'/login'}>Login</Link>
        </div>
      </nav>
    );
  }
}

export default NavbarComponent;
