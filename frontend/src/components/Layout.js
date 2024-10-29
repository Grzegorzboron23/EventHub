import React from 'react';
import Navbar from './Navbar';

const Layout = ({ children }) => (
  <div>
    <Navbar />  
    <main>{children}</main>  
  </div>
);
