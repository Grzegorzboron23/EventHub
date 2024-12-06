import React from 'react';
import '../styles/event.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Row, Col } from 'react-bootstrap';
import EventCard from './EventCard';
import ImageUpload from '../components/UploadEventImage';
import useFetchData from './fetchData/useFetchData';
import Loading from './errors/LoadingError';
import ErrorComponent from './errors/ErrorComponent'; 

const Events = () => {
    const { data: events, loading, error } = useFetchData(`${process.env.REACT_APP_BASE_URL}/event/all/1`);

    if (loading) return <Loading />;
    if (error) return <ErrorComponent error={error} />;

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
            <div className="mb-4">
                <ImageUpload />
            </div>
        </div>
    );
};

export default Events;
