import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Card, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const EventCard = ({ event }) => {
    const navigate = useNavigate();

    const handleViewDetails = () => {
        navigate(`/event/${event.eventId}`); 
    };

    return (
        <Card className="h-100">
            <Card.Img variant="top" src={event.image || "https://via.placeholder.com/150"} alt={event.eventName} />
            <Card.Body>
                <Card.Title>{event.eventName}</Card.Title>
                <Card.Text>{event.eventDescription}</Card.Text>
                <Button variant="primary" onClick={handleViewDetails}>View Details</Button>
            </Card.Body>
        </Card>
    );
};

export default EventCard;