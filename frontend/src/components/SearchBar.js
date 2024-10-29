import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/searchBar.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import EventCard from './EventCard'; 

const SearchBar = () => {
    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(false); 
    const [error, setError] = useState(null);
    const [searchQuery, setSearchQuery] = useState("");


     const handleSearchClick = async (event) => {
        if (loading) return; //to prevent search multiple times
        setLoading(true);
        setError(null);
        try{
            const response = await fetch(`${process.env.REACT_APP_BASE_URL}/event/eventsByName/${searchQuery}`);
            if (!response.ok) {
                throw new Error('Failed to fetch events');
            }

            const data = await response.json();
            setEvents(data);
            setLoading(false);
        }catch(error){
            console.error("Error fetching events:", error); 
            setError(error.message); 
            setLoading(false); 
        }
    }

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div className="mx-auto d-flex flex-column">
            <form className="form-inline d-flex mb-4">
                <input
                    className="form-control mr-sm-2"
                    type="search"
                    placeholder="Search Event"
                    aria-label="Search"
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                />
                <button
                    className="buttonSearch"
                    type="button"
                    onClick={handleSearchClick}
                    disabled={loading}
                >
                    {loading ? (
                        <i className="fas fa-spinner fa-spin white"></i>
                    ) : (
                        <i className="fas fa-search white"></i>
                    )}
                </button>
            </form>
            
            {loading && <div>Loading...</div>}
            {error && <div>Error: {error}</div>}

            <div className="container">
                <div className="row">
                    {events.length > 0 && events.map((event, index) => (
                        <div key={index} className="col-sm-12 col-md-6 col-lg-4 mb-4">
                            <EventCard event={event} />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}
export default SearchBar;