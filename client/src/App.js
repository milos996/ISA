import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import HomeComponent from './components/Home';
import LoginComponent from './components/Login';
import NavbarComponent from './components/Navbar';
import './styles/app.css';

class App extends Component {
  render() {
    return (
      <Router>
        <div style={{ alignItems: 'center', alignContent: 'center' }}>
          <NavbarComponent />
          <Route exact path="/" component={HomeComponent} />
          <Route exact path="/login" component={LoginComponent} />
        </div>
      </Router>
    );
  }
}

export default App;
