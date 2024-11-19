import React from 'react';
import '../styles/event.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Row, Col } from 'react-bootstrap';
import EventCard from './EventCard';
import { useNavigate } from 'react-router-dom';
import useFetchData from './fetchData/useFetchData';

const Events = () => {
    const { data: events, loading, error } = useFetchData(`${process.env.REACT_APP_BASE_URL}/event/all/1`);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div className="container text-center">
            <h2 className="mb-4">Event List</h2>
            <Row>
                {events && events.content ? (
                    events.content.map((event, index) => (
                        <Col key={index} sm={12} md={6} lg={4} className="mb-4">
                            <EventCard event={event} />
                        </Col>
                    ))
                ) : (
                    <div>No events available.</div>
                )}
            </Row>
        </div>
    );
};

export default Events;
