import React from 'react';
import Dropdown from '../components/Dropdown';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import '../styles/navbar.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import { useNavigate } from 'react-router-dom';
import SearchBar from '../components/SearchBar';



const Navbar = () => {
    const navigate = useNavigate();

    const handleSearchForEvent = () => {
        navigate('/events');
    };

    const handleSearchForHome = () => {
        navigate('/home');
    };


    return (
        <nav className="navbar navbar-expand-lg navbar-custom-purple">
            <div className="navbar-nav navbar-custom-pl-5">
                <div className="circle-container">
                    <span className="circle-text">GB</span>
                </div>

            <a className="navbar-brand transform-12 pl-5 custom-navbar-font white margin-right-15rem" onClick={handleSearchForHome}>Home</a>
            <Dropdown className={"pl-5-767"}
                buttonLabel="Events"
                items={["Search for event", "Your events"]}
                onItemClick={(item) => {
                    if (item === "Search for event") {
                        handleSearchForEvent();
                    }
                }}
            />
            <Dropdown className={"pl-5-767"}
                buttonLabel="Users"
                items={["Look for users", "Else"]}
            />                
                
            </div>

            <SearchBar />

            <div className="ml-auto navbar-custom-pr-1">
            <a href="https://github.com/GrzegorzBoron23" target="_blank" rel="noopener noreferrer" className="btn transform-12 white">
                GrzegorzBoron23 <i className="fab fa-github"></i>
            </a>
                <button className="btn transform-12 white">Login</button>
            </div>
        </nav>
    );
}

export default Navbar;