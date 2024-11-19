import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import EventCard from './EventCard';
import useFetchData from './fetchData/useFetchData';
import LoadingComponent from './errors/LoadingError';
import ErrorComponent from './errors/ErrorComponent';


const SearchResults = () => {
    const { query } = useParams();
    const { data: response, loading, error } = useFetchData(`${process.env.REACT_APP_BASE_URL}/event/eventsByName/${query}`);

    if (loading) return <LoadingComponent />;
    if (error) return <ErrorComponent error={error} />;

    const events = response?.content || [];

    return (
        <div className="container d-flex flex-column align-items-center justify-content-center">
             <h2 className="mb-4">Event List</h2>
            <Row>
                {events.length > 0 ? (
                    events.map((event, index) => (
                        <div key={index} className="col-sm-12 col-md-6 col-lg-4 mb-4">
                            <EventCard event={event} />
                        </div>
                    ))
                ) : (
                    <div>No events found for "{query}"</div>
                )}
            </Row>
        </div>
    );
};

export default SearchResults;
