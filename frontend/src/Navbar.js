import React from 'react';
import Dropdown from './Dropdown';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './navbar.css';
import '@fortawesome/fontawesome-free/css/all.min.css';


const Navbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-custom-purple">
            <div className="navbar-nav navbar-custom-pl-5">
                <div className="circle-container">
                    <span className="circle-text">GB</span>
                </div>

            <a className="navbar-brand transform-13 pl-5 custom-navbar-font" href="#">Home</a>
            <Dropdown className={"pl-5-767"}
                buttonLabel="Events"
                items={["Search for event", "Your events"]}
            />
            <Dropdown className={"pl-5-767"}
                buttonLabel="Users"
                items={["Look for users", "Else"]}
            />                
                
            </div>

            <div className="mx-auto d-flex">
                <form className="form-inline d-flex">
                    <input className="form-control mr-sm-2" type="search" placeholder="Search Event" aria-label="Search" />
                    <button className="buttonSearch" type="submit">
                        <i className="fas fa-search white"></i> 
                    </button>
                </form>
            </div>


            <div className="ml-auto navbar-custom-pr-1">
            <a href="https://github.com/GrzegorzBoron23" target="_blank" rel="noopener noreferrer" className="btn transform-13">
                GrzegorzBoron23 <i className="fab fa-github"></i>
            </a>
                <button className="btn transform-13">Login</button>
            </div>
        </nav>
    );
}

export default Navbar;