import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './index.css';
import Navbar from './components/Navbar';
import Home from './components/Home';
import Events from './components/Events';



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Router>
      <Navbar />
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/events" element={<Events />} />
      </Routes>
    </Router>
  </React.StrictMode>
);

