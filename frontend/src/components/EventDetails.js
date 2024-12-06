import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Card, Container } from 'react-bootstrap';
import '../styles/eventDetails.css';
import useFetchData from './fetchData/useFetchData';
import Error from './errors/ErrorComponent';
import Loading from './errors/LoadingError';





const EventDetails = () => {
    const { id } = useParams(); 
    const url = `${process.env.REACT_APP_BASE_URL}/event/${id}`;
    const { data: event, loading, error } = useFetchData(url);

    const formatDateTime = (isoString) => {
        const date = new Date(isoString);
        const datePart = date.toISOString().split('T')[0];
        const timePart = date.toTimeString().split(' ')[0];
        return `${datePart} ${timePart}`;
    };

    if (loading) return <Loading />;
    if (error) return <Error message={error} />
    

    return (
        <Container className="mt-5">
            {event && (
                <Card>
                    <Card.Img className="cardImg" variant="top" src={event.image || "https://via.placeholder.com/150"} alt={event.eventName} />
                    <Card.Body>
                        <Card.Title className="text-center">{event.eventName}</Card.Title>
                        <Card.Text className="text-center">{event.eventDescription}</Card.Text>
                        <Card.Text><strong>Start Date:</strong> {formatDateTime(event.startTime)}</Card.Text>
                        <Card.Text><strong>End Date:</strong> {formatDateTime(event.endTime)}</Card.Text>
                        <Card.Text>
                            <strong>Location:</strong> {`${event.address.street}, ${event.address.city}, ${event.address.country}`}
                        </Card.Text>
                    </Card.Body>
                </Card>
            )}
        </Container>
    );
    
};

export default EventDetails;
