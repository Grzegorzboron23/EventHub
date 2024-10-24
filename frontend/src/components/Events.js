import React, { useState, useEffect } from 'react';
import '../styles/event.css';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS
import { Card, Button, Row, Col } from 'react-bootstrap';

const Events = () => {
    const [events, setEvents] = useState([]); 
    const [loading, setLoading] = useState(true); 
    const [error, setError] = useState(null);

    // getEvents from backend
    const fetchEvents = async (page = 1) => {
        console.log("Fetching events for page:", page); 
        try {
            console.log("Base URL:", process.env.REACT_APP_BASE_URL);
            const response = await fetch(`${process.env.REACT_APP_BASE_URL}/event/all/${page}`);
            console.log("Response status:", response.status);
            if (!response.ok) {
                throw new Error('Failed to fetch events');
            }
            const data = await response.json();
            console.log("Fetched events:", data);
            setEvents(data); 
            setLoading(false);
        } catch (error) {
            console.error("Error fetching events:", error); 
            setError(error.message); 
            setLoading(false); 
        }
    };

    useEffect(() => {
        fetchEvents();
    }, []);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div className="container text-center">
            <h2 className="mb-4">Event List</h2>
            <Row>
                {events.map((event, index) => (
                    <Col key={index} sm={12} md={6} lg={4} className="mb-4">
                        <Card className="h-100">
                            <Card.Img variant="top" src={event.image || "https://via.placeholder.com/150"} alt={event.eventName} />
                            <Card.Body>
                                <Card.Title>{event.eventName}</Card.Title>
                                <Card.Text>{event.eventDescription}</Card.Text>
                                <Button variant="primary">View Details</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </div>
    );
};

export default Events;
