import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import HomeComponent from './components/Home';
import LoginComponent from './components/Login';
import './styles/App.css';

class App extends Component {
  render() {
    return (
      <Router>
        <div>
          <Route exact path="/" component={HomeComponent} />
          <Route exact path="/login" component={LoginComponent} />
        </div>
      </Router>
    );
  }
}

export default App;
