import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/searchBar.css';

const SearchBar = () => {
    const [searchQuery, setSearchQuery] = useState("");
    const navigate = useNavigate();

    const handleSearchClick = () => {
        console.log("SearchQuery " , searchQuery);
        navigate(`/search/${searchQuery}`); 
    };

    return (
        <div className="mx-auto d-flex flex-column">
            <form className="form-inline d-flex mb-4" onSubmit={(e) => e.preventDefault()}>
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
                    type="submit"
                    onClick={handleSearchClick}
                >
                    <i className="fas fa-search white"></i>
                </button>
            </form>
        </div>
    );
};

export default SearchBar;
