import React from 'react';
import './home.css'

const Home = () => {
    return (
        <div className="home-container">
            <h1>Welcome to the Event Application!!!</h1>
            <h2>
                In this application you can both add and browse events
                 in your area and create free advertising for them.
                  The more active you are, the higher your events will 
                  be positioned!!!
            </h2>

            {/* TODO: ADD INSTRUCTION WITH IMAGES HOW TO ADD EVENTS ETC... */}
        </div>
    );
};

export default Home;